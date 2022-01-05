package AfkoAPI.RequestObjects;

import AfkoAPI.Model.TempAbbreviation;

import javax.persistence.Column;

public class TicketRequestObject {
    private String message;
    private String accountId;
    private TempAbbreviation temporaryAbbreviation;
    private String statusName;
    private String type;
    private String userName;
    private String userEmail;
    private String userPhone;

    public TicketRequestObject() {}

    public TicketRequestObject(String message, String accountId, TempAbbreviation temporaryAbbreviation, String statusName, String type, String userName, String userEmail, String userPhone) {
        this.message = message;
        this.accountId = accountId;
        this.temporaryAbbreviation = temporaryAbbreviation;
        this.statusName = statusName;
        this.type = type;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
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
