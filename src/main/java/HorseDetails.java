public class HorseDetails {
    int number;
    String name;
    int odds;
    boolean won;

    public HorseDetails(int number, String name, int odds, boolean won) {
        this.number = number;
        this.name = name;
        this.odds = odds;
        this.won = won;
    }

    @Override
    public String toString() {
        return number +
                ", " + name +
                ", " + odds +
                ", " + won;
    }
}
