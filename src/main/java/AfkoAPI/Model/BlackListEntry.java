package AfkoAPI.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "blacklist_entry")
public class BlackListEntry {

    @Id
    private String entry;

    protected BlackListEntry() {}
    public BlackListEntry(String entry) {
        this.entry = entry;
    }

    public String getEntry() {
        return entry;
    }
}
