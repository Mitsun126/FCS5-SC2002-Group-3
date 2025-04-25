package code;

import java.util.ArrayList;

/**
 * Represents an HDB Manager who can create, edit, delete, and manage projects.
 * The manager can also approve or reject applicants and handle enquiries.
 */
public class HDB_Manager extends User {
    private ArrayList<Project> createdProjects; // List of projects created by the manager
    private EligibilityChecker eligibilityChecker = new EligibilityChecker(); // Eligibility checker for applicants

    /**
     * Default constructor for creating a HDB_Manager instance.
     */
    public HDB_Manager() {
        super();
        this.createdProjects = new ArrayList<>();
    }

    /**
     * Constructor to initialize a HDB_Manager with specific details.
     * 
     * @param name The name of the manager.
     * @param NRIC The NRIC of the manager.
     * @param age The age of the manager.
     * @param marital_status The marital status of the manager.
     * @param password The password of the manager.
     */
    public HDB_Manager(String name, String NRIC, int age, String marital_status, String password) {
        super(name, NRIC, age, marital_status, password);
        this.createdProjects = new ArrayList<>();
    }
    
    /**
     * Gets the created projects array.
     * 
     * @return The createdProject array.
     */
    public ArrayList<Project> getCreatedProjects(){return this.createdProjects;}

    /**
     * Creates a new project and adds it to the list of created projects.
     * 
     * @param projectName The name of the project.
     * @param neighbourhood The neighborhood of the project.
     * @param t1 Type of the first flat.
     * @param nof1 Number of flats of type 1.
     * @param sp1 Selling price of flats of type 1.
     * @param t2 Type of the second flat.
     * @param nof2 Number of flats of type 2.
     * @param sp2 Selling price of flats of type 2.
     * @param aod Application opening date.
     * @param acd Application closing date.
     * @param noo Officer slot count.
     * @return The created project.
     */
    public Project createProject(String projectName, String neighbourhood, String t1, int nof1, int sp1, String t2, int nof2, int sp2, String aod, String acd, int noo) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate newStart = LocalDate.parse(aod, formatter);
		LocalDate newEnd = LocalDate.parse(acd,formatter);
		
		for (Project existing : createdProjects) {
			
			if (existing.get_ProjectName().equalsIgnoreCase(projectName)) {
				System.out.println("Project name has already been used!\n");
				return null;
			}
			
			LocalDate existingStart = LocalDate.parse(existing.get_Application_opening_date(),formatter);
			LocalDate existingEnd = LocalDate.parse(existing.get_Application_closing_date(),formatter);
			
			if (!(newEnd.isBefore(existingStart) || existingEnd.isBefore(newStart))) { 
				System.out.println("Project clashes with existing project!\n");
				return null;
			}
		}
		
