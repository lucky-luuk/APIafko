package AfkoAPI.DAO;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Ticket;
import AfkoAPI.Repository.*;
import AfkoAPI.RequestObjects.TicketRequestObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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
        Ticket ticket = new Ticket();
        ticket.setId(requestObject.getId());
        ticket.setMessage(requestObject.getMessage());
        ticket.setAccountID(requestObject.getAccountID());
        ticket.setAbbreviation(requestObject.getAbbreviation());
        ticket.setCreateDate(requestObject.getCreateDate());
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

    public HTTPResponse changeTicket(Ticket[] tickets) { //neemt 2 abbreviation vervang de 1e voor de ander
        Ticket old = tickets[0];
        Ticket newObject = tickets[1];
        Optional<Ticket> ticket = ticketRep.findById(old.getId());

        if (ticket.isEmpty()){
            return HTTPResponse.<Abbreviation[]>returnFailure("could not find ticket with id: " + old.getId());
        }

        ticket.get().setId(newObject.getId());
        ticket.get().setMessage(newObject.getMessage());
        ticket.get().setAccountID(newObject.getAccountID());
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
}
