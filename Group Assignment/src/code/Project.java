package code;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a housing project in the HDB system.
 * This class contains details such as the project name, flats available, application dates,
 * HDB manager, assigned officers, applicants, enquiries, and project visibility.
 */
public class Project {
    
    private String projectName;
    private String neighbourhood;
    private Flat flat_1;
    private Flat flat_2;
    private String application_opening_date;
    private String application_closing_date;
    private String HDB_Manager;
    private int officer_slot;
    private ArrayList<HDB_Officer> officers;   
    private boolean visibility;
    private ArrayList<Enquiry> enquiries;
    private ArrayList<Applicant> applicants;
    private ArrayList<HDB_Officer> requestingOfficer;

    /**
     * Default constructor to initialize an empty project.
     */
    public Project() {
        this.projectName = "";
        this.neighbourhood = "";
        this.flat_1 = new Flat();
        this.flat_2 = new Flat();
        this.application_opening_date = "";
        this.application_closing_date = "";
        this.HDB_Manager = "";
        this.officer_slot = 0;
        this.visibility = false;
        this.officers = new ArrayList<>();
        this.enquiries = new ArrayList<>();
        this.applicants = new ArrayList<>();
        this.requestingOfficer = new ArrayList<>();
    }

    /**
     * Constructor to initialize a project with specified details.
     * 
     * @param projectName            The name of the project.
     * @param neighbourhood          The neighbourhood of the project.
     * @param t1                     The type of the first flat.
     * @param nof1                   The number of units in flat 1.
     * @param sp1                    The size of flat 1.
     * @param t2                     The type of the second flat.
     * @param nof2                   The number of units in flat 2.
     * @param sp2                    The size of flat 2.
     * @param aod                    The application opening date.
     * @param acd                    The application closing date.
     * @param Manager                The name of the HDB manager.
     * @param noo                    The number of officer slots available.
     */
    public Project(String projectName, String neighbourhood, String t1, int nof1, int sp1, 
                   String t2, int nof2, int sp2, String aod, String acd, String Manager, int noo) {
        this.projectName = projectName;
        this.neighbourhood = neighbourhood;
        this.flat_1 = new Flat(t1, nof1, sp1);
        this.flat_2 = new Flat(t2, nof2, sp2);
        this.application_opening_date = aod;
        this.application_closing_date = acd;
        this.HDB_Manager = Manager;
        this.officer_slot = noo;
        this.visibility = false;
        this.officers = new ArrayList<>();
        this.enquiries = new ArrayList<>();
        this.applicants = new ArrayList<>();
        this.requestingOfficer = new ArrayList<>();
    }

    // Getter and Setter methods with comments

    /**
     * Gets the project name.
     * 
     * @return The project name.
     */
    public String get_ProjectName() { return projectName; }

    /**
     * Sets the project name.
     * 
     * @param projectName The name of the project.
     */
    public void set_ProjectName(String projectName) { this.projectName = projectName; }

    /**
     * Gets the neighbourhood of the project.
     * 
     * @return The neighbourhood.
     */
    public String get_Neighbourhood() { return neighbourhood; }

    /**
     * Sets the neighbourhood for the project.
     * 
     * @param neighbourhood The neighbourhood.
     */
    public void set_Neighbourhood(String neighbourhood) { this.neighbourhood = neighbourhood; }

    /**
     * Gets the first flat associated with the project.
     * 
     * @return The first flat.
     */
    public Flat get_flat_1() { return flat_1; }

    /**
     * Gets the second flat associated with the project.
     * 
     * @return The second flat.
     */
    public Flat get_flat_2() { return flat_2; }

    /**
     * Gets the application opening date.
     * 
     * @return The application opening date.
     */
    public String get_Application_opening_date() { return application_opening_date; }

    /**
     * Sets the application opening date.
     * 
     * @param application_opening_date The application opening date.
     */
    public void set_Application_opening_date(String application_opening_date) { this.application_opening_date = application_opening_date; }

    /**
     * Gets the application closing date.
     * 
     * @return The application closing date.
     */
    public String get_Application_closing_date() { return application_closing_date; }

    /**
     * Sets the application closing date.
     * 
     * @param application_closing_date The application closing date.
     */
    public void set_Application_closing_date(String application_closing_date) { this.application_closing_date = application_closing_date; }

    /**
     * Gets the name of the HDB manager.
     * 
     * @return The HDB manager name.
     */
    public String get_HDB_Manager() { return HDB_Manager; }

    /**
     * Sets the name of the HDB manager.
     * 
     * @param HDB_Manager The name of the HDB manager.
     */
    public void set_HDB_Manager(String HDB_Manager) { this.HDB_Manager = HDB_Manager; }

    /**
     * Gets the number of available officer slots.
     * 
     * @return The number of officer slots.
     */
    public int get_Officer_slot() { return officer_slot; }

