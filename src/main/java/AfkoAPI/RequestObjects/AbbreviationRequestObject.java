package AfkoAPI.RequestObjects;

import AfkoAPI.Model.Account;
import AfkoAPI.Model.Organisation;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AbbreviationRequestObject {
    private String id;

    private String name;

    private String description;

    private String[] organisations;

    private String createdBy;

    public AbbreviationRequestObject(String id, String name, String description, String[] organisationIds, String account_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.organisations = organisationIds;
        this.createdBy = account_id;
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

    public String[] getOrganisations() {
        return organisations;
    }

    public void setOrganisations(String[] organisations) {
        this.organisations = organisations;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
