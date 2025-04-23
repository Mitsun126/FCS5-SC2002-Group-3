package code;

import java.util.ArrayList;

public class HDB_Manager extends User {
	private ArrayList<Project> createdProjects;
	private EligibilityChecker eligibilityChecker = new EligibilityChecker();

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
        
        case "type_1": project.get_flat_1().set_type((String) newValue);break;
        case "nof1": project.get_flat_1().set_number_of_flats((Integer) newValue); break;
        case "sp1": project.get_flat_1().set_sellingPrice((Integer) newValue); break;
        case "type_2": project.get_flat_2().set_type((String) newValue);break;
        case "nof2": project.get_flat_2().set_number_of_flats((Integer) newValue); break;
        case "sp2": project.get_flat_2().set_sellingPrice((Integer) newValue); break;
        
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
	}
	
	
	//Set applicant status to successful
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
		ReportGenerator reportGenerator = new ReportGenerator();
		reportGenerator.generateApplicantReport(applicants, filterCategory, filterValue);
	}

		
}
