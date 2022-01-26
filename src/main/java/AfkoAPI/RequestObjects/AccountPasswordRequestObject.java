package AfkoAPI.RequestObjects;

public class AccountPasswordRequestObject {
    String oldPassword;
    String newPassword;

    public AccountPasswordRequestObject(String email, String oldPassword, String newPassword){
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
