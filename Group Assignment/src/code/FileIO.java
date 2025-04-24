package code;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileIO {
	
	public ArrayList<Applicant> loadApplicants(String filename) {
		ArrayList<Applicant> applicants = new ArrayList<>();
		try(Scanner sc = new Scanner(new File(filename))){
			if (sc.hasNextLine()) sc.nextLine();
			while (sc.hasNextLine()) {
				String[] tokens = sc.nextLine().split(",");
				applicants.add(new Applicant(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]));
			}
			System.out.println("Applicants loaded successfully!");
		} catch (Exception e) {
			System.out.println("Failed to load Applicants!");
			e.printStackTrace();
		}
		
		return applicants;
	}
	
	public ArrayList<HDB_Officer> loadOfficers(String filename) {
		ArrayList<HDB_Officer> officers = new ArrayList<>();
		try(Scanner sc = new Scanner(new File(filename))){
			if (sc.hasNextLine()) sc.nextLine();
			while (sc.hasNextLine()) {
				String[] tokens = sc.nextLine().split(",");
				officers.add(new HDB_Officer(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]));
			}
			System.out.println("Officers loaded successfully!");
			
		} catch (IOException e) {
			System.out.println("Failed to load Officers!");
		}
		
		return officers;
	}
	
	public ArrayList<HDB_Manager> loadManagers(String filename) {
		ArrayList<HDB_Manager> managers = new ArrayList<>();
		try(Scanner sc = new Scanner(new File(filename))){
			if (sc.hasNextLine()) sc.nextLine();
			while (sc.hasNextLine()) {
				String[] tokens = sc.nextLine().split(",");
				managers.add(new HDB_Manager(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]));
			}
			System.out.println("Managers loaded successfully!");
			
		} catch (IOException e) {
			System.out.println("Failed to load Managers!");
		}
		
		return managers;
	}
	
	public ArrayList<Project> loadProjects(String filename, ArrayList<HDB_Officer> officers) {
		ArrayList<Project> projects = new ArrayList<>();
		try(Scanner sc = new Scanner(new File(filename))){
			if (sc.hasNextLine()) sc.nextLine();
			while (sc.hasNextLine()) {
				String[] tokens = sc.nextLine().split(",");
                Project p = new Project(
                    tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]),
                    tokens[5], Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]),
                    tokens[8], tokens[9], tokens[10], Integer.parseInt(tokens[11])
                );
                
                for (int j = 12; j<tokens.length;j++) {
                	String name = tokens[j].trim();
                	String cleanName = name.replace("\"", "").trim();
                	for (HDB_Officer o : officers) {
                		if (o.get_Name().equalsIgnoreCase(cleanName)) {
                			p.add_officer(o);
                			break;
                		}
                	}
                }
                
                projects.add(p);
            }
			System.out.println("Projects loaded successfully!");
			
        } catch (IOException e) {
            System.out.println("Failed to load projects!");
        }
        
        return projects;
    }
	
	public void saveApplicant(String fileName, ArrayList<Applicant> applicants) {
		try (PrintWriter writer = new PrintWriter(new File(fileName))){
			writer.println("Name,NRIC,Age,Marital Status,Password");
			for (Applicant a : applicants) {
				writer.printf("%s,%s,%d,%s,%s\n", a.get_Name(), a.get_Nric(), a.get_Age(), a.get_Marital_status(), a.get_Password());
			}
			System.out.println("Applicants saved to CSV!");
		} catch (Exception e) {
			System.out.println("Error saving Applicants to CSV!");
			e.printStackTrace();
		}
	}
	
	public void saveOfficer(String fileName, ArrayList<HDB_Officer> officers) {
		try (PrintWriter writer = new PrintWriter(new File(fileName))){
			writer.println("Name,NRIC,Age,Marital Status,Password");
			for (HDB_Officer o : officers) {
				writer.printf("%s,%s,%d,%s,%s\n", o.get_Name(), o.get_Nric(), o.get_Age(), o.get_Marital_status(), o.get_Password());
			}
			System.out.println("Officers saved to CSV!");
		} catch (Exception e) {
			System.out.println("Error saving Officer to CSV!");
			e.printStackTrace();
		}
	}
	
	public void saveManager(String fileName, ArrayList<HDB_Manager> managers) {
		try (PrintWriter writer = new PrintWriter(new File(fileName))){
			writer.println("Name,NRIC,Age,Marital Status,Password");
			for (HDB_Manager m : managers) {
				writer.printf("%s,%s,%d,%s,%s\n", m.get_Name(), m.get_Nric(), m.get_Age(), m.get_Marital_status(), m.get_Password());
			}
			System.out.println("Managers saved to CSV!");
		} catch (Exception e) {
			System.out.println("Error saving Managers to CSV!");
			e.printStackTrace();
		}
	}
	
	public void saveProject(String fileName, ArrayList<Project> projects) {
		try (PrintWriter writer = new PrintWriter(new File(fileName))){
			writer.println("Project Name,Neighborhood,Type 1,Number of units for Type 1,Selling price for Type 1,Type 2,Number of units for Type 2,Selling price for Type 2,Application opening date,Application closing date,Manager,Officer Slot,Officer");
			for (Project p : projects) {
				Flat flat_1 = p.get_flat_1();
				Flat flat_2 = p.get_flat_2();
				ArrayList<HDB_Officer> officers = p.get_officers();
				
				String officerNames = officers.stream()
						.map(HDB_Officer::get_Name)
						.collect(Collectors.joining(","));
				
				officerNames = "\"" + officerNames.replace("\"", "\"\"") + "\"";
				
				writer.printf("%s,%s,%s,%d,%d,%s,%d,%d,%s,%s,%s,%d,%s\n", p.get_ProjectName(), p.get_Neighbourhood(),flat_1.get_type(), flat_1.get_number_of_flats(), flat_1.get_sellingPrice()
						,flat_2.get_type(),flat_2.get_number_of_flats(), flat_2.get_sellingPrice(),p.get_Application_opening_date(),p.get_Application_closing_date(),p.get_HDB_Manager()
						,p.get_Officer_slot(), officerNames);
			}
			System.out.println("Projects saved to CSV!");
		} catch (Exception e) {
			System.out.println("Error saving Projects to CSV!");
			e.printStackTrace();
		}
	}
	
}
