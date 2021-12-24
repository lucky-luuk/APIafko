package AfkoAPI.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.type.descriptor.java.LocalDateJavaDescriptor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private Account accountid;

    @ManyToOne(targetEntity = Abbreviation.class)
    private Abbreviation abbreviation;

    @Column(name = "statusname")
    private String StatusName;




    // The default constructor exists only for the sake of JPA (https://spring.io/guides/gs/accessing-data-jpa/)
    public Ticket() {}
    // creates a new id, do not use for already existing data!
    public Ticket(String message, LocalDate currentDate, Account accountID, Abbreviation abbreviation, String StatusName) {

        this.message = message;
        this.createDate = currentDate;
        this.accountid = accountID;
        this.abbreviation = abbreviation;
        this.StatusName = StatusName;
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
        return accountid;
    }

    public void setAccountID(Account accountID) {
        this.accountid = accountID;
    }

    public Abbreviation getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(Abbreviation abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }
}
