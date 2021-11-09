package AfkoAPI.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Comparator;

@Entity
@Table(name = "blacklist_entry")
public class BlacklistEntry {

    @Id
    private String entry;

    protected BlacklistEntry() {}
    public BlacklistEntry(String entry) {
        this.entry = entry;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}
