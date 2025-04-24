package code;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the user interface for an HDB Officer.
 * It allows the officer to manage projects, handle applicant-related functions,
 * and perform various actions like replying to enquiries, booking flats, and generating receipts.
 */
public class OfficerUI {
    private HDB_Officer officer;
    private ArrayList<Project> projects;
    private Scanner scanner = new Scanner(System.in);
    private ApplicantUI applicantUI;

    /**
     * Constructs an OfficerUI instance with a given officer and list of projects.
     *
     * @param officer The HDB officer using the interface.
     * @param projects The list of all HDB projects.
     */
    public OfficerUI(HDB_Officer officer, ArrayList<Project> projects) {
        this.officer = officer;
        this.projects = projects;
        this.applicantUI = new ApplicantUI(officer, projects);
    }

    /**
     * Displays the main menu for the officer with options to access officer functions,
     * applicant functions, change password, or go back.
     */
    public void showMenu() {
        System.out.println("Welcome " + officer.get_Name());
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

            switch (choice) {
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
        } while (choice != 4);
    }

    /**
     * Displays the menu for officer-specific functionalities.
     */
    public void showOfficerFunctions() {
        int choice;
        do {
            System.out.println("\nOfficer Functionalities: ");
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

            switch (choice) {
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
        } while (choice != 8);
    }

    /**
     * Prompts the officer to change their password by entering and confirming a new password.
     */
    private void changePassword() {
        System.out.println("New Password: ");
        String newPassword1 = scanner.nextLine();

        System.out.println("Confirm New Password: ");
        String newPassword2 = scanner.nextLine();

        if (newPassword1.equals(newPassword2)) {
            officer.set_Password(newPassword1);
            System.out.println("Password reset successful!\n");
        } else {
            System.out.println("Password reset unsuccessful!\n");
        }
    }

    /**
     * Displays all projects that are currently visible.
     */
    private void viewAllAvailableProjects() {
        System.out.println("All Available Projects: ");
        for (Project project : projects) {
            if (project.get_visibility())
                System.out.println(project);
        }
    }

    /**
     * Allows the officer to register themselves for a selected project.
     */
    private void registerForProject() {
        Project project = this.selectProject();

        if (project != null) {
            officer.requestAssignment(project);
        } else {
            System.out.println("Invalid Project!\n");
        }
    }

    /**
     * Displays enquiries related to a specific project selected by the officer.
     */
    private void viewProjectEnquiries() {
        Project project = this.selectProject();

        if (project != null) {
            officer.viewEnquiries(project);
        } else {
            System.out.println("Invalid Project!\n");
        }
    }

    /**
     * Allows the officer to reply to an enquiry from a selected project.
     */
    private void replyToEnquiry() {
        Project project = this.selectProject();

        if (project != null) {
            System.out.println("Enter EnquiryID to reply: ");
            int enquiryID = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter Reply to Enquiry: ");
            String reply = scanner.nextLine();

            officer.replyToEnquiry(project, enquiryID, reply);
        } else {
            System.out.println("Invalid Project!\n");
        }
    }

    /**
     * Books a flat in a selected project for a specified applicant and flat type.
     */
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

    /**
     * Generates a receipt for an applicant associated with a selected project.
     */
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

    /**
     * Prompts the officer to enter a project name and returns the corresponding project object.
     *
     * @return The selected Project object, or null if not found.
     */
    private Project selectProject() {
        this.viewAllAvailableProjects();
        System.out.println("Enter Project Name: ");
        String projectName = scanner.nextLine();

        for (Project p : projects) {
            if (p.get_ProjectName().equalsIgnoreCase(projectName)) {
                return p;
            }
        }

        return null;
    }
}
