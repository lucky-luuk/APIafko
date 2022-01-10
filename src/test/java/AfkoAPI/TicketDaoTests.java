package AfkoAPI;

import AfkoAPI.DAO.TicketDao;
import AfkoAPI.Model.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TicketDaoTests {

    @Autowired
    TicketDao dao;

    @Test
    public void copyTicketCopiesTicket() throws Exception {
        Ticket old = new Ticket();
        old.setUserName("foo");
        Ticket newTicket = dao.copyTicket(old);
        assertThat(old.getUserName().equals(newTicket.getUserName())).isTrue();
    }
}
