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
        this.applicantNRIC = applicantNRIC;
        this.projectName = projectName;
        this.enquiryText = enquiryText;
        this.reply = "";   // No reply initially
    }
    
    /**
     * Gets the unique ID of the enquiry.
     * 
     * @return The enquiry ID.
     */
    public int getEnquiryID() {
        return enquiryID;
    }

    /**
     * Gets the NRIC of the applicant who made the enquiry.
     * 
     * @return The NRIC of the applicant.
     */
    public String getApplicantNRIC() {
        return applicantNRIC;
    }

    /**
     * Gets the name of the project related to the enquiry.
     * 
     * @return The project name.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Gets the content of the enquiry.
     * 
     * @return The enquiry text.
     */
    public String getEnquiryText() {
        return enquiryText;
    }

    /**
     * Sets the content of the enquiry.
     * 
     * @param enquiryText The new content of the enquiry.
     */
    public void setEnquiryText(String enquiryText) {
        this.enquiryText = enquiryText;
    }

    /**
     * Gets the reply to the enquiry.
     * 
     * @return The reply to the enquiry, or an empty string if no reply has been given.
     */
    public String getReply() {
        return reply;
    }

    /**
     * Sets the reply to the enquiry.
     * 
     * @param reply The reply to the enquiry.
     */
    public void setReply(String reply) {
        this.reply = reply;
    }

    /**
     * Returns a string representation of the enquiry, including its ID, project name, 
     * enquiry text, and reply. If no reply has been given, it displays "No Reply".
     * 
     * @return A string representation of the enquiry.
     */
    @Override
    public String toString() {
        return "EnquiryID: " + enquiryID + "\nProject: " + projectName + 
               "\nEnquiry: " + enquiryText + "\nReply: " + (reply.isEmpty() ? "No Reply" : reply);
    }
}
