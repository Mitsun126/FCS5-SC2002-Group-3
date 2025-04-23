package code;

public class Flat {
	private String type;
	private int number_of_flats;
	private int sellingPrice;
	
	public Flat() {
		this.type = "";
		this.number_of_flats = 0;
		this.sellingPrice = 0;
	}

	public Flat(String type, int number_of_flats, int sellingPrice) {
		this.type = type;
		this.number_of_flats = number_of_flats;
		this.sellingPrice = sellingPrice;
	}
	
	public String get_type() {return type;}
	
	public void set_type(String type) {this.type = type;}
	
	public int get_number_of_flats() {return number_of_flats;}
	
	 public void set_number_of_flats(int number_of_flats) { this.number_of_flats = number_of_flats; }

	 public int get_sellingPrice() { return sellingPrice; }

    public void set_sellingPrice(int sellingPrice) { this.sellingPrice = sellingPrice; }
    
    @Override
    public String toString() {
    	return type + " (" + number_of_flats + " flats, Price: $" + sellingPrice +")\n";
    }

}
