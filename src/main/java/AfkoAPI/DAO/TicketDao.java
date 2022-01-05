package AfkoAPI.DAO;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.TempAbbreviation;
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
    TempAbbreviationRepository tempAbbrRep;
    @Autowired
    AbbreviationRepository abbreviationRepository;
    @Autowired
    BlacklistRepository blacklistRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    OrganisationRepository organisationRepository;

    public HTTPResponse<Ticket> addTicket (TicketRequestObject requestObject){

        Ticket ticket = new Ticket(requestObject);
        ticket.setCreateDate(LocalDate.now());
        // if this is not an info request
        if (ticket.getTemporaryAbbreviation() != null) {
            // if this is a new abbreviation
            Optional<Abbreviation> abbr = abbreviationRepository.findById(requestObject.getTemporaryAbbreviation().getId());
            if (abbr.isEmpty()) {
                // create a new temp abbr so we also create an id
                TempAbbreviation a = new TempAbbreviation(requestObject.getTemporaryAbbreviation().getName(),
                        requestObject.getTemporaryAbbreviation().getDescription(),
                        requestObject.getTemporaryAbbreviation().getOrganisations(),
                        requestObject.getTemporaryAbbreviation().getAccountId());
                tempAbbrRep.save(a);
                ticket.setTemporaryAbbreviation(a);
            }
            else {
                // else this is about an error in an abbr
                // check if a tempAbbr already exists, if not create one
                Optional<TempAbbreviation> a = tempAbbrRep.findById(requestObject.getTemporaryAbbreviation().getId());
                if (a.isEmpty()) {
                    tempAbbrRep.save(requestObject.getTemporaryAbbreviation());
                }
            }
        }
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
        Ticket newTicket = ticket.get();
        newTicket = copyTicket(newObject);

        ticketRep.save(newTicket);
        return HTTPResponse.<Ticket[]>returnSuccess(tickets);
    }

    public Ticket copyTicket(Ticket ticket) {
        Ticket newTicket = new Ticket();
        newTicket.setId(ticket.getId());
        newTicket.setMessage(ticket.getMessage());
        newTicket.setAccountId(ticket.getAccountId());
        newTicket.setTemporaryAbbreviation(ticket.getTemporaryAbbreviation());
        newTicket.setCreateDate(ticket.getCreateDate());
        newTicket.setStatusName(ticket.getStatusName());
        newTicket.setUserEmail(ticket.getUserEmail());
        newTicket.setUserName(ticket.getUserName());
        newTicket.setUserPhone(ticket.getUserPhone());
        return newTicket;
    }

    public HTTPResponse<Abbreviation[]> deleteTicket(Ticket[] tickets) {
        for (Ticket ticket: tickets){
            Optional<Ticket> a = ticketRep.findById(ticket.getId());
            if (a.isEmpty()) return HTTPResponse.<Abbreviation[]>returnFailure("could not find ticket with id: " + ticket.getId());
            ticketRep.deleteById(ticket.getId());
            // if there are no more tickets linked to this tempabbr, delete it.
            if (ticket.getTemporaryAbbreviation() != null) {
                List<Ticket> abbrTickets = ticketRep.findBytemporaryAbbreviation_id(ticket.getTemporaryAbbreviation().getId());
                if (abbrTickets.size() == 0) tempAbbrRep.deleteById(ticket.getTemporaryAbbreviation().getId());
            }
        }
        return HTTPResponse.<Ticket[]>returnSuccess(tickets);
    }

    public HTTPResponse getTicketByID(Integer id) {
        Optional<Ticket> data = ticketRep.findById(id);

        if (data.isEmpty())
            return HTTPResponse.<Ticket>returnFailure("could not find id: " + id);

        return HTTPResponse.<Ticket>returnSuccess(data.get());
    }

    public HTTPResponse getTicketsByAbbreviationId(String abbrID) {
        List<Ticket> tickets = ticketRep.findBytemporaryAbbreviation_id(abbrID);
        return HTTPResponse.<List<Ticket>>returnSuccess(tickets);
    }

    public HTTPResponse getAllTickets() {
        return HTTPResponse.<List<Ticket>>returnSuccess(ticketRep.findAll());
    }
}
