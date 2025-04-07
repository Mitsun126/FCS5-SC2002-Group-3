package code;

import java.util.ArrayList;

public class Applicant extends User {
	private Project appliedProject;
	private String applicationStatus;
	private ArrayList<Enquiry> enquiries;
	private String flatType;
	private boolean withdrawal_status;
	
	
	public Applicant() {
		super();
		this.appliedProject = null;
		this.applicationStatus = "";
		this.enquiries = new ArrayList<>();
		this.withdrawal_status = false;
	}

	public Applicant(String name, String NRIC, int age, String marital_status) {
		super(name, NRIC, age, marital_status);
		this.appliedProject = null;
		this.applicationStatus = "";
		this.enquiries = new ArrayList<>();
		this.withdrawal_status = false;
	}
	
	//Check eligibility based on martial status and age
	private boolean isEligible(Project project) {
		if (this.get_Marital_status().equalsIgnoreCase("Single") && this.get_Age() >= 35) {
			return project.get_Type_1().equalsIgnoreCase("2-Room") || project.get_Type_2().equalsIgnoreCase("2-Room");
		} else if (this.get_Marital_status().equalsIgnoreCase("Married") && this.get_Age() >= 21) {
			return true;
		}
		
		return false;
	}
	
	//View a list of open projects the applicant is eligible for
	public ArrayList<Project> viewOpenProjects(ArrayList<Project> projects){
		ArrayList<Project> openProjects = new ArrayList<>();
		for (Project project: projects) {
			if (project.get_visibility() && isEligible(project)) {
				openProjects.add(project);
			}
		}
		
		return openProjects;
	}
	
	//appliedProject get/set
	public Project getAppliedProject() {return appliedProject;}
	public void applyForProject(Project project) {
		if (appliedProject != null) {
			System.out.println("You have already applied for a project: " + appliedProject.get_ProjectName());
			return;
		}
		
		if (!isEligible(project)) {
			System.out.println("You are not eligible to apply for this project!");
			return;
		}
		
		appliedProject = project;
		applicationStatus = "Pending";
		System.out.println("Application submitted for this project!");
	}
	
	//applicationStatus get/set
	public String getApplicationStatus() {return applicationStatus;}
	public void setApplicationStatus(String status) {this.applicationStatus = status;}
	
	//View applied project
	public void viewAppliedProject() {
		if (appliedProject == null) {
			System.out.println("You have not applied for any project!");
			return;
		}
		else {
			System.out.println("Project: " + appliedProject.get_ProjectName());
			System.out.println("Status: " + applicationStatus);
		}
	}
	
	//Get withdrawl_status
	public boolean getWithdrawalStatus() {return withdrawal_status;}
	
	//Requesting for withdrawal
	public void requestWithdrawal() {
		withdrawal_status = true;
	}
	
	//Withdrawal of application (For manager to use)
	public boolean withdrawApplication() {
		if (appliedProject == null) {
			System.out.println("Applicant not applied for any project!");
			return false;
		} else if (withdrawal_status  == false) {
			System.out.println("Applicant did not request for withdrawal!");
			return false;
		}
		
		appliedProject = null;
		applicationStatus = "";
		withdrawal_status = false;
		System.out.println("Application withdrawn successfully!");
		return true;
	}
	
	
	//Submitting enquiry
	public void submitEnquiry(String message) {
		if (appliedProject == null) {
			System.out.println("You must apply for a project first!");
			return;
		}
		
		Enquiry newEnquiry = new Enquiry(this.get_Nric(), appliedProject.get_ProjectName(), message);
		enquiries.add(newEnquiry);
		appliedProject.add_Enquiry(newEnquiry);
		System.out.println("Enquiry Submitted Successfully!");
	}
	
	//View all submitted enquiries
	public void viewEnquiries() {
		if (enquiries.isEmpty()) {
			System.out.println("No Enquiries Found!");
			return;
		}
		
		for (Enquiry e: enquiries) {
			System.out.println(e);
		}
	}
	
	//Edit an enquiry by its ID
	public void editEnquiry(int enquiryID, String newMessage) {
		for (Enquiry e: enquiries) {
			if (e.getEnquiryID() == enquiryID) {
				e.setEnquiryText(newMessage);
				System.out.println("Enquiry Updated!");
				return;
			}
		}
		System.out.println("Enquiry Not Found!");
	}
	
	//Deleting an enquiry by its ID
	public void deleteEnquiry(int enquiryID) {
		for (int i = 0; i< enquiries.size(); i++) {
			if (enquiries.get(i).getEnquiryID()==enquiryID) {
				enquiries.remove(i);
				System.out.println("Enquiry Deleted Successfully!");
				return;
			}
		}
		System.out.println("Enquiry Not Found!");
	}
	
	//Flat type get/set
	public void setFlatType(String flatType) {this.flatType = flatType;}
    public String getFlatType() {return flatType;}
	
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("Applicant Name: ").append(this.get_Name()).append("\n")
        .append("NRIC: ").append(this.get_Nric()).append("\n")
        .append("Age: ").append(this.get_Age()).append("\n")
        .append("Marital Status: ").append(this.get_Marital_status()).append("\n");

      if (appliedProject != null) {
          sb.append("Flat Type Booked: ").append(flatType).append("\n")
            .append("Applied Project: ").append(appliedProject.get_ProjectName()).append("\n")
            .append("Application Status: ").append(applicationStatus).append("\n");
      } else {
          sb.append("No flat booked yet.\n");
      }

      return sb.toString();
    }

}
