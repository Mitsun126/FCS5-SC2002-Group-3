package code;

import java.util.Scanner;
import java.util.ArrayList;

public class ApplicantUI {
	private Applicant applicant;
	private Scanner scanner = new Scanner(System.in);
	private ArrayList<Project> projects;

	public ApplicantUI(Applicant applicant, ArrayList<Project> projects) {
		this.applicant = applicant;
		this.projects = projects;
	}
	
	public void showMenu() {
		System.out.println("Welcome " + applicant.get_Name());
		int choice;
		do {
			System.out.println("\nApplicant Menu: ");
			System.out.println("1. View Available Projects");
	        System.out.println("2. Apply for Project");
	        System.out.println("3. View My Application Status");
	        System.out.println("4. Submit Enquiry");
	        System.out.println("5. View My Enquiries");
	        System.out.println("6. Edit Enquiry");
	        System.out.println("7. Delete Enquiry");
	        System.out.println("8. Request for Withdrawal of Application");
	        System.out.println("9. Change Password");
	        System.out.println("10. Back");
	        
	        System.out.print("Enter your choice: ");
	        choice = scanner.nextInt();
	        scanner.nextLine();
	        
	        switch(choice) {
		        case 1:
		        	viewProjects();
		        	break;
	        	case 2:
	        		applyForProject();
	        		break;
	        	case 3:
                    viewApplicationStatus();
                    break;
                case 4:
                    submitEnquiry();
                    break;
                case 5:
                	viewEnquiries();
            		break;
                case 6:
                    editEnquiry();
                    break;
                case 7:
                    deleteEnquiry();
                    break;
                case 8:
                    requestWithdrawApplication();
                    break;
                case 9:
                	changePassword();
                	break;
                case 10:
                    System.out.println("Exiting Page...\n");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again!\n");
	        }
		} while(choice!=10);
	}
	
	private void changePassword() {
		System.out.println("New Password: ");
		String newPassword1 = scanner.nextLine();
		
		System.out.println("Confirm New Password: ");
		String newPassword2 = scanner.nextLine();
		
		if (newPassword1.equals(newPassword2)) {
			applicant.set_Password(newPassword1);
			System.out.println("Password reset successful!\n");
			return;
		}
		
		System.out.println("Password reset unsuccessful!\n");
	}
	
	private void viewProjects() {
		System.out.println("Viewing Available Projects: ");
		ArrayList<Project> openProjects = applicant.viewOpenProjects(projects);
		
		if (openProjects.isEmpty()) {
			System.out.println("No Projects Available!\n");
		}
		else {
			for (Project p: openProjects) System.out.println(p);
		}
	}
	
	private void applyForProject() {
		System.out.println("Enter Project Name to apply for: ");
		String projectName = scanner.nextLine();
		
		//getting project by name
		Project project = null;
		for (Project p:projects) {
			if (p.get_ProjectName().equalsIgnoreCase(projectName)){
				project = p;
				break;
			}
		}
		
		if (project != null) {
			applicant.applyForProject(project);
		} else {
			System.out.println("Invalid Project!\n");
		}
	}
	
	private void viewApplicationStatus() {
		System.out.println("Viewing Your Applied Project...");
        applicant.viewAppliedProject();
	}
	
	private void submitEnquiry() {
		System.out.println("Enter the Project Name: ");
		String projectName = scanner.nextLine();
		
		Project project = null;
		for (Project p : projects) {
			if (p.get_ProjectName().equalsIgnoreCase(projectName)) {
				project = p;
				break;
			}
		}
		
		if (project != null) {
			System.out.println("Enter you Enquiry Message: ");
			String message = scanner.nextLine();
			
			applicant.submitEnquiry(project, message);
		}
		
		System.out.println("Invalid Project Name!\n");
	}
	
	private void viewEnquiries() {
		System.out.println("Viewing Your Enquiries: ");
		applicant.viewEnquiries();
	}
	
	private void editEnquiry() {
		System.out.println("Enter Enquiry ID to edit: ");
		int enquiryID = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Enter New Enquiry Message: ");
		String newMessage = scanner.nextLine();
		
		applicant.editEnquiry(enquiryID, newMessage);
	}
	
	private void deleteEnquiry() {
		System.out.print("Enter Project Name: ");
		String projectName = scanner.nextLine();
		
		Project project = null;
		for (Project p : projects) {
			if (p.get_ProjectName().equalsIgnoreCase(projectName)) {
				project = p;
				break;
			}
		}
		
		System.out.print("Enter Enquiry ID to delete: ");
		int enquiryID = scanner.nextInt();
        scanner.nextLine();
        
        
        
        applicant.deleteEnquiry(enquiryID, project);
	}
	
	private void requestWithdrawApplication() {
		applicant.requestWithdrawal();
		System.out.println("Requested for Withdrawal!\n");
	}

}
