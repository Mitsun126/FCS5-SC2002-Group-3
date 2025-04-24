package code;

public class ReceiptGenerator {
	public void generateApplicantReceipt(Project project, HDB_Officer officer, String applicantNRIC) {
		if (!officer.isAssigned(project)) {
			System.out.println("You are not assigned to this project!");
			return;
		}
		
		Applicant applicant = project.getApplicantByNRIC(applicantNRIC);
		if (applicant == null) {
			System.out.println("Applicant not found!");
			return;
		}
		
		if (!applicant.getApplicationStatus().equalsIgnoreCase("Booked")) {
			System.out.println("Applicant has not booked a flat yet!");
			return;
		}
		
		System.out.println("Receipt for Applicant: " + applicant.get_Name());
		System.out.println("NRIC: " + applicant.get_Nric());
	    System.out.println("Age: " + applicant.get_Age());
	    System.out.println("Marital Status: " + applicant.get_Marital_status());
	    System.out.println("Flat Type Booked: " + applicant.getFlatType());
	    System.out.println("Project Details: ");
	    System.out.println(project);
	}
}
