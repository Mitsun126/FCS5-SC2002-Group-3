package code;

import java.time.LocalDate;
import java.time.format.*;
import java.util.ArrayList;

public class HDB_Officer extends User {
	private ArrayList<Project> projects;

	public HDB_Officer() {
		super();
		this.projects = new ArrayList<>();
	}

	public HDB_Officer(String name, String NRIC, int age, String marital_status) {
		super(name, NRIC, age, marital_status);
		this.projects = new ArrayList<>();
	}
	
	//check eligibility for registering a project
	public boolean eligibleToRegister(Project project) {
		if (projects.isEmpty()) return true;
		
		for (Project pjk: projects) {
			if (project == pjk) {return false;}
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			LocalDate start1 = LocalDate.parse(pjk.get_Application_opening_date(), formatter);
			LocalDate end1 = LocalDate.parse(pjk.get_Application_closing_date(),formatter);
			
			LocalDate start2 = LocalDate.parse(project.get_Application_opening_date(), formatter);
			LocalDate end2 = LocalDate.parse(project.get_Application_closing_date(),formatter);
			
			
			if (!(end1.isBefore(start2) || end2.isBefore(start1))) 
				{return false;}
			}
			
		
		return true;
	}
	
	
	//assigning a project to the officer
	public void assignProject(Project project) {
		if (!this.eligibleToRegister(project)) {
			System.out.println("Cannot be assigned due to overlapping project dates.");
			return;
		}
		
		if (project.get_officers().size() >= project.get_Officer_slot()) {
			System.out.println("Maximum number of officers for this project.");
            return;
	        }
		
		projects.add(project);
        project.add_officer(this);
        System.out.println("Officer assigned successfully to project: "+ project.get_ProjectName());
	}
	
	//If project found in projects
	public boolean assigned(Project project) {
		return projects.contains(project);
	}
	
	//Viewing all enquires in a project
	public void viewEnquiries(Project project) {
		if (!this.assigned(project)) {
			System.out.println("You are not assigned to this project.");
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
		if (!this.assigned(project)) {
			System.out.println("You are not assigned to this project.");
			return;
		}
		
		for (Enquiry e: project.getEnquiries()) {
			if (e.getEnquiryID() == enquiryID) {
				e.setReply(reply);
				System.out.println("Reply sent successfully!");
				return;
			}
		}
		System.out.println("Enquiry not found.");
	}
	

}
