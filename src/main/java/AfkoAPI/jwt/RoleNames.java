package AfkoAPI.jwt;

public enum RoleNames {
    MOD("MOD"),
    ADMIN("ADMIN");

    private String value;

    RoleNames(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
