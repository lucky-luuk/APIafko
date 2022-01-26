package AfkoAPI.RequestObjects;

public class AccountPasswordRequestObject {
    String newPassword;

    public AccountPasswordRequestObject(String email, String oldPassword, String newPassword){
        this.newPassword = newPassword;
    }
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
