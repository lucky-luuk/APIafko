package AfkoAPI.Model;

import AfkoAPI.RequestObjects.TicketRequestObject;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table
public class Ticket {
    @Id
    @SequenceGenerator(name="webuser_idwebuser_seq",
            sequenceName="webuser_idwebuser_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="webuser_idwebuser_seq")
    @Column(name = "id", updatable=false)
    private Integer id;

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

    @Column()
    private String userName;

    @Column()
    private String userEmail;

    @Column()
    private String userPhone;

    public Ticket() {
    }

    public Ticket(TicketRequestObject obj) {
        this.temporaryAbbreviation = obj.getTemporaryAbbreviation();
        this.accountId = obj.getAccountId();
        this.message = obj.getMessage();
        this.statusName = obj.getStatusName();
        this.type = obj.getType();
        this.userEmail = obj.getUserEmail();
        this.userName = obj.getUserName();
        this.userPhone = obj.getUserPhone();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
