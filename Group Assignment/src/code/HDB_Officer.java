package code;

import java.util.ArrayList;

/**
 * The HDB_Officer class represents an officer who can be assigned to handle HDB projects.
 * Officers can view and respond to enquiries, book flats on behalf of applicants, and generate receipts.
 * Inherits from the Applicant class.
 */
public class HDB_Officer extends Applicant {
	private ArrayList<Project> officerProjects;
	private EligibilityChecker eligibilityChecker = new EligibilityChecker();

	/**
	 * Default constructor initializing the officer with default values and an empty project list.
	 */
	public HDB_Officer() {
		super();
		this.officerProjects = new ArrayList<>();
	}

	/**
	 * Constructs a new HDB_Officer with the given details.
	 * 
	 * @param name           the name of the officer
	 * @param NRIC           the NRIC of the officer
	 * @param age            the age of the officer
	 * @param marital_status the marital status of the officer
	 * @param password       the password of the officer
	 */
	public HDB_Officer(String name, String NRIC, int age, String marital_status, String password) {
		super(name, NRIC, age, marital_status, password);
		this.officerProjects = new ArrayList<>();
	}

	/**
	 * Returns the list of projects the officer is handling.
	 * 
	 * @return the list of officer-assigned projects
	 */
	public ArrayList<Project> get_officerProjects() {
		return this.officerProjects;
	}

	/**
	 * Requests to be assigned to a specific project.
	 * 
	 * @param project the project to be requested
	 */
	public void requestAssignment(Project project) {
		if (!eligibilityChecker.isEligibleToRegisterOfficer(this, project)) {
			System.out.println("Not eligible to register as officer for this project!");
			return;
		}

		if (project.get_officers().size() >= project.get_Officer_slot()) {
			System.out.println("Maximum number of officers for this project!");
			return;
		}

		project.addRequestingOfficer(this);
	}

	/**
	 * Assigns this officer to a project. To be used by a manager.
	 * 
	 * @param project the project to assign
	 */
	public void assignProject(Project project) {
		if (!eligibilityChecker.isEligibleToRegisterOfficer(this, project)) {
			System.out.println("Not eligible to register as officer for this project!");
			return;
		}

		if (project.get_officers().size() >= project.get_Officer_slot()) {
			System.out.println("Maximum number of officers for this project!");
			return;
		}

		officerProjects.add(project);
		project.add_officer(this);
		System.out.println("Officer assigned successfully to project: " + project.get_ProjectName());
	}

	/**
	 * Checks if the officer is assigned to a given project.
	 * 
	 * @param project the project to check
	 * @return true if assigned, false otherwise
	 */
	public boolean isAssigned(Project project) {
		return officerProjects.contains(project);
	}

	/**
	 * Displays details of all projects assigned to the officer.
	 */
	public void viewProjectDetails() {
		for (Project project : officerProjects) {
			System.out.println(project);
		}
	}

	/**
	 * Views all enquiries for a specific project if the officer is assigned.
	 * 
	 * @param project the project to view enquiries from
	 */
	public void viewEnquiries(Project project) {
		if (!this.isAssigned(project)) {
			System.out.println("You are not assigned to this project!");
			return;
		}

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
	 * Replies to a specific enquiry by ID for a project if assigned.
	 * 
	 * @param project   the project containing the enquiry
	 * @param enquiryID the ID of the enquiry
	 * @param reply     the reply text
	 */
	public void replyToEnquiry(Project project, int enquiryID, String reply) {
		if (!this.isAssigned(project)) {
			System.out.println("You are not assigned to this project!");
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
	 * Books a flat for a successful applicant if the officer is assigned to the project.
	 * 
	 * @param project        the project where booking takes place
	 * @param applicantNRIC  the NRIC of the applicant
	 * @param flatType       the type of flat to book
	 */
	public void bookFlat(Project project, String applicantNRIC, String flatType) {
		if (!isAssigned(project)) {
			System.out.println("You are not assigned to this project!");
			return;
		}

		Applicant applicant = project.getApplicantByNRIC(applicantNRIC);
		if (applicant == null) {
			System.out.println("Applicant not found!");
			return;
		}

		if (!"Successful".equalsIgnoreCase(applicant.getApplicationStatus())) {
			System.out.println("Applicant's application is not marked as Successful!");
			return;
		}

		if (!eligibilityChecker.isEligibleForFlat(applicant, flatType)) {
			System.out.println("Applicant is not eligible for selected flat type!");
			return;
		}

		if (!eligibilityChecker.isAvailableFlat(project, flatType)) {
			System.out.println("Selected flat type is not available!");
			return;
		}

		Flat flat1 = project.get_flat_1();
		Flat flat2 = project.get_flat_2();

		// Book the flat
		if (flatType.equalsIgnoreCase(flat1.get_type())) {
			flat1.set_number_of_flats(flat1.get_number_of_flats() - 1);
		} else if (flatType.equalsIgnoreCase(flat2.get_type())) {
			flat2.set_number_of_flats(flat2.get_number_of_flats() - 1);
		}

		applicant.setFlatType(flatType);
		applicant.setApplicationStatus("Booked");

		System.out.println("Flat booking successful for " + applicant.get_Name() + " with " + flatType + " flat!");
	}

	/**
	 * Generates a receipt for an applicant's booking in a project.
	 * 
	 * @param project       the project involved in the booking
	 * @param applicantNRIC the NRIC of the applicant
	 */
	public void generateReceipt(Project project, String applicantNRIC) {
		ReceiptGenerator receiptGenerator = new ReceiptGenerator();
		receiptGenerator.generateApplicantReceipt(project, this, applicantNRIC);
	}
}
