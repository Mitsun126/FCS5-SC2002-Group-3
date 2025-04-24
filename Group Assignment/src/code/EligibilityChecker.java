package code;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class is responsible for checking the eligibility of applicants and officers
 * for different project-related conditions, such as applying for projects, registering for flats,
 * and officer project assignments.
 */
public class EligibilityChecker {
    
    /**
     * Checks if an applicant is eligible to apply for a project based on marital status, age,
     * and the type of flat available in the project.
     * 
     * @param applicant The applicant for whom the eligibility is being checked.
     * @param project The project for which the eligibility is being checked.
     * @return true if the applicant is eligible, false otherwise.
     */
    public boolean isEligibleForProjectApplicant(Applicant applicant, Project project) {
        // Check for single applicants aged 35 or older
        if (applicant.get_Marital_status().equalsIgnoreCase("Single") && applicant.get_Age() >= 35) {
            // Eligible if 2-room flat is available in the project
            return project.get_flat_1().get_type().equalsIgnoreCase("2-Room") || 
                   project.get_flat_2().get_type().equalsIgnoreCase("2-Room");
        } 
        // Check for married applicants aged 21 or older
        else if (applicant.get_Marital_status().equalsIgnoreCase("Married") && applicant.get_Age() >= 21) {
            return true;  // Always eligible for married applicants 21 and older
        }
        
        return false;  // Not eligible for other cases
    }
    
    /**
     * Checks if an applicant is eligible to register for a specific type of flat.
     * 
     * @param applicant The applicant whose eligibility is being checked.
     * @param flatType The type of flat for which the eligibility is being checked.
     * @return true if the applicant is eligible for the flat, false otherwise.
     */
    public boolean isEligibleForFlat(Applicant applicant, String flatType) {
        // Check for single applicants aged 35 or older and 2-room flat eligibility
        if (applicant.get_Marital_status().equalsIgnoreCase("Single") && applicant.get_Age() >= 35) {
            return flatType.equalsIgnoreCase("2-Room");
        } 
        // Check for married applicants aged 21 or older (eligible for all flat types)
        else if (applicant.get_Marital_status().equalsIgnoreCase("Married") && applicant.get_Age() >= 21) {
            return true;
        }
    
        return false;  // Not eligible for other cases
    }
    
    /**
     * Checks the availability of a specific type of flat in a project.
     * 
     * @param project The project where the flat availability is being checked.
     * @param flatType The type of flat for which the availability is being checked.
     * @return true if the flat is available, false otherwise.
     */
    public boolean isAvailableFlat(Project project, String flatType) {
        boolean flatAvailable = false;
        Flat flat1 = project.get_flat_1();
        Flat flat2 = project.get_flat_2();
        String type_1 = flat1.get_type();
        String type_2 = flat2.get_type();
        
        // Check for availability in flat1
        if (flatType.equalsIgnoreCase(type_1) && flat1.get_number_of_flats() > 0) {
            flatAvailable = true;
        } 
        // Check for availability in flat2
        else if (flatType.equalsIgnoreCase(type_2) && flat2.get_number_of_flats() > 0) {
            flatAvailable = true;
        }
        
        return flatAvailable;  // Return whether the flat is available or not
    }
    
    /**
     * Checks if an officer is eligible to register for a project based on their current assignments
     * and the project's application dates.
     * 
     * @param officer The officer whose eligibility is being checked.
     * @param project The project to which the officer may be assigned.
     * @return true if the officer is eligible to register for the project, false otherwise.
     */
    public boolean isEligibleToRegisterOfficer(HDB_Officer officer, Project project) {
        // Officer is eligible if they have no current assignments
        if (officer.get_officerProjects().isEmpty()) return true;
        
        // Officer is not eligible if they are already assigned to the project
        if (officer.getAppliedProject().equals(project)) return false;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate newStart = LocalDate.parse(project.get_Application_opening_date(), formatter);
        LocalDate newEnd = LocalDate.parse(project.get_Application_closing_date(), formatter);
        
        // Check for date conflicts with existing officer projects
        for
