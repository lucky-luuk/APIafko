package AfkoAPI.RequestObjects;

import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Ticket;
public class TicketRequestObject {
    private String id;
    private String Message;
    private String accountID;
    private Abbreviation abbreviation;
    private String createDate;

    public TicketRequestObject(String id, String message, String accountID, Abbreviation abbreviation, String createDate) {
        this.id = id;
        Message = message;
        this.accountID = accountID;
        this.abbreviation = abbreviation;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
