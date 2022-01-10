package AfkoAPI.RequestObjects;

import AfkoAPI.Model.Organisation;

public class AbbreviationRequestObject {
    private String id;

    private String name;

    private String description;

    private Organisation[] organisations;

    private String accountId;

    public AbbreviationRequestObject(String id, String name, String description, Organisation[] organisations, String account_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.organisations = organisations;
        this.accountId = account_id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Organisation[] getOrganisations() {
        return organisations;
    }

    public void setOrganisations(Organisation[] organisations) {
        this.organisations = organisations;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
