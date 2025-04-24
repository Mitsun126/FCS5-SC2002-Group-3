package code;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class provides the user interface for the applicant. It allows the applicant to
 * interact with available projects, submit enquiries, apply for projects, view application
 * status, and manage enquiries.
 */
public class ApplicantUI {
    
    private Applicant applicant;  // The current applicant using the UI
    private Scanner scanner = new Scanner(System.in);  // Scanner object for reading user input
    private ArrayList<Project> projects;  // List of available projects for the applicant

    /**
     * Constructor for ApplicantUI class. Initializes the applicant and projects list.
     * 
     * @param applicant The applicant using this UI.
     * @param projects List of available projects for the applicant to interact with.
     */
    public ApplicantUI(Applicant applicant, ArrayList<Project> projects) {
        this.applicant = applicant;
        this.projects = projects;
    }
    
    /**
     * Displays the applicant's menu and handles user interaction through various options.
     * The menu allows applicants to view projects, apply for projects, submit and manage enquiries,
     * change passwords, and request withdrawal.
     */
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
            
            // Handle user choice and call corresponding methods
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
        } while(choice != 10);  // Continue showing the menu until the user exits
    }
    
    /**
     * Allows the applicant to change their password.
     * The applicant must enter and confirm the new password.
     */
    private void changePassword() {
        System.out.println("New Password: ");
        String newPassword1 = scanner.nextLine();
        
        System.out.println("Confirm New Password: ");
        String newPassword2 = scanner.nextLine();
        
        // Validate the new password
        if (newPassword1.equals(newPassword2)) {
            applicant.set_Password(newPassword1);
            System.out.println("Password reset successful!\n");
            return;
        }
        
        System.out.println("Password reset unsuccessful!\n");
    }
    
    /**
     * Displays available projects that the applicant can apply for.
     * It filters the list to only show open projects.
     */
    private void viewProjects() {
        System.out.println("Viewing Available Projects: ");
        ArrayList<Project> openProjects = applicant.viewOpenProjects(projects);
        
        if (openProjects.isEmpty()) {
            System.out.println("No Projects Available!\n");
        } else {
            for (Project p: openProjects) {
                System.out.println(p);  // Display each open project
            }
        }
    }
    
    /**
     * Allows the applicant to apply for a project by entering its name.
     * The method validates the project name and processes the application.
     */
    private void applyForProject() {
        System.out.println("Enter Project Name to apply for: ");
        String projectName = scanner.nextLine();
        
        // Get the project by name from the list of projects
        Project project = null;
        for (Project p: projects) {
            if (p.get_ProjectName().equalsIgnoreCase(projectName)) {
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
    
    /**
     * Displays the current status of the applicant's application.
     */
    private void viewApplicationStatus() {
        System.out.println("Viewing Your Applied Project...");
        applicant.viewAppliedProject();
    }
    
    /**
     * Allows the applicant to submit an enquiry to a project.
     * The applicant provides the project name and enquiry message.
     */
    private void submitEnquiry() {
        System.out.println("Enter the Project Name: ");
        String projectName = scanner.nextLine();
        
        // Find the project by name
        Project project = null;
        for (Project p : projects) {
            if (p.get_ProjectName().equalsIgnoreCase(projectName)) {
                project = p;
                break;
            }
        }
        
        if (project != null) {
            System.out.println("Enter your Enquiry Message: ");
            String message = scanner.nextLine();
            applicant.submitEnquiry(project, message);  // Submit the enquiry
        } else {
            System.out.println("Invalid Project Name!\n");
        }
    }
    
    /**
     * Displays all enquiries submitted by the applicant.
     */
    private void viewEnquiries() {
        System.out.println("Viewing Your Enquiries: ");
        applicant.viewEnquiries();
    }
    
    /**
     * Allows the applicant to edit an existing enquiry.
     * The applicant provides the enquiry ID and the new message.
     */
    private void editEnquiry() {
        System.out.println("Enter Enquiry ID to edit: ");
        int enquiryID = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter New Enquiry Message: ");
        String newMessage = scanner.nextLine();
        
        applicant.editEnquiry(enquiryID, newMessage);  // Edit the enquiry with the new message
    }
    
    /**
     * Allows the applicant to delete an existing enquiry by entering its ID.
     * The applicant must provide the project name and enquiry ID.
     */
    private void deleteEnquiry() {
        System.out.print("Enter Project Name: ");
        String projectName = scanner.nextLine();
        
        // Find the project by name
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
        
        applicant.deleteEnquiry(enquiryID, project);  // Delete the specified enquiry
    }
    
    /**
     * Allows the applicant to request the withdrawal of their application for a project.
     */
    private void requestWithdrawApplication() {
        applicant.requestWithdrawal();
        System.out.println("Requested for Withdrawal!\n");
    }
}
