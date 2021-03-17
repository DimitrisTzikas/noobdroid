package gr.ihu.noobdroid;

public class Sport {

    private int id;
    private String name;
    private boolean isTeamGame;
    private boolean isMaleGame;

    public Sport(int id, String name, boolean isTeamGame, boolean isMaleGame) {
        this.id = id;
        this.name = name;
        this.isTeamGame = isTeamGame;
        this.isMaleGame = isMaleGame;
    }

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public int isTeamGame() { return this.isTeamGame ? 1 : 0; }

    public int isMaleGame() { return this.isMaleGame ? 1 : 0; }

    @Override
    public String toString() {
        return "Sport{" +
                "  id=" + this.id +
                "  , name=" + this.name +
                "  , isTeamGame" + this.isTeamGame +
                "  , isMaleGame" + this.isMaleGame +
                "}";
    }
}
