package code;

import java.util.*;

/**
 * The {@code MainUI} class serves as the main entry point for the HDB Application System.
 * It handles the loading and saving of users and projects, manages user login,
 * and delegates control to appropriate UI classes based on user roles
 * (Applicant, HDB Officer, HDB Manager).
 */
public class MainUI {

    /** List of all registered applicants. */
    private static ArrayList<Applicant> applicants = new ArrayList<>();

    /** List of all registered HDB officers. */
    private static ArrayList<HDB_Officer> officers = new ArrayList<>();

    /** List of all registered HDB managers. */
    private static ArrayList<HDB_Manager> managers = new ArrayList<>();

    /** List of all projects available in the system. */
    private static ArrayList<Project> projects = new ArrayList<>();

    /** Scanner instance for reading user input. */
    private static Scanner scanner = new Scanner(System.in);

    /** Handles file operations for loading and saving system data. */
    private static FileIO fileio = new FileIO();

    // Future use: Map to store project filter preferences for each user based on NRIC.
    // private static Map<String, String> userProjectFilters = new HashMap<>();

    /**
     * Main method which serves as the starting point of the program.
     * Loads all data from CSV files, displays the main menu, handles login and system exit.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Load persisted data
        applicants = fileio.loadApplicants("data/ApplicantList.csv");
        officers = fileio.loadOfficers("data/OfficerList.csv");
        managers = fileio.loadManagers("data/ManagerList.csv");
        projects = fileio.loadProjects("data/ProjectList.csv", officers, managers);

        int choice = -1;
        do {
            try {
                System.out.println("\nMain Menu: ");
				System.out.println("1. Login");
		        System.out.println("2. Exit");
		        System.out.print("Enter your choice: ");
		        choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        System.out.println("Exiting System...");
                        fileio.saveApplicant("data/ApplicantList.csv", applicants);
                        fileio.saveOfficer("data/OfficerList.csv", officers);
                        fileio.saveManager("data/ManagerList.csv", managers);
                        fileio.saveProject("data/ProjectList.csv", projects);
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again!\n");
		        }
			} catch (NumberFormatException e) {
				System.out.println("Invalid type! Please try again!\n");
			} catch (Exception e) {
				System.out.println("An unexpected error occured!\n");
				e.printStackTrace();
            }
        } while (choice != 2);
    }

    /**
     * Handles login for all users (Applicants, Officers, Managers).
     * Verifies NRIC and password, and launches the appropriate user interface
     * based on the user's role.
     */
    private static void login() {
        System.out.print("Enter NRIC: ");
        String nric = scanner.nextLine().trim().toUpperCase();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        for (Applicant a : applicants) {
            if (a.get_Nric().equalsIgnoreCase(nric) && a.get_Password().equals(password)) {
                System.out.println("Login successful as Applicant!\n");
                new ApplicantUI(a, projects).showMenu();
                return;
            }
        }

        for (HDB_Officer o : officers) {
            if (o.get_Nric().equalsIgnoreCase(nric) && o.get_Password().equals(password)) {
                System.out.println("Login successful as HDB Officer!\n");
                new OfficerUI(o, projects).showMenu();
                return;
            }
        }

        for (HDB_Manager m : managers) {
            if (m.get_Nric().equalsIgnoreCase(nric) && m.get_Password().equals(password)) {
                System.out.println("Login successful as HDB Manager!\n");
                new ManagerUI(m, projects, applicants).showMenu();
                return;
            }
        }

        System.out.println("Login failed! Check your NRIC or password!");
    }

}
