package code;

import java.util.ArrayList;

public class Project {
	private String projectName;
	private String neighbourhood;
	private String type_1;
	private int number_of_flats_1;
	private int sellingPrice1;
	private String type_2;
	private int number_of_flats_2;
	private int sellingPrice2;
	private String application_opening_date;
	private String application_closing_date;
	private String HDB_Manager;
	private int officer_slot;
	private ArrayList<HDB_Officer> officers;   
	private boolean visibility;
	private ArrayList<Enquiry> enquiries;
	private ArrayList<Applicant> applicants;
	
	public Project()
	{
		this.projectName = "";
		this.neighbourhood = "";
		this.type_1 = "";
		this.number_of_flats_1 = 0;
		this.sellingPrice1 = 0;
		this.type_2 = "";
		this.number_of_flats_2 = 0;
		this.sellingPrice2 = 0;
		this.application_opening_date = "";
		this.application_closing_date = "";
		this.HDB_Manager = "";
		this.officer_slot = 0;
		this.officers = new ArrayList<>();
		this.enquiries = new ArrayList<>();
		this.applicants = new ArrayList<>();
	}
	
	public Project(String projectName, String neighbourhood, String t1, int nof1, int sp1, String t2, int nof2, int sp2, String aod, String acd, String Manager, int noo) 
	{
		this.projectName = projectName;
		this.neighbourhood = neighbourhood;
		this.type_1 = t1;
		this.number_of_flats_1 = nof1;
		this.sellingPrice1 = sp1;
		this.type_2 = t2;
		this.number_of_flats_2 = nof2;
		this.sellingPrice2 = sp2;
		this.application_opening_date = aod;
		this.application_closing_date = acd;
		this.HDB_Manager = Manager;
		this.officer_slot = noo;
		this.officers = new ArrayList<>();
		this.enquiries = new ArrayList<>();
		this.applicants = new ArrayList<>();
	}
	
	//projectName get/set
	public String get_ProjectName() {return projectName;}
    public void set_ProjectName(String projectName) {this.projectName = projectName;}
    
    //neighborhood get/set
    public String get_Neighbourhood() {return neighbourhood;}
    public void set_Neighbourhood(String neighbourhood) {this.neighbourhood = neighbourhood;}
    
    //type, number_of_flats, selling price (1) get/set
    public String get_Type_1() {return this.type_1;}
    public void set_Type_1(String type_1) {this.type_1 = type_1;}

    public int get_Number_of_flats_1() {return number_of_flats_1;}
    public void set_Number_of_flats_1(int number_of_flats_1) {this.number_of_flats_1 = number_of_flats_1;}

    public int get_SellingPrice1() {return sellingPrice1;}
    public void set_SellingPrice1(int sellingPrice1) {this.sellingPrice1 = sellingPrice1;}
  
	//type, number_of_flats, selling price (1) get/set
    public String get_Type_2() {return this.type_2;}
    public void set_Type_2(String type_2) {this.type_2 = type_2;}

    public int get_Number_of_flats_2() {return number_of_flats_2;}
    public void set_Number_of_flats_2(int number_of_flats_2) {this.number_of_flats_2 = number_of_flats_2;}

    public int get_SellingPrice2() {return sellingPrice2;}
    public void set_SellingPrice2(int sellingPrice2) {this.sellingPrice2 = sellingPrice2;}
    
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		 sb.append("Project Name: ").append(projectName).append("\n")
         .append("Neighbourhood: ").append(neighbourhood).append("\n")
         .append("Type 1: ").append(type_1).append(" (").append(number_of_flats_1).append(" flats, Price: $").append(sellingPrice1).append(")\n")
         .append("Type 2: ").append(type_2).append(" (").append(number_of_flats_2).append(" flats, Price: $").append(sellingPrice2).append(")\n")
         .append("Application Opening Date: ").append(application_opening_date).append("\n")
         .append("Application Closing Date: ").append(application_closing_date).append("\n")
         .append("HDB Manager: ").append(HDB_Manager).append("\n")
         .append("Officer Slot: ").append(officer_slot).append("\n")
         .append("Visibility: ").append(visibility ? "Open" : "Closed").append("\n")
         .append("Number of Officers Assigned: ").append(officers.size()).append("\n")
         .append("Total Enquiries: ").append(enquiries.size()).append("\n");

       return sb.toString();
	}
}
