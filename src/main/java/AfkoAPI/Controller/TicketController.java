package AfkoAPI.Controller;

import AfkoAPI.DAO.TicketDao;
import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Ticket;
import AfkoAPI.RequestObjects.TicketRequestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class TicketController {
    @Autowired
    TicketDao dao;

    @PostMapping("/ticket")
    public HTTPResponse addTicket(@RequestBody TicketRequestObject[] tickets) {
        return dao.addTickets(tickets);
    }

    @PutMapping("/ticket")
    public HTTPResponse changeTicket(@RequestBody Ticket[] tickets) {
        if (tickets.length == 2) {
            return dao.changeTicket(tickets);
        }
        return HTTPResponse.returnFailure("input length is not 2");
    }

    @DeleteMapping("/ticket")
    public HTTPResponse deleteTicket(@RequestBody Ticket[] ticket) {
        return dao.deleteTicket(ticket);
    }

    @GetMapping("/ticket")
    public HTTPResponse getTicket(@RequestParam(name = "id", defaultValue = "-1") Integer id,
                                  @RequestParam(name = "abbreviation_id", defaultValue = "") String abbreviationId) {
        if (id != -1) return dao.getTicketByID(id);
        if (!abbreviationId.equals("")) return dao.getTicketsByAbbreviationId(abbreviationId);
        return dao.getAllTickets();
    }
}
