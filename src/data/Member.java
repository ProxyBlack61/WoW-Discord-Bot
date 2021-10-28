package data;

/**
 * @author Kilian Stöckler
 */
public class Member {

    private String name;
    private double score;
    private String spec;

    public Member(String name, int spec) {
        this.name = name;
        switch (spec) {
            case 1:
                this.spec = "Krieger";
                break;
            case 2:
                this.spec = "Paladin";
                break;
            case 3:
                this.spec = "Jäger";
                break;
            case 4:
                this.spec = "Schurke";
                break;
            case 5:
                this.spec = "Priester";
                break;
            case 6:
                this.spec = "Todesritter";
                break;
            case 7:
                this.spec = "Schamane";
                break;
            case 8:
                this.spec = "Magier";
                break;
            case 9:
                this.spec = "Hexenmeister";
                break;
            case 10:
                this.spec = "Mönch";
                break;
            case 11:
                this.spec = "Druide";
                break;
            case 12:
                this.spec = "Demonen Jäger";
                break;
        }
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public String getSpec() {
        return spec;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
