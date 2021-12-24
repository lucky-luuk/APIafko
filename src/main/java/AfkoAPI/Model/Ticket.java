package AfkoAPI.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity

public class Ticket {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "message")
    private String message;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "createdate")
    private LocalDate createDate;


    @ManyToOne(targetEntity = Account.class)
    private Account accountId;

    @ManyToOne(targetEntity = Abbreviation.class)
    private Abbreviation abbreviation;

    @Column(name = "statusname")
    private String statusName;




    // The default constructor exists only for the sake of JPA (https://spring.io/guides/gs/accessing-data-jpa/)
    public Ticket() {}
    // creates a new id, do not use for already existing data!
    public Ticket(String message, LocalDate currentDate, Account accountId, Abbreviation abbreviation, String StatusName) {

        this.message = message;
        this.createDate = currentDate;
        this.accountId = accountId;
        this.abbreviation = abbreviation;
        this.statusName = StatusName;
        this.id = UUID.randomUUID().toString();

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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Account getAccountID() {
        return accountId;
    }

    public void setAccountID(Account accountID) {
        this.accountId = accountID;
    }

    public Abbreviation getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(Abbreviation abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
