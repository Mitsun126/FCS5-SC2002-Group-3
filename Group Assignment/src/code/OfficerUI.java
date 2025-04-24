package code;

import java.util.ArrayList;
import java.util.Scanner;

public class OfficerUI {
	private HDB_Officer officer;
	private ArrayList<Project> projects;
	private Scanner scanner = new Scanner(System.in);
	private ApplicantUI applicantUI;

	public OfficerUI(HDB_Officer officer, ArrayList<Project> projects) {
		this.officer = officer;
		this.projects = projects;
		this.applicantUI = new ApplicantUI(officer, projects);
	}
	
	public void showMenu() {
		System.out.println("Welcome "+ officer.get_Name());
		int choice;
		do {
			System.out.println("\nHDB Officer Menu: ");
			System.out.println("1. Officer Functions");
	        System.out.println("2. Applicant Functions");
	        System.out.println("3. Change Password");
	        System.out.println("4. Back");
	        System.out.print("Enter your choice: ");
	        choice = scanner.nextInt();
	        scanner.nextLine();
	        
	        switch(choice) {
		        case 1:
		        	showOfficerFunctions();
		        	break;
	        	case 2:
	        		applicantUI.showMenu();
	        		break;
	        	case 3:
	        		changePassword();
	        		break;
	        	case 4:
                    System.out.println("Exiting Page...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again!\n");
	        }
		} while(choice!=4);
	}
	
	public void showOfficerFunctions() {
		int choice;
		do {
			System.out.println("Officer Functionalities: ");
			System.out.println("1. View All Available Projects");
			System.out.println("2. Register for Project");
            System.out.println("3. View Registered Projects");
            System.out.println("4. View Enquiries in a Project");
            System.out.println("5. Reply to Enquiry");
            System.out.println("6. Book Flat for Applicant");
            System.out.println("7. Generate Applicant Receipt");
            System.out.println("8. Back");
            
	        System.out.print("Enter your choice: ");
	        choice = scanner.nextInt();
	        scanner.nextLine();
	        
	        switch(choice) {
		        case 1:
		        	viewAllAvailableProjects();
		        	break;
	        	case 2:
	        		registerForProject();
	        		break;
	        	case 3:
                    officer.viewProjectDetails();
                    break;
                case 4:
                    viewProjectEnquiries();
                    break;
                case 5:
                	replyToEnquiry();
            		break;
                case 6:
                	bookingFlat();
                    break;
                case 7:
                    generatingReceipt();
                    break;
                case 8:
                    System.out.println("Exiting Page...\n");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again!\n");
	        }
		} while(choice!=8);
	}

	private void changePassword() {
		System.out.println("New Password: ");
		String newPassword1 = scanner.nextLine();
		
		System.out.println("Confirm New Password: ");
		String newPassword2 = scanner.nextLine();
		
		if (newPassword1.equals(newPassword2)) {
			officer.set_Password(newPassword1);
			System.out.println("Password reset successful!\n");
			return;
		}
		
		System.out.println("Password reset unsuccessful!\n");
	}
	
	private void viewAllAvailableProjects() {
		System.out.println("All Available Projects: ");
        for (Project project : projects) {
        	if (project.get_visibility())
        		System.out.println(project);
        }
	}
	
	private void registerForProject() {
		Project project = this.selectProject();
		
		if (project != null) {
			officer.requestAssignment(project);
		} else {
			System.out.println("Invalid Project!\n");
		}
	}
	
	
	private void viewProjectEnquiries() {
		Project project = this.selectProject();
		
		if (project != null) {
			officer.viewEnquiries(project);
		} else {
			System.out.println("Invalid Project!\n");
		}
	}
	
	private void replyToEnquiry() {
		Project project = this.selectProject();
		
		if (project != null) {
			System.out.println("Enter EnquiryID to reply: ");
			int EnquiryID = scanner.nextInt();
			scanner.nextLine();
			
			System.out.println("Enter Reply to Enquiry: ");
			String reply = scanner.nextLine();
			
			officer.replyToEnquiry(project, EnquiryID, reply);
		} else {
			System.out.println("Invalid Project!\n");
		}
		
	}
	
	private void bookingFlat() {
		Project project = this.selectProject();
		
		if (project != null) {
			System.out.println("Enter Applicant NRIC: ");
			String applicantNric = scanner.nextLine();
			
			System.out.println("Enter Flat Type: ");
			String flatType = scanner.nextLine();
			
			officer.bookFlat(project, applicantNric, flatType);
		} else {
			System.out.println("Invalid Project!\n");
		}

	}
	
	private void generatingReceipt() {
		Project project = this.selectProject();
		
		if (project != null) {
			System.out.println("Enter Applicant NRIC: ");
			String applicantNric = scanner.nextLine();
			
			officer.generateReceipt(project, applicantNric);
		} else {
			System.out.println("Invalid Project!\n");
		}

	}
	
	
	//getting a specific project by name
	private Project selectProject() {
		this.viewAllAvailableProjects();
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
