package code;

/**
 * This class represents an enquiry made by an applicant for a project. 
 * It includes the enquiry ID, applicant NRIC, project name, the enquiry text,
 * and the reply to the enquiry.
 */
public class Enquiry {
    
    // Static counter for generating unique enquiry IDs
    private static int idCounter = 1;

    private int enquiryID;          // Unique ID for the enquiry
    private String applicantNRIC;   // NRIC of the applicant making the enquiry
    private String projectName;     // Name of the project related to the enquiry
    private String enquiryText;     // The content of the enquiry
    private String reply;           // The reply to the enquiry, if any

    /**
     * Constructor to create an enquiry object.
     * 
     * @param applicantNRIC The NRIC of the applicant making the enquiry.
     * @param projectName The name of the project related to the enquiry.
     * @param enquiryText The content of the enquiry.
     */
    public Enquiry(String applicantNRIC, String projectName, String enquiryText) {
        this.enquiryID = idCounter++;   // Assign a unique enquiry ID
