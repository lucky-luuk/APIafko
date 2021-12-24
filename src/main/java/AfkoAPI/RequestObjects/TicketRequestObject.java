package AfkoAPI.RequestObjects;

import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Account;
import AfkoAPI.Model.Ticket;

import java.time.LocalDate;
import java.util.Date;

public class TicketRequestObject {
    private String Message;
    private Account accountID;
    private Abbreviation abbreviation;
    private LocalDate createDate;
    private String StatusName;

    public TicketRequestObject(String message, Account accountID, Abbreviation abbreviation, LocalDate createDate, String StatusName) {
        this.Message = message;
        this.accountID = accountID;
        this.abbreviation = abbreviation;
        this.createDate = createDate;
        this.StatusName = StatusName;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Account getAccountID() {
        return accountID;
    }

    public void setAccountID(Account accountID) {
        this.accountID = accountID;
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
