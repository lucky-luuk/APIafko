package AfkoAPI.RequestObjects;

import AfkoAPI.Model.Account;
import AfkoAPI.Model.Organisation;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class AbbreviationRequestObject {
    private String id;

    private String name;

    private String description;

    private String organisation_id;

    private String account_id;

    public AbbreviationRequestObject(String id, String name, String description, String organisation_id, String account_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.organisation_id = organisation_id;
        this.account_id = account_id;
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

    public String getOrganisation_id() {
        return organisation_id;
    }

    public void setOrganisation_id(String organisation_id) {
        this.organisation_id = organisation_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }
}
