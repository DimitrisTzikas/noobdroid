package gr.ihu.noobdroid;

public class Team {

    private int id;
    private String name;
    private String stadium;
    private String headquarters;
    private String county;
    private int sportId;
    private int establishYear;

    public Team(int id, String name, String stadium, String headquarters, String county,
                int sportId, int establishYear) {
        this.id = id;
        this.name = name;
        this.stadium = stadium;
        this.headquarters = headquarters;
        this.county = county;
        this.sportId = sportId;
        this.establishYear = establishYear;
    }

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public String getStadium() { return this.stadium; }

    public String getHeadquarters() { return this.headquarters; }

    public String getCounty() { return this.county; }

    public int getSportId() { return this.sportId; }

    public int getEstablishYear() { return this.establishYear; }

}
