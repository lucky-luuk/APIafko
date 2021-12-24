package AfkoAPI.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;


@Data
@Entity
@NoArgsConstructor
public class Role {
    @Id
    private String id;
    private String name;

    public Role(String id, String name) {
        this.id = id;
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }
}
