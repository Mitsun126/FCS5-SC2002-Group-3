package code;

/**
 * Represents a flat in a housing project, including its type, number of flats, and selling price.
 */
public class Flat {
    private String type;              // Type of the flat (e.g., 1-room, 2-room, etc.)
    private int number_of_flats;      // Number of flats of this type in the project
    private int sellingPrice;         // Selling price of each flat

    /**
     * Default constructor initializing the Flat with default values.
     */
    public Flat() {
        this.type = "";
        this.number_of_flats = 0;
        this.sellingPrice = 0;
    }

    /**
     * Constructor to initialize a Flat object with specific values.
     * 
     * @param type The type of the flat (e.g., 1-room, 2-room).
     * @param number_of_flats The number of flats of this type in the project.
     * @param sellingPrice The selling price of each flat.
     */
    public Flat(String type, int number_of_flats, int sellingPrice) {
        this.type = type;
        this.number_of_flats = number_of_flats;
        this.sellingPrice = sellingPrice;
    }

    /**
     * Gets the type of the flat.
     * 
     * @return The type of the flat.
     */
    public String get_type() {
        return type;
    }

    /**
     * Sets the type of the flat.
     * 
     * @param type The type of the flat (e.g., 1-room, 2-room).
     */
    public void set_type(String type) {
        this.type = type;
    }

    /**
     * Gets the number of flats of this type in the project.
     * 
     * @return The number of flats.
     */
    public int get_number_of_flats() {
        return number_of_flats;
    }

    /**
     * Sets the number of flats of this type in the project.
     * 
     * @param number_of_flats The number of flats.
     */
    public void set_number_of_flats(int number_of_flats) {
        this.number_of_flats = number_of_flats;
    }

    /**
     * Gets the selling price of each flat.
     * 
     * @return The selling price.
     */
    public int get_sellingPrice() {
        return sellingPrice;
    }

    /**
     * Sets the selling price of each flat.
     * 
     * @param sellingPrice The selling price.
     */
    public void set_sellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * Returns a string representation of the flat, including its type, number of flats, and selling price.
     * 
     * @return A string representing the flat's details.
     */
    @Override
    public String toString() {
        return type + " (" + number_of_flats + " flats, Price: $" + sellingPrice +")\n";
    }
}
