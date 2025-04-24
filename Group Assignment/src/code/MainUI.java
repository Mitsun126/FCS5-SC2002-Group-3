package code;

import java.util.*;

public class MainUI {
	private static ArrayList<Applicant> applicants = new ArrayList<>();
	private static ArrayList<HDB_Officer> officers = new ArrayList<>();
	private static ArrayList<HDB_Manager> managers = new ArrayList<>();
	private static ArrayList<Project> projects = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);
	private static FileIO fileio = new FileIO();
	//I need help with filter! map<NRIC, filter>
	//private static Map<String, String> userProjectFilters = new HashMap<>();

	public static void main(String[] args) {
		applicants = fileio.loadApplicants("data/ApplicantList.csv");
		officers = fileio.loadOfficers("data/OfficerList.csv");
		managers = fileio.loadManagers("data/ManagerList.csv");
		projects = fileio.loadProjects("data/ProjectList.csv", officers);
				
		int choice = -1;
		do {
			try {
				System.out.println("Main Menu: ");
				System.out.println("1. Login");
		        System.out.println("2. Exit");
		        System.out.print("Enter your choice: ");
		        choice = Integer.parseInt(scanner.nextLine());
		        
		        switch(choice) {
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
	                    System.out.println("Invalid choice! Please try again!");
		        }
			} catch (NumberFormatException e) {
				System.out.println("Invalid type! Please try again!");
			} catch (Exception e) {
				System.out.println("An unexpected error occured!");
				e.printStackTrace();
			}
		}while(choice!=2);
		

	}
	
	private static void login() {
		System.out.print("Enter NRIC: ");
        String nric = scanner.nextLine().trim().toUpperCase();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        for (Applicant a : applicants) {
            if (a.get_Nric().equalsIgnoreCase(nric) && a.get_Password().equals(password)) {
                System.out.println("Login successful as Applicant.");
                new ApplicantUI(a, projects).showMenu();
                return;
            }
        }

        for (HDB_Officer o : officers) {
            if (o.get_Nric().equalsIgnoreCase(nric) && o.get_Password().equals(password)) {
                System.out.println("Login successful as HDB Officer.");
                new OfficerUI(o, projects).showMenu();
                return;
            }
        }

        for (HDB_Manager m : managers) {
            if (m.get_Nric().equalsIgnoreCase(nric) && m.get_Password().equals(password)) {
                System.out.println("Login successful as HDB Manager.");
                new ManagerUI(m, projects, applicants).showMenu();
                return;
            }
        }

        System.out.println("Login failed! Check your NRIC or password!");
    }
	

}