    /**
     * Sets the number of available officer slots.
     * 
     * @param officer_slot The number of officer slots.
     */
    public void set_Officer_slot(int officer_slot) { this.officer_slot = officer_slot; }

    /**
     * Gets the list of officers assigned to the project.
     * 
     * @return The list of officers.
     */
    public ArrayList<HDB_Officer> get_officers() { return officers; }

    /**
     * Adds an officer to the project, provided there is space.
     * 
     * @param officer The officer to add.
     */
    public void add_officer(HDB_Officer officer) {
        if (officers.size() >= officer_slot) {
            System.out.println("Maximum Number of Officers!");
        } else {
            officers.add(officer);
            removeRequestingOfficer(officer);
            System.out.println("Officer Added Successfully!");
        }
    }

    /**
     * Gets the visibility status of the project.
     * 
     * @return True if the project is visible, false otherwise.
     */
    public boolean get_visibility() { return visibility; }

    /**
     * Sets the visibility of the project.
     * 
     * @param on_off "on" to make visible, "off" to hide.
     */
    public void set_visibility(String on_off) {
        if (on_off.equalsIgnoreCase("on")) {
            visibility = true;
        } else if (on_off.equalsIgnoreCase("off")) {
            visibility = false;
        } else {
            System.out.println("Invalid Input!");
        }
    }

    /**
     * Adds an enquiry to the project.
     * 
     * @param enquiry The enquiry to add.
     */
    public void add_Enquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
    }

    /**
     * Gets the list of enquiries related to the project.
     * 
     * @return The list of enquiries.
     */
    public ArrayList<Enquiry> getEnquiries() {
        return enquiries;
    }

    /**
     * Gets the list of applicants for the project.
     * 
     * @return The list of applicants.
     */
    public ArrayList<Applicant> getApplicants() { return applicants; }

    /**
     * Adds an applicant to the project.
     * 
     * @param applicant The applicant to add.
     */
    public void addApplicant(Applicant applicant) { applicants.add(applicant); }

    /**
     * Gets an applicant by their NRIC.
     * 
     * @param NRIC The NRIC of the applicant.
     * @return The applicant with the specified NRIC, or null if not found.
     */
    public Applicant getApplicantByNRIC(String NRIC) {
        for (Applicant applicant: applicants) {
            if (applicant.get_Nric().equals(NRIC)) return applicant;
        }
        return null;
    }

    /**
     * Removes an applicant from the project.
     * 
     * @param applicant The applicant to remove.
     */
    public void removeApplicant(Applicant applicant) {
        if (this.applicants.contains(applicant)) {
            applicants.remove(applicant);
        }
    }

    /**
     * Gets the list of officers requesting for the project.
     * 
     * @return The list of requesting officers.
     */
    public ArrayList<HDB_Officer> getRequestingOfficer() { return requestingOfficer; }

    /**
     * Adds an officer to the list of requesting officers.
     * 
     * @param officer The officer to add.
     */
    public void addRequestingOfficer(HDB_Officer officer) { requestingOfficer.add(officer); }

    /**
     * Gets a requesting officer by their NRIC.
     * 
     * @param NRIC The NRIC of the officer.
     * @return The officer with the specified NRIC, or null if not found.
     */
    public HDB_Officer getRequestingOfficerByNRIC(String NRIC) {
        for (HDB_Officer officer: requestingOfficer) {
            if (officer.get_Nric().equals(NRIC)) return officer;
        }
        return null;
    }

    /**
     * Removes a requesting officer from the project.
     * 
     * @param officer The officer to remove.
     */
    public void removeRequestingOfficer(HDB_Officer officer) {
        if (this.requestingOfficer.contains(officer)) {
            requestingOfficer.remove(officer);
        }
    }

    /**
     * Returns a string representation of the project, including project details and the list of assigned officers.
     * 
     * @return A string representation of the project.
     */
    @Override
    public String toString() {
        String officerNames = officers.stream()
                .map(HDB_Officer::get_Name)
                .collect(Collectors.joining(",")); 
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("\nProject Name: ").append(projectName).append("\n")
          .append("Neighbourhood: ").append(neighbourhood).append("\n")
          .append("Type 1: ").append(flat_1)
          .append("Type 2: ").append(flat_2)
          .append("Application Opening Date: ").append(application_opening_date).append("\n")
          .append("Application Closing Date: ").append(application_closing_date).append("\n")
          .append("HDB Manager: ").append(HDB_Manager).append("\n")
          .append("Officer Slot: ").append(officer_slot).append("\n")
          .append("Visibility: ").append(visibility ? "Open" : "Closed").append("\n")
          .append("Number of Officers Assigned: ").append(officers.size()).append("\n")
          .append("Officers Assigned: ").append(officerNames).append("\n")
          .append("Total Enquiries: ").append(enquiries.size()).append("\n");

        return sb.toString();
    }
}
