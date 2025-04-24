package code;

import java.util.ArrayList;

public class ReportGenerator {

	public void generateApplicantReport(ArrayList<Applicant> applicants, String filterCategory, String filterValue) {
		for (Applicant a: applicants) {
			boolean matches = false;
			
			switch (filterCategory.toLowerCase()) {
			case "flat type":
				matches = a.getFlatType() != null && a.getFlatType().equalsIgnoreCase(filterValue);
				break;
				
			case "project name":
				matches = a.getAppliedProject() != null && a.getAppliedProject().get_ProjectName().equalsIgnoreCase(filterValue);
				break;
				
			case "age":
				try {
					matches = a.get_Age() >= Integer.parseInt(filterValue);
				} catch (NumberFormatException e) {
					System.out.println("Invalid age filter value!");
				}
				break;
				
			case "marital status":
				matches = a.get_Marital_status().equalsIgnoreCase(filterValue);
				break;
			
			default:
				System.out.println("Unknown filter category!");
			}
			
			if (matches) {
				System.out.println("_________________________________");
				System.out.println("Name: " + a.get_Name());
                System.out.println("NRIC: " + a.get_Nric());
                System.out.println("Age: " + a.get_Age());
                System.out.println("Marital Status: " + a.get_Marital_status());
                System.out.println("Flat Type: " + a.getFlatType());
                System.out.println("Project Name: " + (a.getAppliedProject() != null ? a.getAppliedProject().get_ProjectName() : "N/A"));
            }
		}
	}

}
