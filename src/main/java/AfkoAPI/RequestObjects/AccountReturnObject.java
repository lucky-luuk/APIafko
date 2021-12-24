package AfkoAPI.RequestObjects;

public class AccountReturnObject {
    String id;
    String firstName;
    String lsatName;
    String email;

    public AccountReturnObject() {
    }

    public AccountReturnObject(String id, String firstName, String lsatName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lsatName = lsatName;
        this.email = email;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLsatName() {
        return lsatName;
    }

    public void setLsatName(String lsatName) {
        this.lsatName = lsatName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
