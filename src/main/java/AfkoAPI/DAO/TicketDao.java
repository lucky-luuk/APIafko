package AfkoAPI.DAO;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Ticket;
import AfkoAPI.Repository.*;
import AfkoAPI.RequestObjects.TicketRequestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class TicketDao {
    @Autowired
    TicketRepository ticketRep;
    @Autowired
    BlacklistRepository blacklistRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    OrganisationRepository organisationRepository;

    public HTTPResponse<Ticket> addTicket (TicketRequestObject requestObject){

        requestObject.setCreateDate(LocalDate.now());
        Ticket ticket = new Ticket(requestObject);
        ticketRep.save(ticket);
        return HTTPResponse.returnSuccess(ticket);
    }

    public HTTPResponse<Ticket[]> addTickets(TicketRequestObject[] ticketRequestObjects) {
        Ticket[] tickets = new Ticket[ticketRequestObjects.length];


        for (int i = 0; i < ticketRequestObjects.length; i++) {
            HTTPResponse<Ticket> response = addTicket(ticketRequestObjects[i]);

            if (response.isSuccess())
                tickets[i] = response.getData();
            else
                return HTTPResponse.<Ticket[]>returnFailure(response.getError());
        }

        return HTTPResponse.<Ticket[]>returnSuccess(tickets);
    }

    public HTTPResponse changeTicket(Ticket[] tickets) {
        Ticket old = tickets[0];
        Ticket newObject = tickets[1];
        Optional<Ticket> ticket = ticketRep.findById(old.getId());

        if (ticket.isEmpty()){
            return HTTPResponse.<Abbreviation[]>returnFailure("could not find ticket with id: " + old.getId());
        }

        ticket.get().setId(newObject.getId());
        ticket.get().setMessage(newObject.getMessage());
        ticket.get().setAccountId(newObject.getAccountId());
        ticket.get().setAbbreviation(newObject.getAbbreviation());
        ticket.get().setCreateDate(newObject.getCreateDate());
        ticketRep.save(ticket.get());
        return HTTPResponse.<Ticket[]>returnSuccess(tickets);
    }

    public HTTPResponse<Abbreviation[]> deleteTicket(Ticket[] tickets){
        for (Ticket ticket: tickets){
            Optional<Ticket> a = ticketRep.findById(ticket.getId());
            if (a.isEmpty()) return HTTPResponse.<Abbreviation[]>returnFailure("could not find ticket with id: " + ticket.getId());
            ticketRep.deleteById(ticket.getId());
        }
        return HTTPResponse.<Ticket[]>returnSuccess(tickets);
    }

    public HTTPResponse getTicketByID(String id) {
        Optional<Ticket> data = ticketRep.findById(id);

        if (data.isEmpty())
            return HTTPResponse.<Ticket>returnFailure("could not find id: " + id);

        return HTTPResponse.<Ticket>returnSuccess(data.get());
    }

    public HTTPResponse getAllTickets() {
        return HTTPResponse.<List<Ticket>>returnSuccess(ticketRep.findAll());
    }
}
