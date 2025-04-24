package code;

import java.util.ArrayList;

/**
 * Represents an applicant in the HDB flat booking system.
 * Applicants can view and apply for projects, manage their applications,
 * and submit, edit, or delete enquiries related to projects.
 */
public class Applicant extends User {

    /** The project the applicant has applied for */
    private Project appliedProject;

    /** The current application status (e.g., Pending, Approved, Rejected) */
    private String applicationStatus;

    /** List of enquiries submitted by the applicant */
    private ArrayList<Enquiry> enquiries;

    /** The type of flat the applicant is applying for */
    private String flatType;

    /** Flag indicating whether the applicant has requested to withdraw their application */
    private boolean withdrawal_status;

    /** Utility to check if the applicant is eligible for a project */
    private EligibilityChecker eligibilityChecker = new EligibilityChecker();

    /**
     * Default constructor for creating an empty applicant.
     */
    public Applicant() {
        super();
        this.appliedProject = null;
        this.applicationStatus = "";
        this.enquiries = new ArrayList<>();
        this.withdrawal_status = false;
    }

    /**
     * Constructor with applicant details.
     *
     * @param name           Applicant's name
     * @param NRIC           Applicant's NRIC
     * @param age            Applicant's age
     * @param marital_status Applicant's marital status
     * @param password       Applicant's password
     */
    public Applicant(String name, String NRIC, int age, String marital_status, String password) {
        super(name, NRIC, age, marital_status, password);
        this.appliedProject = null;
        this.applicationStatus = "";
        this.enquiries = new ArrayList<>();
        this.withdrawal_status = false;
    }

    /**
     * Returns a list of open and eligible projects for the applicant.
     *
     * @param projects List of all available projects
     * @return List of eligible open projects
     */
    public ArrayList<Project> viewOpenProjects(ArrayList<Project> projects){
        ArrayList<Project> openProjects = new ArrayList<>();
        for (Project project: projects) {
            if (project.get_visibility() && eligibilityChecker.isEligibleForProjectApplicant(this, project)) {
                openProjects.add(project);
            }
        }
        return openProjects;
    }

    /** @return the project the applicant has applied for */
    public Project getAppliedProject() {
        return appliedProject;
    }

    /**
     * Applies for a project if not already applied and eligible.
     *
     * @param project The project to apply for
     */
    public void applyForProject(Project project) {
        if (appliedProject != null) {
            System.out.println("You have already applied for a project: " + appliedProject.get_ProjectName());
            return;
        }

        if (!eligibilityChecker.isEligibleForProjectApplicant(this, project)) {
            System.out.println("You are not eligible to apply for this project!");
            return;
        }

        appliedProject = project;
        applicationStatus = "Pending";
        project.addApplicant(this);
        System.out.println("Application submitted for this project!");
    }

    /** @return current application status */
    public String getApplicationStatus() {
        return applicationStatus;
    }

    /**
     * Sets the status of the application.
     *
     * @param status New status of the application
     */
    public void setApplicationStatus(String status) {
        this.applicationStatus = status;
    }

    /**
     * Displays details of the applied project.
     */
    public void viewAppliedProject() {
        if (appliedProject == null) {
            System.out.println("You have not applied for any project!");
        } else {
            System.out.println("Project: " + appliedProject.get_ProjectName());
            System.out.println("Status: " + applicationStatus);
        }
    }

    /** @return true if the applicant has requested withdrawal */
    public boolean getWithdrawalStatus() {
        return withdrawal_status;
    }

    /**
     * Marks the applicant as having requested withdrawal.
     */
    public void requestWithdrawal() {
        withdrawal_status = true;
    }

    /**
     * Withdraws the application. Used by the manager.
     *
     * @return true if withdrawal was successful
     */
    public boolean withdrawApplication() {
        if (appliedProject == null) {
            System.out.println("Applicant not applied for any project!");
            return false;
        } else if (!withdrawal_status) {
            System.out.println("Applicant did not request for withdrawal!");
            return false;
        }

        appliedProject = null;
        applicationStatus = "";
        withdrawal_status = false;
        System.out.println("Application withdrawn successfully!");
        return true;
    }

    /**
     * Submits a new enquiry to a project.
     *
     * @param project The project related to the enquiry
     * @param message The enquiry message
     */
    public void submitEnquiry(Project project, String message) {
        Enquiry newEnquiry = new Enquiry(this.get_Nric(), project.get_ProjectName(), message);
        enquiries.add(newEnquiry);
        project.add_Enquiry(newEnquiry);
        System.out.println("Enquiry Submitted Successfully!");
    }

    /**
     * Displays all enquiries submitted by the applicant.
     */
    public void viewEnquiries() {
        if (enquiries.isEmpty()) {
            System.out.println("No Enquiries Found!");
            return;
        }

        for (Enquiry e: enquiries) {
            System.out.println(e);
        }
    }

    /**
     * Edits an existing enquiry by ID.
     *
     * @param enquiryID   ID of the enquiry to edit
     * @param newMessage  New message for the enquiry
     */
    public void editEnquiry(int enquiryID, String newMessage) {
        for (Enquiry e: enquiries) {
            if (e.getEnquiryID() == enquiryID) {
                e.setEnquiryText(newMessage);
                System.out.println("Enquiry Updated!");
                return;
            }
        }
        System.out.println("Enquiry Not Found!");
    }

    /**
     * Deletes an enquiry from the applicant and project records.
     *
     * @param enquiryID ID of the enquiry to delete
     * @param project   Project to remove the enquiry from
     */
    public void deleteEnquiry(int enquiryID, Project project) {
        for (int i = 0; i < enquiries.size(); i++) {
            if (enquiries.get(i).getEnquiryID() == enquiryID) {
                Enquiry enquiry = enquiries.get(i);
                project.getEnquiries().remove(enquiry);
                enquiries.remove(i);

                System.out.println("Enquiry Deleted Successfully!");
                return;
            }
        }
        System.out.println("Enquiry Not Found!");
    }

    /** @param flatType Sets the type of flat the applicant is applying for */
    public void setFlatType(String flatType) {
        this.flatType = flatType;
    }

    /** @return The type of flat applied for */
    public String getFlatType() {
        return flatType;
    }

    /**
     * Returns a string representation of the applicant's profile.
     *
     * @return Applicant details in string format
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Applicant Name: ").append(this.get_Name()).append("\n")
          .append("NRIC: ").append(this.get_Nric()).append("\n")
          .append("Age: ").append(this.get_Age()).append("\n")
          .append("Marital Status: ").append(this.get_Marital_status()).append("\n");

        if (appliedProject != null) {
            sb.append("Flat Type Booked: ").append(flatType).append("\n")
              .append("Applied Project: ").append(appliedProject.get_ProjectName()).append("\n")
              .append("Application Status: ").append(applicationStatus).append("\n");
        } else {
            sb.append("No flat booked yet.\n");
        }

        return sb.toString();
    }
}
