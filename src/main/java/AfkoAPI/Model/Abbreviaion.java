package AfkoAPI.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table()
public class Abbreviaion {
    @Id
    @Column(name = "fname")
    private String fname;
    public Abbreviaion() {

    }

}
