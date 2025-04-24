package code;

/**
 * This class is responsible for generating a receipt for an applicant
 * who has successfully booked a flat in an HDB project.
 */
public class ReceiptGenerator {

    /**
     * Generates a receipt for an applicant associated with a specific project,
     * if the officer is assigned to the project and the applicant has booked a flat.
     *
     * @param project The project in which the flat is booked.
     * @param officer The officer generating the receipt.
     * @param applicantNRIC The NRIC of the applicant for whom the receipt is to be generated.
     */
    public void generateApplicantReceipt(Project project, HDB_Officer officer, String applicantNRIC) {
        // Check if the officer is assigned to the project
        if (!officer.isAssigned(project)) {
            System.out.println("You are not assigned to this project!");
            return;
        }

        // Retrieve the applicant from the project by NRIC
        Applicant applicant = project.getApplicantByNRIC(applicantNRIC);
        if (applicant == null) {
            System.out.println("Applicant not found!");
            return;
        }

        // Ensure that the applicant has booked a flat
        if (!applicant.getApplicationStatus().equalsIgnoreCase("Booked")) {
            System.out.println("Applicant has not booked a flat yet!");
            return;
        }

        // Print the receipt details
        System.out.println("Receipt for Applicant: " + applicant.get_Name());
        System.out.println("NRIC: " + applicant.get_Nric());
        System.out.println("Age: " + applicant.get_Age());
        System.out.println("Marital Status: " + applicant.get_Marital_status());
        System.out.println("Flat Type Booked: " + applicant.getFlatType());
        System.out.println("Project Details: ");
        System.out.println(project);
    }
}
