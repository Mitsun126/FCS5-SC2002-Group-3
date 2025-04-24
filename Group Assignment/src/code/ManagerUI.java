package code;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerUI {
	private HDB_Manager manager;
	private ArrayList<Project> projects;
	private ArrayList<Applicant> applicants;
	private Scanner scanner = new Scanner(System.in);

	public ManagerUI(HDB_Manager manager, ArrayList<Project> projects, ArrayList<Applicant> applicants) {
		this.manager = manager;
		this.projects = projects;
		this.applicants = applicants;
	}
	
	public void showMenu() {
		int choice;
		do {
			System.out.println("HDB Manager Menu: ");
			System.out.println("1. Create Project");
	        System.out.println("2. Edit Project");
	        System.out.println("3. Delete Project");
	        System.out.println("4. Toggle Project Visibility");
	        System.out.println("5. View All Projects");
	        System.out.println("6. View My Projects");
	        System.out.println("7. View All Enquiries");
	        System.out.println("8. Reply Project Enquiry");
	        System.out.println("9. Approve Officer Registration");
	        System.out.println("10. Approve Applicant's Flat Application");
	        System.out.println("11. Approve Applicant's Applicantion Withdrawal");
	        System.out.println("12. Generate Applicant Report");
	        System.out.println("13. Change Password");
	        System.out.println("14. Back");
	        
	        System.out.print("Enter your choice: ");
	        choice = scanner.nextInt();
	        scanner.nextLine();
	        
	        switch(choice) {
		        case 1:
		        	createProject();
		        	break;
	        	case 2:
	        		editProject();
	        		break;
	        	case 3:
	        		deleteProject();
                    break;
                case 4:
                    toggleVisibility();
                    break;
                case 5:
                	viewAllProjects();
                    break;
                case 6:
                	viewMyProjects();
                    break;
                case 7:
                	viewEnquiries();
            		break;
                case 8:
                    replyEnquiry();
                    break;
                case 9:
                	approveOfficer();
                    break;
                case 10:
                	approveApplicant();
                    break;
                case 11:
                	approveWithdrawal();
                    break;
                case 12:
                	generateReport();
                    break;
                case 13:
                	changePassword();
                	break;
                case 14:
                	System.out.println("Exiting Page...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again!");
	        }
		} while(choice!=14);
	}
	
	private void changePassword() {
		System.out.println("New Password: ");
		String newPassword1 = scanner.nextLine();
		
		System.out.println("Confirm New Password: ");
		String newPassword2 = scanner.nextLine();
		
		if (newPassword1.equals(newPassword2)) {
			manager.set_Password(newPassword1);
			System.out.println("Password reset successful!");
			return;
		}
		
		System.out.println("Password reset unsuccessful!");
	}
	
	private void createProject() {
		System.out.print("Project Name: ");
        String pn = scanner.nextLine();
        System.out.print("Neighbourhood: ");
        String nh = scanner.nextLine();
        System.out.print("Type 1 (e.g., 2-Room): ");
        String t1 = scanner.nextLine();
        System.out.print("No. of Type 1 Flats: ");
        int n1 = Integer.parseInt(scanner.nextLine());
        System.out.print("Selling Price 1: ");
        int s1 = Integer.parseInt(scanner.nextLine());
        System.out.print("Type 2 (e.g., 3-Room): ");
        String t2 = scanner.nextLine();
        System.out.print("No. of Type 2 Flats: ");
        int n2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Selling Price 2: ");
        int s2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Application Opening Date: ");
        String aod = scanner.nextLine();
        System.out.print("Application Closing Date: ");
        String acd = scanner.nextLine();
        System.out.print("Officer Slots (max 10): ");
        int slots = Integer.parseInt(scanner.nextLine());

        Project newProject = manager.createProject(pn, nh, t1, n1, s1, t2, n2, s2, aod, acd, slots);
        //Adding new project into overall project list
        projects.add(newProject);
	}
	
	private void editProject() {
		Project p = selectProject();
		if (p != null) {
			System.out.print("Enter field to edit: ");
			String field = scanner.nextLine();
			System.out.print("Enter new value: ");
			String value = scanner.nextLine();
		
		
			//changing type to integer for specific field
			try {
				Object newValue = switch(field.toLowerCase()) {
					case "nof1", "nof2", "sp1", "sp2", "officerslots"-> Integer.parseInt(value);
					default-> value;
				};
				manager.editProject(p, field, newValue);
				
			} catch(Exception e) {
				System.out.println("Invalid input for field.");
			}
		}
	}
	
	private void deleteProject() {
		Project p = selectProject();
		if (p != null) {
            manager.deleteProjet(p);
            projects.remove(p);
        }
	}
	
	private void toggleVisibility() {
		Project p = selectProject();
		if (p != null) {
			System.out.print("Enter visibility (on/off): ");
            String vis = scanner.nextLine();
            manager.toggleProjectVisibility(p, vis);
		}
		else {
			System.out.println("Toggled Failed!");
		}
	}
	
	private void viewAllProjects() {
		manager.viewAllProjects(projects);
	}
	
	private void viewMyProjects() {
		manager.viewMyProjects();
	}
	
	private void viewEnquiries() {
		for (Project p : projects) {
			manager.viewEnquiries(p);
		}
	}
	
	private void replyEnquiry() {
		Project p = selectProject();
		if (p != null) {
			manager.viewEnquiries(p);
			System.out.print("Enter Enquiry ID to reply: ");
			int id = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter Reply to Enquiry: ");
			String reply = scanner.nextLine();
			manager.replyToEnquiry(p, id, reply);
		}
	}
	
	private void approveOfficer() {
		Project p = selectProject();
		if (p != null) {
			for (HDB_Officer officer: p.getRequestingOfficer()) {
				System.out.println("Officer NRIC: " + officer.get_Nric());
			}
			System.out.print("Enter Officer NRIC: ");
			String nric = scanner.nextLine();
			HDB_Officer officer = p.getRequestingOfficerByNRIC(nric);
			if (officer != null) {
				manager.approveOfficer(p, officer);
				return;
			}
			System.out.println("Officer not found in request list!");
		}
	}
	
	private void approveApplicant() {
		Project p = selectProject();
		if (p != null) {
			for (Applicant applicant: p.getApplicants()) {
				System.out.println("Applicant NRIC: " + applicant.get_Nric());
			}
			System.out.print("Enter Applicant NRIC: ");
			String nric = scanner.nextLine();
			Applicant applicant = p.getApplicantByNRIC(nric);
			if (applicant != null) {
				System.out.print("Enter Flat Type: ");
	            String flatType = scanner.nextLine();
				manager.approveApplicant(p, applicant, flatType);
				return;
			}
			System.out.println("Applicant not found!");
		}
	}
	
	private void approveWithdrawal() {
		System.out.print("Enter Applicant NRIC to approve withdrawal: ");
		String nric = scanner.nextLine();
		for (Applicant a : applicants) {
            if (a.get_Nric().equalsIgnoreCase(nric)) {
                manager.approveWithdrawal(a);
                return;
            }
        }
        System.out.println("Applicant not found!");
	}
	
	private void generateReport() {
		System.out.print("Enter filter category (flat type / project name / age / marital status): ");
        String category = scanner.nextLine();
        System.out.print("Enter filter value: ");
        String value = scanner.nextLine();
        manager.generateReport(applicants, category, value);
	}
	
	//getting a specific project by name
	private Project selectProject() {
		this.viewAllProjects();
		System.out.println("Enter Project Name: ");
		String projectName = scanner.nextLine();
		
		Project project = null;
		for (Project p:projects) {
			if (p.get_ProjectName().equalsIgnoreCase(projectName)){
				project = p;
				break;
			}
		}
		
		return project;
	}

}
