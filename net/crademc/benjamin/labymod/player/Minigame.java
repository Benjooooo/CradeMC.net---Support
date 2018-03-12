package net.crademc.benjamin.labymod.player;

public class Minigame {

    private String map;
    private int mapcountdown;
    private String kit;
    private int kitcountdown;

    private String team;

    public Minigame() {
        this.map = "Voting";
        this.kitcountdown = 0;
        this.kit = "Classic";
        this.kitcountdown = 0;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public int getMapcountdown() {
        return mapcountdown;
    }

    public void setMapcountdown(int mapcountdown) {
        this.mapcountdown = mapcountdown;
    }

    public String getKit() {
        return kit;
    }

    public void setKit(String kit) {
        this.kit = kit;
    }

    public int getKitcountdown() {
        return kitcountdown;
    }

    public void setKitcountdown(int kitcountdown) {
        this.kitcountdown = kitcountdown;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
