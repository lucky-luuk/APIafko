package AfkoAPI.RequestObjects;

public class RoleUserRequestObject {
    private String email;
    private String roleName;

    public RoleUserRequestObject() {
    }

    public RoleUserRequestObject(String username, String roleName) {
        this.email = username;
        this.roleName = roleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
