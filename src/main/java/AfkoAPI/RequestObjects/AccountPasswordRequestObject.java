package AfkoAPI.RequestObjects;

public class AccountPasswordRequestObject {
    String email;
    String oldPassword;
    String newPassword;

    public AccountPasswordRequestObject(String email, String oldPassword, String newPassword){
        this.email = email;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
