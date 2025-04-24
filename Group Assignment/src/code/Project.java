package code;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Project {
	private String projectName;
	private String neighbourhood;
	private Flat flat_1;
	private Flat flat_2;
	private String application_opening_date;
	private String application_closing_date;
	private String HDB_Manager;
	private int officer_slot;
	private ArrayList<HDB_Officer> officers;   
	private boolean visibility;
	private ArrayList<Enquiry> enquiries;
	private ArrayList<Applicant> applicants;
	private ArrayList<HDB_Officer> requestingOfficer;
	
	public Project()
	{
		this.projectName = "";
		this.neighbourhood = "";
		this.flat_1 = new Flat();
		this.flat_2 = new Flat();
		this.application_opening_date = "";
		this.application_closing_date = "";
		this.HDB_Manager = "";
		this.officer_slot = 0;
		this.visibility = false;
		this.officers = new ArrayList<>();
		this.enquiries = new ArrayList<>();
		this.applicants = new ArrayList<>();
		this.requestingOfficer = new ArrayList<>();
	}
	
	public Project(String projectName, String neighbourhood, String t1, int nof1, int sp1, String t2, int nof2, int sp2, String aod, String acd, String Manager, int noo) 
	{
		this.projectName = projectName;
		this.neighbourhood = neighbourhood;
		this.flat_1 = new Flat(t1, nof1, sp1);
		this.flat_2 = new Flat(t2, nof2, sp2);
		this.application_opening_date = aod;
		this.application_closing_date = acd;
		this.HDB_Manager = Manager;
		this.officer_slot = noo;
		this.visibility = false;
		this.officers = new ArrayList<>();
		this.enquiries = new ArrayList<>();
		this.applicants = new ArrayList<>();
		this.requestingOfficer = new ArrayList<>();
	}
	
	//projectName get/set
	public String get_ProjectName() {return projectName;}
    public void set_ProjectName(String projectName) {this.projectName = projectName;}
    
    //neighborhood get/set
    public String get_Neighbourhood() {return neighbourhood;}
    public void set_Neighbourhood(String neighbourhood) {this.neighbourhood = neighbourhood;}
    
    //flat 1 and 2 get
    public Flat get_flat_1() {return flat_1;}
    public Flat get_flat_2() {return flat_2;}
    
    //application opening closing date get/set
    public String get_Application_opening_date() {return application_opening_date;}
    public void set_Application_opening_date(String application_opening_date) {this.application_opening_date = application_opening_date;}

    public String get_Application_closing_date() {return application_closing_date;}
    public void set_Application_closing_date(String application_closing_date) {this.application_closing_date = application_closing_date;}
    
    //HDB Manager get/set
    public String get_HDB_Manager() {return HDB_Manager;}
    public void set_HDB_Manager(String HDB_Manager) {this.HDB_Manager = HDB_Manager;}
	
    //Officer slot get/set
    public int get_Officer_slot() {return officer_slot;}
    public void set_Officer_slot(int officer_slot) {this.officer_slot = officer_slot;}
	
	// Officer get/set 
	public ArrayList<HDB_Officer> get_officers(){return officers;}
	public void add_officer(HDB_Officer officer)
	{
		if (officers.size() >= officer_slot)
		{
			System.out.println("Maximum Number of Officers!");
		}
		else 
		{
			officers.add(officer);
			removeRequestingOfficer(officer);
			System.out.println("Officer Added Successfully!");
		}
	}
	
	
	//visibility get/set
	public boolean get_visibility() {return visibility;}
	public void set_visibility(String on_off) {
		if (on_off.equalsIgnoreCase("on")) {
			visibility = true;
		}
		else if (on_off.equalsIgnoreCase("off")) {
			visibility = false;
		}
		else {System.out.println("Invalid Input!");}
	}
	
	
	//Adding and Reading Enquires
	public void add_Enquiry(Enquiry enquiry) {
		enquiries.add(enquiry);
	}
	
	public ArrayList<Enquiry> getEnquiries(){
		return enquiries;
	}
	
	
	//Applicant get/set
	public ArrayList<Applicant> getApplicants() {return applicants;}
	public void addApplicant(Applicant applicant) {applicants.add(applicant);}
	
	//Get applicant by NRIC
	public Applicant getApplicantByNRIC(String NRIC) {
		for (Applicant applicant: applicants) {
			if (applicant.get_Nric().equals(NRIC)) return applicant;
		}
		
		return null;
	}
	
	public void removeApplicant(Applicant applicant) {
		if (this.applicants.contains(applicant)) {
			applicants.remove(applicant);
		}
	}
	
	//requesting Officer get/set
	public ArrayList<HDB_Officer> getRequestingOfficer() {return requestingOfficer;}
	public void addRequestingOfficer(HDB_Officer officer) {requestingOfficer.add(officer);}
	
	public HDB_Officer getRequestingOfficerByNRIC(String NRIC) {
		for (HDB_Officer officer: requestingOfficer) {
			if (officer.get_Nric().equals(NRIC)) return officer;
		}
		
		return null;
	}
	
	public void removeRequestingOfficer(HDB_Officer officer) {
		if (this.requestingOfficer.contains(officer)) {
			requestingOfficer.remove(officer);
		}
	}
	
	@Override
	public String toString() {
		String officerNames = officers.stream()
                .map(HDB_Officer::get_Name)
                .collect(Collectors.joining(",")); 
		
		StringBuilder sb = new StringBuilder();
		
		 sb.append("\nProject Name: ").append(projectName).append("\n")
         .append("Neighbourhood: ").append(neighbourhood).append("\n")
         .append("Type 1: ").append(flat_1)
         .append("Type 2: ").append(flat_2)
         .append("Application Opening Date: ").append(application_opening_date).append("\n")
         .append("Application Closing Date: ").append(application_closing_date).append("\n")
         .append("HDB Manager: ").append(HDB_Manager).append("\n")
         .append("Officer Slot: ").append(officer_slot).append("\n")
         .append("Visibility: ").append(visibility ? "Open" : "Closed").append("\n")
         .append("Number of Officers Assigned: ").append(officers.size()).append("\n")
         .append("Officers Assigned: ").append(officerNames).append("\n")
         .append("Total Enquiries: ").append(enquiries.size()).append("\n");

       return sb.toString();
	}
}
