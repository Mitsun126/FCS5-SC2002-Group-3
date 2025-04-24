package code;

/**
 * This is an abstract class representing a user in the system. It contains common attributes 
 * and methods for managing user details such as name, NRIC, age, marital status, and password.
 * This class serves as a base class for other specific user types like applicants and officers.
 */
public abstract class User {
    
    private String name;
    private String nric;
    private int age;
    private String marital_status;
    private String password;

    /**
     * Default constructor that initializes a user with default values.
     * The password is set to "password".
     */
    public User() {
        name = "";
        nric = "";
        age = 0;
        marital_status = "";
        password = "password";
    }

    /**
     * Constructor that initializes a user with specific values.
     *
     * @param name The name of the user.
     * @param NRIC The NRIC (National Registration Identity Card) of the user.
     * @param age The age of the user.
     * @param marital_status The marital status of the user.
     * @param password The password of the user.
     */
    public User(String name, String NRIC, int age, String marital_status, String password) {
        this.name = name;
        this.nric = NRIC;
        this.age = age;
        this.marital_status = marital_status;
        this.password = password;
    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String get_Name() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The new name of the user.
     */
    public void set_Name(String name) {
        this.name = name;
    }

    /**
     * Gets the NRIC of the user.
     *
     * @return The NRIC of the user.
     */
    public String get_Nric() {
        return nric;
    }

    /**
     * Sets the NRIC of the user.
     *
     * @param nric The new NRIC of the user.
     */
    public void set_Nric(String nric) {
        this.nric = nric;
    }

    /**
     * Gets the age of the user.
     *
     * @return The age of the user.
     */
    public int get_Age() {
        return age;
    }

    /**
     * Sets the age of the user.
     *
     * @param age The new age of the user.
     */
    public void set_Age(int age) {
        this.age = age;
    }

    /**
     * Gets the marital status of the user.
     *
     * @return The marital status of the user.
     */
    public String get_Marital_status() {
        return marital_status;
    }

    /**
     * Sets the marital status of the user.
     *
     * @param marital_status The new marital status of the user.
     */
    public void set_Marital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String get_Password() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The new password of the user.
     */
    public void set_Password(String password) {
        this.password = password;
    }
}
