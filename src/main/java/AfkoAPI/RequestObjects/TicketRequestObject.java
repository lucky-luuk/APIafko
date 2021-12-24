package AfkoAPI.RequestObjects;

import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Account;

import java.time.LocalDate;

public class TicketRequestObject {
    private String message;
    private Account accountId;
    private Abbreviation abbreviation;
    private LocalDate createDate;
    private String statusName;
    private String type;

    public TicketRequestObject() {}
    public TicketRequestObject(String message, Account accountID, Abbreviation abbreviation, LocalDate createDate, String StatusName, String type) {
        this.message = message;
        this.accountId = accountID;
        this.abbreviation = abbreviation;
        this.createDate = createDate;
        this.statusName = StatusName;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Abbreviation getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(Abbreviation abbreviation) {
        this.abbreviation = abbreviation;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
