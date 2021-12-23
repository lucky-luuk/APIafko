package AfkoAPI.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Ticket {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "Message")
    private String message;

    @Column(name = "CreateDate")
    private String createDate;

    @OneToOne(targetEntity = Account.class)
    private String accountID;

    @OneToOne(targetEntity = Account.class)
    private Abbreviation abbreviation;



    // The default constructor exists only for the sake of JPA (https://spring.io/guides/gs/accessing-data-jpa/)
    public Ticket() {}
    // creates a new id, do not use for already existing data!
    public Ticket(String id, String message, String createDate, String accountID, Abbreviation abbreviation) {
        this.id = id;
        this.message = message;
        this.createDate = createDate;
        this.accountID = accountID;
        this.abbreviation = abbreviation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Abbreviation getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(Abbreviation abbreviation) {
        this.abbreviation = abbreviation;
    }
}
