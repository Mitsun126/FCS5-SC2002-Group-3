package code;

import java.util.ArrayList;

public class HDB_Manager extends User {
	private ArrayList<Project> createdProjects;

	public HDB_Manager() {
		super();
		this.createdProjects = new ArrayList<>();
	}

	public HDB_Manager(String name, String NRIC, int age, String marital_status,String password) {
		super(name, NRIC, age, marital_status, password);
		this.createdProjects = new ArrayList<>();
	}
	
	public Project createProject(String projectName, String neighbourhood, String t1, int nof1, int sp1, String t2, int nof2, int sp2, String aod, String acd, int noo) {
		Project newProject = new Project(projectName, neighbourhood, t1, nof1, sp1, t2, nof2, sp2, aod, acd, this.get_Name(), noo);
		createdProjects.add(newProject);
		System.out.println("Project created Successfully!");
		return newProject;
	}

	public void editProject(Project project, String field, Object newValue) {
		switch(field.toLowerCase()) {
		case "projectname": project.set_ProjectName((String) newValue); break;
        case "neighbourhood": project.set_Neighbourhood((String) newValue); break;
        case "type_1": project.set_Type_1((String) newValue); break;
        case "nof1": project.set_Number_of_flats_1((Integer) newValue); break;
        case "sp1": project.set_SellingPrice1((Integer) newValue); break;
        case "type_2": project.set_Type_2((String) newValue); break;
        case "nof2": project.set_Number_of_flats_2((Integer) newValue); break;
        case "sp2": project.set_SellingPrice2((Integer) newValue); break;
        case "open": project.set_Application_opening_date((String) newValue); break;
        case "close": project.set_Application_closing_date((String) newValue); break;
        case "officerslots": project.set_Officer_slot((Integer) newValue); break;
        default: System.out.println("Invalid field!");
		}
		
		System.out.println("Project edited successfully!");
		
	}
	
	public void deleteProjet(Project project) {
		createdProjects.remove(project);
		System.out.println("Project deleted successfully!");
	}
	
	public void viewAllProjects(ArrayList<Project> allProjects){
		for (Project p: allProjects) {
			System.out.println(p);
		}
	}
	
	public ArrayList<Project> viewMyProjects(){
		for (Project p: createdProjects) {
			System.out.println(p);
		}
		return createdProjects;
	}
	
	public void toggleProjectVisibility(Project project, String visibility) {
		project.set_visibility(visibility);
	}
	
	//Viewing all enquires in a project
	public void viewEnquiries(Project project) {
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
		if (!createdProjects.contains(project)) {
			System.out.println("You are not handling this project!");
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
	
	public void approveOfficer(Project project, HDB_Officer officer) {
		officer.assignProject(project);
		project.removeRequestingOfficer(officer);
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
	
	//Check for flat availability
	private boolean isAvailableForFlat(Project project, String flatType) {
		boolean flatAvailable = false;
		String type_1 = project.get_Type_1();
		String type_2 = project.get_Type_2();
		
		//Check for availability
		if (flatType.equalsIgnoreCase(type_1) && project.get_Number_of_flats_1() > 0) {
			flatAvailable = true;
		} else if (flatType.equalsIgnoreCase(type_2) && project.get_Number_of_flats_2() > 0) {
			flatAvailable = true;
		}
		
		return flatAvailable;
	}
	
	//Set applicant status to successful
	public void approveApplicant(Project project, Applicant applicant, String flatType) {
		boolean isEligible = isEligibleForFlat(applicant, flatType);
		boolean isAvailable = isAvailableForFlat(project, flatType);
		
		if (isEligible && isAvailable) {
			System.out.println("BTO application successful!");
			applicant.setApplicationStatus("Successful");
			return;
		} 
		
		System.out.println("BTO application unsuccessful!");
		applicant.setApplicationStatus("Unsuccessful");
		project.removeApplicant(applicant);
	}
	
	//Approve withdrawal of applicant
	public void approveWithdrawal(Applicant applicant) {
		boolean success = applicant.withdrawApplication();	
		Project project = applicant.getAppliedProject();
		
		if (success == true) {
			System.out.println("Withdrawal approved!");
			project.removeApplicant(applicant);
			return;
		}
		
		System.out.println("Withdrawal rejected!");
	}
	
	
	//Generate report
	public void generateReport(ArrayList<Applicant> applicants, String filterCategory, String filterValue) {
		for (Applicant a: applicants) {
			boolean matches = false;
			
			switch (filterCategory.toLowerCase()) {
			case "flat type":
				matches = a.getFlatType() != null && a.getFlatType().equalsIgnoreCase(filterValue);
				break;
				
			case "project name":
				matches = a.getAppliedProject() != null && a.getAppliedProject().get_ProjectName().equalsIgnoreCase(filterValue);
				break;
				
			case "age":
				try {
					matches = a.get_Age() >= Integer.parseInt(filterValue);
				} catch (NumberFormatException e) {
					System.out.println("Invalid age filter value!");
				}
				break;
				
			case "marital status":
				matches = a.get_Marital_status().equalsIgnoreCase(filterValue);
				break;
			
			default:
				System.out.println("Unknown filter category!");
			}
			
			if (matches) {
				System.out.println("Flat Type: " + a.getFlatType() + ", Project Name: " + (a.getAppliedProject() != null ? a.getAppliedProject().get_ProjectName() : "N/A") + ", Age: " + a.get_Age() + ", Marital Status: " + a.get_Marital_status());
            }
		}
	}

		
}
