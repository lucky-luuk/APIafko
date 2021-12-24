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

    //todo change name
    @Column(name = "account_id")
    private String accountId;

    @ManyToOne(targetEntity = TempAbbreviation.class)
    private TempAbbreviation temporaryAbbreviation;

    @Column(name = "statusname")
    private String statusName;

    @Column(name = "type")
    private String type;

    public Ticket() {
    }

    public Ticket(TicketRequestObject obj) {
        this.id = UUID.randomUUID().toString();
        this.temporaryAbbreviation = obj.getTemporaryAbbreviation();
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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public TempAbbreviation getTemporaryAbbreviation() {
        return temporaryAbbreviation;
    }

    public void setTemporaryAbbreviation(TempAbbreviation temporaryAbbreviation) {
        this.temporaryAbbreviation = temporaryAbbreviation;
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