		Project newProject = new Project(projectName, neighbourhood, t1, nof1, sp1, t2, nof2, sp2, aod, acd, this.get_Name(), noo);
		createdProjects.add(newProject);
		System.out.println("Project created Successfully!\n");
		return newProject;
	}

    /**
     * Edits a project field by changing its value.
     * 
     * @param project The project to be edited.
     * @param field The field to be edited.
     * @param newValue The new value for the field.
     */
    public void editProject(Project project, String field, Object newValue) {
        switch (field.toLowerCase()) {
            case "projectname": project.set_ProjectName((String) newValue); break;
            case "neighbourhood": project.set_Neighbourhood((String) newValue); break;
            case "type_1": project.get_flat_1().set_type((String) newValue); break;
            case "nof1": project.get_flat_1().set_number_of_flats((Integer) newValue); break;
            case "sp1": project.get_flat_1().set_sellingPrice((Integer) newValue); break;
            case "type_2": project.get_flat_2().set_type((String) newValue); break;
            case "nof2": project.get_flat_2().set_number_of_flats((Integer) newValue); break;
            case "sp2": project.get_flat_2().set_sellingPrice((Integer) newValue); break;
            case "open": project.set_Application_opening_date((String) newValue); break;
            case "close": project.set_Application_closing_date((String) newValue); break;
            case "officerslots": project.set_Officer_slot((Integer) newValue); break;
            default: System.out.println("Invalid field!"); break;
        }
        System.out.println("Project edited successfully!");
    }

    /**
     * Deletes a project from the list of created projects.
     * 
     * @param project The project to be deleted.
     */
    public void deleteProject(Project project) {
        createdProjects.remove(project);
        System.out.println("Project deleted successfully!");
    }

    /**
     * Views all projects.
     * 
     * @param allProjects List of all projects.
     */
    public void viewAllProjects(ArrayList<Project> allProjects) {
        for (Project p : allProjects) {
            System.out.println(p);
        }
    }

    /**
     * Views the projects created by the manager.
     * 
     * @return The list of projects created by the manager.
     */
    public ArrayList<Project> viewMyProjects() {
        for (Project p : createdProjects) {
            System.out.println(p);
        }
        return createdProjects;
    }

    /**
     * Toggles the visibility of a project.
     * 
     * @param project The project whose visibility needs to be toggled.
     * @param visibility The new visibility status (e.g., "Visible" or "Hidden").
     */
    public void toggleProjectVisibility(Project project, String visibility) {
        project.set_visibility(visibility);
    }

    /**
     * Views all enquiries for a project.
     * 
     * @param project The project whose enquiries need to be viewed.
     */
    public void viewEnquiries(Project project) {
        ArrayList<Enquiry> enquiries = project.getEnquiries();
        if (enquiries.isEmpty()) {
            System.out.println("No enquiries found for project " + project.get_ProjectName());
        } else {
            for (Enquiry e : enquiries) {
                System.out.println(e);
            }
        }
    }

    /**
     * Replies to an enquiry related to a project.
     * 
     * @param project The project associated with the enquiry.
     * @param enquiryID The ID of the enquiry to reply to.
     * @param reply The reply to be sent to the enquiry.
     */
    public void replyToEnquiry(Project project, int enquiryID, String reply) {
        if (!createdProjects.contains(project)) {
            System.out.println("You are not handling this project!");
            return;
        }
        for (Enquiry e : project.getEnquiries()) {
            if (e.getEnquiryID() == enquiryID) {
                e.setReply(reply);
                System.out.println("Reply sent successfully!");
                return;
            }
        }
        System.out.println("Enquiry not found!");
    }

    /**
     * Approves an officer to be assigned to a project.
     * 
     * @param project The project to which the officer will be assigned.
     * @param officer The officer to be assigned to the project.
     */
    public void approveOfficer(Project project, HDB_Officer officer) {
        officer.assignProject(project);
    }

    /**
     * Approves or rejects an applicant's BTO application.
     * 
     * @param project The project for which the application is being processed.
     * @param applicant The applicant whose application is being processed.
     * @param flatType The type of flat the applicant is applying for.
     */
    public void approveApplicant(Project project, Applicant applicant, String flatType) {
        boolean isEligible = eligibilityChecker.isEligibleForFlat(applicant, flatType);
        boolean isAvailable = eligibilityChecker.isAvailableFlat(project, flatType);

        if (isEligible && isAvailable) {
            System.out.println("BTO application successful!");
            applicant.setApplicationStatus("Successful");
            return;
        }

        System.out.println("BTO application unsuccessful!");
        applicant.setApplicationStatus("Unsuccessful");
        project.removeApplicant(applicant);
    }

    /**
     * Approves or rejects an applicant's withdrawal request.
     * 
     * @param applicant The applicant whose withdrawal request is being processed.
     */
    public void approveWithdrawal(Applicant applicant) {
        boolean success = applicant.withdrawApplication();
        Project project = applicant.getAppliedProject();

        if (success == true && project != null) {
            System.out.println("Withdrawal approved!");
            project.removeApplicant(applicant);
        } else {
            System.out.println("Withdrawal rejected!");
        }
    }

    /**
     * Generates a report based on a filter category and filter value.
     * 
     * @param applicants List of applicants.
     * @param filterCategory The category to filter by (e.g., "age", "status").
     * @param filterValue The value to filter by.
     */
    public void generateReport(ArrayList<Applicant> applicants, String filterCategory, String filterValue) {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateApplicantReport(applicants, filterCategory, filterValue);
    }
}
