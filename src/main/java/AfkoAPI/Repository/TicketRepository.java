package AfkoAPI.Repository;

import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String> {
    // https://spring.io/guides/gs/accessing-data-jpa/
    List<Ticket> findByTicketID(String id);
    // organisation_id = organisation.id https://stackoverflow.com/questions/44566760/spring-boot-using-foreign-key-in-crudrepository
    List<Ticket> findByMessage(String message);

    List<Ticket> findByAccountID(String accountID);

    List<Ticket> findByAbbreviationID(String AbbreviationID);

    List<Ticket> findByCreateDate(String createDate);

}
