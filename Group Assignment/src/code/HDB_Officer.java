package code;

import java.util.ArrayList;

public class HDB_Officer extends Applicant {
	private ArrayList<Project> officerProjects;
	private EligibilityChecker eligibilityChecker = new EligibilityChecker();

	public HDB_Officer() {
		super();
		this.officerProjects = new ArrayList<>();
	}

	public HDB_Officer(String name, String NRIC, int age, String marital_status, String password) {
		super(name, NRIC, age, marital_status, password);
		this.officerProjects = new ArrayList<>();
	}
	
	//officer projects getter
	public ArrayList<Project> get_officerProjects(){return this.officerProjects;}
	
	//Requesting as officer for project
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
	
	//assigning a project to the officer (For Manager Use)
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
        System.out.println("Officer assigned successfully to project: "+ project.get_ProjectName());
	}
	
	//If project found in projects
	public boolean isAssigned(Project project) {
		return officerProjects.contains(project);
	}
	
	//View all project details that officer is handling
	public void viewProjectDetails() {
		for (Project project: officerProjects) {
			System.out.println(project);
		}
	}
	
	//Viewing all enquires in a project
	public void viewEnquiries(Project project) {
		if (!this.isAssigned(project)) {
			System.out.println("You are not assigned to this project!");
			return;
		}
		
		ArrayList<Enquiry> enquiries = project.getEnquiries();
		if (enquiries.isEmpty()) {
			System.out.println("No enquiries found for project " + project.get_ProjectName());
		} else {
			for (Enquiry e: enquiries) {
				System.out.println(e);
			}
		}
	}
	
	//Replying to enquires
	public void replyToEnquiry(Project project, int enquiryID, String reply) {
		if (!this.isAssigned(project)) {
			System.out.println("You are not assigned to this project!");
			return;
		}
		
		for (Enquiry e: project.getEnquiries()) {
			if (e.getEnquiryID() == enquiryID) {
				e.setReply(reply);
				System.out.println("Reply sent successfully!");
				return;
			}
		}
		System.out.println("Enquiry not found!");
	}
	
	
	//Book flat on behalf of a successful applicant
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
		
		boolean flatEligibility = eligibilityChecker.isEligibleForFlat(applicant, flatType);
		if (!flatEligibility) {
			System.out.println("Applicant is not eligible for selected flat type!");
			return;
		}
		
		boolean flatAvailable = eligibilityChecker.isAvailableFlat(project, flatType);
		
		if (!flatAvailable) {
			System.out.println("Selected flat type is not avaialble!");
			return;
		}
		
		Flat flat1 = project.get_flat_1();
		Flat flat2 = project.get_flat_2();
		
		//Book the flat
		if (flatType.equalsIgnoreCase(flat1.get_type())) {
			flat1.set_number_of_flats(flat1.get_number_of_flats()-1);
		} else if (flatType.equalsIgnoreCase(flat2.get_type())) {
			flat2.set_number_of_flats(flat2.get_number_of_flats()-1);
		}
		
		//Set applicant's flat type and status
		applicant.setFlatType(flatType);
		applicant.setApplicationStatus("Booked");
		
		System.out.println("Flat booking successful for " + applicant.get_Name() + " with " + flatType + " flat!");
	} 
	
	//Generate receipt for with booking details
	public void generateReceipt(Project project, String applicantNRIC) {
		ReceiptGenerator receiptGenerator = new ReceiptGenerator();
		receiptGenerator.generateApplicantReceipt(project, this, applicantNRIC);
	}
	

}
