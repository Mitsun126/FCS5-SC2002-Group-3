package code;

public class Enquiry {
	private static int idCounter = 1;
	private int enquiryID;
	private String applicantNRIC;
	private String projectName;
	private String enquiryText;
	private String reply;
	
	public Enquiry(String applicantNRIC, String projectName, String enquiryText) {
		this.enquiryID = idCounter++;
		this.applicantNRIC = applicantNRIC;
		this.projectName = projectName;
		this.enquiryText = enquiryText;
		this.reply = "";
	}
	
	//enquiryID get
	public int getEnquiryID() {return enquiryID;}
	
	//applicantNRIC get
	public String getApplicantNRIC() {return applicantNRIC;}
	
	//projectName get
	public String getProjectName() {return projectName;}
	
	//enquiryTect get/set
	public String getEnquiryText() {return enquiryText;}
	public void setEnquiryText(String enquiryText) {this.enquiryText = enquiryText;}
	
	//reply get/set
	public String getReply() {return reply;}
	public void setReply(String reply) {this.reply = reply;}
	
	//printing details
	public String toString() {
		return "EnquiryID: " + enquiryID + "\nProject: " + projectName + "\nEnquiry: " + enquiryText + "\nReply: " + (reply.isEmpty() ? "No Reply": reply);
	}
	
}
