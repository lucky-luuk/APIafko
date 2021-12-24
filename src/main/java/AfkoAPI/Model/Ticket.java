package AfkoAPI.Model;

import AfkoAPI.RequestObjects.TicketRequestObject;
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

    @Column(name = "type")
    private String type;

    public Ticket() {
    }

    public Ticket(TicketRequestObject obj) {
        this.id = UUID.randomUUID().toString();
        this.abbreviation = obj.getAbbreviation();
        this.createDate = obj.getCreateDate();
        this.accountId = obj.getAccountId();
        this.message = obj.getMessage();
        this.statusName = obj.getStatusName();
        this.type = obj.getType();
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

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
