package code;

import java.io.*;
import java.util.*;

public class MainUI {
	private static ArrayList<Applicant> applicants = new ArrayList<>();
	private static ArrayList<HDB_Officer> officers = new ArrayList<>();
	private static ArrayList<HDB_Manager> managers = new ArrayList<>();
	private static ArrayList<Project> projects = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);
	//I need help with filter! map<NRIC, filter>
	private static Map<String, String> userProjectFilters = new HashMap<>();

	public static void main(String[] args) {
		loadApplicants();
		loadOfficers();
		loadManagers();
		loadProjects();
		
		int choice;
		do {
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
	        		break;
                default:
                    System.out.println("Invalid choice! Please try again.");
	        }
		} while(choice!=2);

	}
	
	private static void login() {
		System.out.print("Enter NRIC: ");
        String nric = scanner.nextLine().trim().toUpperCase();
        System.out.print("Enter password: ");
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
	
	
	private static void loadApplicants() {
		try (BufferedReader br = new BufferedReader(new FileReader("/Project/ApplicantList.csv"))){
			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				applicants.add(new Applicant(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]));
			}
			System.out.println("Applicants load successfully!");
		} catch (IOException e) {
			System.out.println("Failed to load Applicants!");
		}
	}
	
	private static void loadOfficers() {
		try (BufferedReader br = new BufferedReader(new FileReader("/Project/OfficerList.csv"))){
			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				officers.add(new HDB_Officer(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]));
			}
			System.out.println("Officers load successfully!");
			
		} catch (IOException e) {
			System.out.println("Failed to load Officers!");
		}
	}
	
	private static void loadManagers() {
		try (BufferedReader br = new BufferedReader(new FileReader("/Project/ManagerList.csv"))){
			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				managers.add(new HDB_Manager(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]));
			}
			System.out.println("Managers load successfully!");
			
		} catch (IOException e) {
			System.out.println("Failed to load Managers!");
		}
	}
	
	private static void loadProjects() {
        try (BufferedReader br = new BufferedReader(new FileReader("ProjectList.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                Project p = new Project(
                    tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]),
                    tokens[5], Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]),
                    tokens[8], tokens[9], tokens[10], Integer.parseInt(tokens[11])
                );
                String[] assignedOfficers = tokens[12].split(",");
                
                for (int i=0; i < assignedOfficers.length; i++) {
                	for (HDB_Officer o: officers) {
                		if (o.get_Name().equalsIgnoreCase(assignedOfficers[i])){
                			p.add_officer(o);
                			break;
                		}
                	}
                }
                projects.add(p);
            }
        } catch (IOException e) {
            System.out.println("Failed to load projects.");
        }
    }

}
