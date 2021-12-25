package AfkoAPI.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class TempAbbreviation {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(targetEntity = Organisation.class)
    private Set<Organisation> organisations = new HashSet<>();

    @Column
    private String accountId;

    public TempAbbreviation( String name, String description, Set<Organisation> organisations, String tempAccountId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.organisations = organisations;
        this.accountId = tempAccountId;
    }

    public TempAbbreviation() {
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

    public Set<Organisation> getOrganisations() {
        return organisations;
    }

    public void setOrganisations(Set<Organisation> organisations) {
        this.organisations = organisations;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
