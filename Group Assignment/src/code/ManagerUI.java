package code;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code ManagerUI} class provides a user interface for HDB Managers to
 * manage housing projects and applicants. It allows managers to create, edit,
 * delete projects, handle enquiries, approve officers and applicants, and
 * generate reports.
 */
public class ManagerUI {
    /** The HDB Manager currently using the system. */
    private HDB_Manager manager;

    /** The list of all projects in the system. */
    private ArrayList<Project> projects;

    /** The list of all applicants in the system. */
    private ArrayList<Applicant> applicants;

    /** Scanner instance for user input. */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a new {@code ManagerUI} with the given manager, projects, and applicants.
     *
     * @param manager the HDB Manager using the interface
     * @param projects the list of all projects in the system
     * @param applicants the list of all applicants in the system
     */
    public ManagerUI(HDB_Manager manager, ArrayList<Project> projects, ArrayList<Applicant> applicants) {
        this.manager = manager;
        this.projects = projects;
        this.applicants = applicants;
    }

    /**
     * Displays the main menu for the manager and handles menu selection and execution.
     */
    public void showMenu() {
        System.out.println("Welcome " + manager.get_Name());
        int choice;
        do {
            System.out.println("\nHDB Manager Menu: ");
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
            System.out.println("11. Approve Applicant's Application Withdrawal");
            System.out.println("12. Generate Applicant Report");
            System.out.println("13. Change Password");
            System.out.println("14. Back");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1 -> createProject();
                case 2 -> editProject();
                case 3 -> deleteProject();
                case 4 -> toggleVisibility();
                case 5 -> viewAllProjects();
                case 6 -> viewMyProjects();
                case 7 -> viewEnquiries();
                case 8 -> replyEnquiry();
                case 9 -> approveOfficer();
                case 10 -> approveApplicant();
                case 11 -> approveWithdrawal();
                case 12 -> generateReport();
                case 13 -> changePassword();
                case 14 -> System.out.println("Exiting Page...\n");
                default -> System.out.println("Invalid choice! Please try again!\n");
            }
        } while (choice != 14);
    }

    /**
     * Allows the manager to change their password.
     */
    private void changePassword() {
        System.out.println("New Password: ");
        String newPassword1 = scanner.nextLine();

        System.out.println("Confirm New Password: ");
        String newPassword2 = scanner.nextLine();

        if (newPassword1.equals(newPassword2)) {
            manager.set_Password(newPassword1);
            System.out.println("Password reset successful!\n");
        } else {
            System.out.println("Password reset unsuccessful!\n");
        }
    }

    /**
     * Prompts the manager to enter details and creates a new project.
     */
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
        projects.add(newProject);
    }

    /**
     * Allows the manager to edit a selected project by specifying a field and new value.
     */
    private void editProject() {
        Project p = selectProject();
        if (p != null) {
            System.out.println("Possible fields: projectname, neighbourhood, type_1, type_2, nof1, nof2, sp1, sp2, open, close, officerslots");
            System.out.print("Enter field to edit: ");
            String field = scanner.nextLine();
            System.out.print("Enter new value: ");
            String value = scanner.nextLine();

            try {
                Object newValue = switch (field.toLowerCase()) {
                    case "nof1", "nof2", "sp1", "sp2", "officerslots" -> Integer.parseInt(value);
                    default -> value;
                };
                manager.editProject(p, field, newValue);
            } catch (Exception e) {
                System.out.println("Invalid input for field!\n");
            }
        }
    }

    /**
     * Allows the manager to delete a selected project.
     */
    private void deleteProject() {
        Project p = selectProject();
        if (p != null) {
            manager.deleteProjet(p);
            projects.remove(p);
        }
    }

    /**
     * Toggles the visibility of a selected project on or off.
     */
    private void toggleVisibility() {
        Project p = selectProject();
        if (p != null) {
            System.out.print("Enter visibility (on/off): ");
            String vis = scanner.nextLine();
            manager.toggleProjectVisibility(p, vis);
        } else {
            System.out.println("Toggle Failed!\n");
        }
    }

    /**
     * Displays all projects in the system.
     */
    private void viewAllProjects() {
        manager.viewAllProjects(projects);
    }

    /**
     * Displays projects that belong to the current manager.
     */
    private void viewMyProjects() {
        manager.viewMyProjects();
    }

    /**
     * Displays all enquiries across all projects.
     */
    private void viewEnquiries() {
        for (Project p : projects) {
            manager.viewEnquiries(p);
        }
    }

    /**
     * Allows the manager to reply to a specific enquiry for a selected project.
     */
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

    /**
     * Approves an officer’s registration for a selected project.
     */
    private void approveOfficer() {
        Project p = selectProject();
        if (p != null) {
            for (HDB_Officer officer : p.getRequestingOfficer()) {
                System.out.println("Officer NRIC: " + officer.get_Nric());
            }
            System.out.print("Enter Officer NRIC: ");
            String nric = scanner.nextLine();
            HDB_Officer officer = p.getRequestingOfficerByNRIC(nric);
            if (officer != null) {
                manager.approveOfficer(p, officer);
            } else {
                System.out.println("Officer not found in request list!\n");
            }
        }
    }

    /**
     * Approves an applicant’s flat application for a selected project.
     */
    private void approveApplicant() {
        Project p = selectProject();
        if (p != null) {
            for (Applicant applicant : p.getApplicants()) {
                System.out.println("Applicant NRIC: " + applicant.get_Nric());
            }
            System.out.print("Enter Applicant NRIC: ");
            String nric = scanner.nextLine();
            Applicant applicant = p.getApplicantByNRIC(nric);
            if (applicant != null) {
                System.out.print("Enter Flat Type: ");
                String flatType = scanner.nextLine();
                manager.approveApplicant(p, applicant, flatType);
            } else {
                System.out.println("Applicant not found!\n");
            }
        }
    }

    /**
     * Approves an applicant’s request to withdraw from the project application process.
     */
    private void approveWithdrawal() {
        System.out.print("Enter Applicant NRIC to approve withdrawal: ");
        String nric = scanner.nextLine();
        for (Applicant a : applicants) {
            if (a.get_Nric().equalsIgnoreCase(nric)) {
                manager.approveWithdrawal(a);
                return;
            }
        }
        System.out.println("Applicant not found!\n");
    }

    /**
     * Generates a report based on a specified category and filter value.
     */
    private void generateReport() {
        System.out.print("Enter filter category (flat type / project name / age / marital status): ");
        String category = scanner.nextLine();
        System.out.print("Enter filter value: ");
        String value = scanner.nextLine();
        manager.generateReport(applicants, category, value);
    }

    /**
     * Prompts the manager to select a project by name from the list of projects.
     *
     * @return the selected {@code Project} object, or {@code null} if not found
     */
    private Project selectProject() {
        this.viewAllProjects();
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
