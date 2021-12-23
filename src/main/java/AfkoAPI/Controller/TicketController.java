package AfkoAPI.Controller;

import AfkoAPI.DAO.TicketDao;
import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Ticket;
import AfkoAPI.RequestObjects.TicketRequestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class TicketController {
    @Autowired
    TicketDao dao;

    @GetMapping("/")
    public String index() {
        return "";
    }

    @PostMapping("/ticket")
    public HTTPResponse addTicket(@RequestBody TicketRequestObject[] tickets) {
        return dao.addTickets(tickets);
    }

    @PutMapping("/ticket")
    public HTTPResponse changeTicket(@RequestBody Ticket[] tickets){
        if (tickets.length == 2) {
            return dao.changeTicket(tickets);
        }
        return HTTPResponse.returnFailure("input length is not 2");
    }

    @DeleteMapping("/ticket")
    public HTTPResponse deleteTicket(@RequestBody Ticket[] ticket){
        return dao.deleteTicket(ticket);
    }

    @GetMapping("/ticket")
    public HTTPResponse getTicket(@RequestParam(name="ID", defaultValue="") String id,
                                        @RequestParam(name="Message", defaultValue = "") String message,
                                        @RequestParam(name="AccountID", defaultValue="") String accountID,
                                        @RequestParam(name="AbbreviationID", defaultValue="") String AbbreviationID,
                                        @RequestParam(name="createdDate", defaultValue="") String createdDate) {


        if (!id.equals("")) return dao.getTicketByID(id);
        return HTTPResponse.returnFailure("ticketID field is empty");
    }
}
