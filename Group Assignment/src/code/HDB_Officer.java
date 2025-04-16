package code;

import java.time.LocalDate;
import java.time.format.*;
import java.util.ArrayList;

public class HDB_Officer extends Applicant {
	private ArrayList<Project> officerProjects;

	public HDB_Officer() {
		super();
		this.officerProjects = new ArrayList<>();
	}

	public HDB_Officer(String name, String NRIC, int age, String marital_status, String password) {
		super(name, NRIC, age, marital_status, password);
		this.officerProjects = new ArrayList<>();
	}
	
	//check eligibility for registering a project
	public boolean eligibleToRegister(Project project) {
		if (officerProjects.isEmpty()) return true;
		if (getAppliedProject().equals(project)) return false;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate newStart = LocalDate.parse(project.get_Application_opening_date(), formatter);
		LocalDate newEnd = LocalDate.parse(project.get_Application_closing_date(),formatter);
		
		for (Project pjk: officerProjects) {
			if (project == pjk) return false;
			LocalDate existingStart = LocalDate.parse(pjk.get_Application_opening_date(), formatter);
			LocalDate existingEnd = LocalDate.parse(pjk.get_Application_closing_date(),formatter);
			
			if (!(newEnd.isBefore(existingStart) || existingEnd.isBefore(newStart))) 
				return false;
			}
		
		return true;
	}
	
	//Requesting as officer for project
	public void requestAssignment(Project project) {
		if (!this.eligibleToRegister(project)) {
			System.out.println("Not eligible to register as officer for this project!");
			return;
		}
		
		if (project.get_officers().size() >= project.get_Officer_slot()) {
			System.out.println("Maximum number of officers for this project!");
            return;
        }
		
		project.addRequestingOfficer(null);
	}
	
	//assigning a project to the officer (For Manager Use)
	public void assignProject(Project project) {
		if (!this.eligibleToRegister(project)) {
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
	
	//Check applicant's eligibility for chosen flat type
	private boolean isEligibleForFlat(Applicant applicant, String flatType) {
		if (applicant.get_Marital_status().equalsIgnoreCase("Single") && applicant.get_Age() >= 35) {
			return flatType.equalsIgnoreCase("2-Room");
		} else if (applicant.get_Marital_status().equalsIgnoreCase("Married") && applicant.get_Age() >= 21) {
			return true;
		}
	
		return false;
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
		
		boolean flatEligibility = isEligibleForFlat(applicant, flatType);
		if (!flatEligibility) {
			System.out.println("Applicant is not eligible for selected flat type!");
			return;
		}
		
		boolean flatAvailable = false;
		String type_1 = project.get_Type_1();
		String type_2 = project.get_Type_2();
		
		//Check for availability
		if (flatType.equalsIgnoreCase(type_1) && project.get_Number_of_flats_1() > 0) {
			flatAvailable = true;
		} else if (flatType.equalsIgnoreCase(type_2) && project.get_Number_of_flats_2() > 0) {
			flatAvailable = true;
		}
		
		if (!flatAvailable) {
			System.out.println("Selected flat type is not avaialble!");
			return;
		}
		
		
		//Book the flat
		if (flatType.equalsIgnoreCase(type_1)) {
			project.set_Number_of_flats_1(project.get_Number_of_flats_1() - 1);
		} else if (flatType.equalsIgnoreCase(type_2)) {
			project.set_Number_of_flats_2(project.get_Number_of_flats_2() - 1);
		}
		
		//Set applicant's flat type and status
		applicant.setFlatType(flatType);
		applicant.setApplicationStatus("Booked");
		
		System.out.println("Flat booking successful for " + applicant.get_Name() + " with " + flatType + "flat!");
	} 
	
	//Generate receipt for with booking details
	public void generateReceipt(Project project, String applicantNRIC) {
		if (!isAssigned(project)) {
			System.out.println("You are not assigned to this project!");
			return;
		}
		
		Applicant applicant = project.getApplicantByNRIC(applicantNRIC);
		if (applicant == null) {
			System.out.println("Applicant not found!");
			return;
		}
		
		if (!applicant.getApplicationStatus().equalsIgnoreCase("Booked")) {
			System.out.println("Applicant has not booked a flat yet!");
			return;
		}
		
		System.out.println("Receipt for Applicant: " + applicant.get_Name());
		System.out.println("NRIC: " + applicant.get_Nric());
	    System.out.println("Age: " + applicant.get_Age());
	    System.out.println("Marital Status: " + applicant.get_Marital_status());
	    System.out.println("Flat Type Booked: " + applicant.getFlatType());
	    System.out.println("Project Details: ");
	    System.out.println(project);
	}
	

}
