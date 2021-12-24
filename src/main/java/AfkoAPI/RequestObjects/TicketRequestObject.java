package AfkoAPI.RequestObjects;

import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Account;

import java.time.LocalDate;

public class TicketRequestObject {
    private String message;
    private Account accountId;
    private Abbreviation abbreviation;
    private LocalDate createDate;
    private String StatusName;

    public TicketRequestObject(String message, Account accountID, Abbreviation abbreviation, LocalDate createDate, String StatusName) {
        this.message = message;
        this.accountId = accountID;
        this.abbreviation = abbreviation;
        this.createDate = createDate;
        this.StatusName = StatusName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }
}
