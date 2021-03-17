package gr.ihu.noobdroid;

public class Sportsman {

    private int id;
    private String firstName;
    private String lastName;
    private String headquarters;
    private String country;
    private int sportId;
    private int birthYear;

    public Sportsman(int id, String firstName, String lastName, String headquarters, String country,
                     int sportId, int birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.headquarters = headquarters;
        this.country = country;
        this.sportId = sportId;
        this.birthYear = birthYear;
    }

    public int getId() { return this.id; }

    public String getFirstName() { return this.firstName; }

    public String getLastName() { return this.lastName; }

    public String getHeadquarters() { return this.headquarters; }

    public String getCountry() { return this.country; }

    public int getSportId() { return this.sportId; }

    public int getBirthYear() { return this.birthYear; }

    @Override
    public String toString() {
        return "Sportsman{" +
                "  id=" + this.id +
                "  , firstName=" + this.firstName +
                "  , lastName=" + this.lastName +
                "  , headquarters=" + this.headquarters +
                "  , country=" + this.country +
                "  , sportId=" + this.sportId +
                "  , birthYear=" + this.birthYear +
                "}";
    }
    
}
