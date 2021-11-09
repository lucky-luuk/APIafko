package AfkoAPI.RequestObjects;

public class ScoreRequestObject {

    private String name;

    private Integer score;

    private String organisation_id;

    public ScoreRequestObject(String name, Integer score, String organisation_id){
        this.name = name;
        this.score = score;
        this.organisation_id = organisation_id;
    }

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public Integer getScore(){return score;}

    public void setScore(Integer score) {this.score = score;}

    public String getOrganisation_id() {return organisation_id;}

    public void setOrganisation_id(String organisation_id) { this.organisation_id = organisation_id;}
}
