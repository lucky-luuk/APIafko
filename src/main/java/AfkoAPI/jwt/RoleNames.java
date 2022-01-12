package AfkoAPI.jwt;

public enum RoleNames {
    MOD("MOD");
    private String value;

    RoleNames(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
