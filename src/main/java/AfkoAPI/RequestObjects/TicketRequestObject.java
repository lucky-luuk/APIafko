package AfkoAPI.RequestObjects;

import AfkoAPI.Model.TempAbbreviation;

public class TicketRequestObject {
    private String message;
    private String accountId;
    private TempAbbreviation temporaryAbbreviation;
    private String statusName;
    private String type;

    public TicketRequestObject() {}
    public TicketRequestObject(String message, String accountID, TempAbbreviation temporaryAbbreviation, String StatusName, String type) {
        this.message = message;
        this.accountId = accountID;
        this.temporaryAbbreviation = temporaryAbbreviation;
        this.statusName = StatusName;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
