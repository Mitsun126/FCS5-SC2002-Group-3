package code;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EligibilityChecker {
	
	//check eligibility for a project for applicants
	public boolean isEligibleForProjectApplicant(Applicant applicant, Project project) {
		if (applicant.get_Marital_status().equalsIgnoreCase("Single") && applicant.get_Age() >= 35) {
			return project.get_flat_1().get_type().equalsIgnoreCase("2-Room") || project.get_flat_2().get_type().equalsIgnoreCase("2-Room");
		} else if (applicant.get_Marital_status().equalsIgnoreCase("Married") && applicant.get_Age() >= 21) {
			return true;
		}
		
		return false;
	}
	
	//check eligibility for flat for applicants
	public boolean isEligibleForFlat(Applicant applicant, String flatType) {
		if (applicant.get_Marital_status().equalsIgnoreCase("Single") && applicant.get_Age() >= 35) {
			return flatType.equalsIgnoreCase("2-Room");
		} else if (applicant.get_Marital_status().equalsIgnoreCase("Married") && applicant.get_Age() >= 21) {
			return true;
		}
	
		return false;
	}
	
	//check for flat availability
	public boolean isAvailableFlat(Project project, String flatType) {
		boolean flatAvailable = false;
		Flat flat1 = project.get_flat_1();
		Flat flat2 = project.get_flat_2();
		String type_1 = flat1.get_type();
		String type_2 = flat2.get_type();
		
		//Check for availability
		if (flatType.equalsIgnoreCase(type_1) && flat1.get_number_of_flats() > 0) {
			flatAvailable = true;
		} else if (flatType.equalsIgnoreCase(type_2) && flat2.get_number_of_flats() > 0) {
			flatAvailable = true;
		}
		
		return flatAvailable;
	}
	
	//check eligibility to register for a project as officer
	public boolean isEligibleToRegisterOfficer(HDB_Officer officer, Project project) {
		if (officer.get_officerProjects().isEmpty()) return true;
		if (officer.getAppliedProject().equals(project)) return false;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate newStart = LocalDate.parse(project.get_Application_opening_date(), formatter);
		LocalDate newEnd = LocalDate.parse(project.get_Application_closing_date(),formatter);
		
		for (Project pjk: officer.get_officerProjects()) {
			if (project == pjk) return false;
			LocalDate existingStart = LocalDate.parse(pjk.get_Application_opening_date(), formatter);
			LocalDate existingEnd = LocalDate.parse(pjk.get_Application_closing_date(),formatter);
			
			if (!(newEnd.isBefore(existingStart) || existingEnd.isBefore(newStart))) 
				return false;
			}
		
		return true;
	}
}
