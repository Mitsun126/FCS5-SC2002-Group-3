package code;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class is responsible for checking the eligibility of applicants and officers
 * for various actions related to projects and flats. It includes methods for validating 
 * applicant eligibility based on marital status, age, and project requirements, 
 * as well as checking the availability of flats and officer registration conditions.
 */
public class EligibilityChecker {

    /**
     * Checks if an applicant is eligible for a project based on marital status and age.
     * If the applicant is single and at least 35 years old, they are eligible only 
     * for 2-room flats. If the applicant is married and at least 21 years old, 
     * they are eligible for any flat type.
     *
     * @param applicant The applicant whose eligibility is being checked.
     * @param project The project for which the eligibility is being checked.
     * @return true if the applicant is eligible for the project, false otherwise.
     */
    public boolean isEligibleForProjectApplicant(Applicant applicant, Project project) {
        if (applicant.get_Marital_status().equalsIgnoreCase("Single") && applicant.get_Age() >= 35) {
            return project.get_flat_1().get_type().equalsIgnoreCase("2-Room") || project.get_flat_2().get_type().equalsIgnoreCase("2-Room");
        } else if (applicant.get_Marital_status().equalsIgnoreCase("Married") && applicant.get_Age() >= 21) {
            return true;
        }

        return false;
    }

    /**
     * Checks if an applicant is eligible for a specific flat type based on their marital status and age.
     * If the applicant is single and at least 35 years old, they are only eligible for 2-room flats.
     * If the applicant is married and at least 21 years old, they are eligible for any flat type.
     *
     * @param applicant The applicant whose eligibility is being checked.
     * @param flatType The type of flat the applicant is considering.
     * @return true if the applicant is eligible for the flat type, false otherwise.
     */
    public boolean isEligibleForFlat(Applicant applicant, String flatType) {
        if (applicant.get_Marital_status().equalsIgnoreCase("Single") && applicant.get_Age() >= 35) {
            return flatType.equalsIgnoreCase("2-Room");
        } else if (applicant.get_Marital_status().equalsIgnoreCase("Married") && applicant.get_Age() >= 21) {
            return true;
        }

        return false;
    }

    /**
     * Checks if a specified flat type is available in the project.
     * The method checks the availability of flats based on the flat type and 
     * the number of flats remaining for that type.
     *
     * @param project The project where the flat availability is being checked.
     * @param flatType The type of flat being checked for availability.
     * @return true if the flat type is available, false otherwise.
     */
    public boolean isAvailableFlat(Project project, String flatType) {
        boolean flatAvailable = false;
        Flat flat1 = project.get_flat_1();
        Flat flat2 = project.get_flat_2();
        String type_1 = flat1.get_type();
        String type_2 = flat2.get_type();

        // Check for availability
        if (flatType.equalsIgnoreCase(type_1) && flat1.get_number_of_flats() > 0) {
            flatAvailable = true;
        } else if (flatType.equalsIgnoreCase(type_2) && flat2.get_number_of_flats() > 0) {
            flatAvailable = true;
        }

        return flatAvailable;
    }

    /**
     * Checks if an officer is eligible to register for a specific project.
     * The officer is eligible if they are not already assigned to the project 
     * and if their current assignments do not overlap with the new project’s application dates.
     *
     * @param officer The officer whose eligibility is being checked.
     * @param project The project for which the officer's eligibility is being checked.
     * @return true if the officer is eligible to register for the project, false otherwise.
     */
    public boolean isEligibleToRegisterOfficer(HDB_Officer officer, Project project) {
        if (officer.get_officerProjects().isEmpty()) return true;
        if (officer.getAppliedProject().equals(project)) return false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate newStart = LocalDate.parse(project.get_Application_opening_date(), formatter);
        LocalDate newEnd = LocalDate.parse(project.get_Application_closing_date(), formatter);

        // Check for date overlaps with officer's current projects
        for (Project pjk : officer.get_officerProjects()) {
            if (project == pjk) return false;
            LocalDate existingStart = LocalDate.parse(pjk.get_Application_opening_date(), formatter);
            LocalDate existingEnd = LocalDate.parse(pjk.get_Application_closing_date(), formatter);

            if (!(newEnd.isBefore(existingStart) || existingEnd.isBefore(newStart)))
                return false;
        }

        return true;
    }
}
