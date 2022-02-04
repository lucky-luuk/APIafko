package AfkoAPI.RequestObjects;

public class AccountPasswordRequestObject {
    private String newPassword;

    public AccountPasswordRequestObject() { }

    public AccountPasswordRequestObject(String newPassword){
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
