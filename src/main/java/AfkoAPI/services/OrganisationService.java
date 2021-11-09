package AfkoAPI.services;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Organisation;
import AfkoAPI.Repository.OrganisationRepository;
import AfkoAPI.RequestObjects.OrganisationRequestObject;

import java.util.Optional;

public class OrganisationService {

    /** adds a list of organisations, does not generate new ids
     * @param orgRep the organisation repository
     * @param orgs the organisation objects to take data from
     * @return a HTTPResponse object, either failure or success
     */
    public static HTTPResponse addOrganisations(OrganisationRepository orgRep, OrganisationRequestObject[] orgs) {
        Organisation[] organisations = new Organisation[orgs.length];

        for (int i = 0; i < orgs.length; i++) {
            HTTPResponse<Organisation> response = addSingleOrganisation(orgRep, orgs[i]);

            if (!response.isSuccess())
                return HTTPResponse.<Organisation[]>returnFailure(response.getError());

            organisations[i] = response.getData();
        }
        return HTTPResponse.<Organisation[]>returnSuccess(organisations);
    }

    /** adds a single organisation, does not generate a new id for it
     * @param orgRep the organisation repository
     * @param org the organisation object to take data from
     * @return a HTTPResponse object, either failure or success
     */
    public static HTTPResponse addSingleOrganisation(OrganisationRepository orgRep, OrganisationRequestObject org) {
        Organisation o = new Organisation(org.getName(), org.getId());
        orgRep.save(o);
        return HTTPResponse.<Organisation>returnSuccess(o);
    }

    /** adds multiple organisations, generates new ids for them all
     * @param orgRep the organisation repository
     * @param orgs the organisation objects to take data from
     * @return a HTTPResponse object, either failure or success
     */
    public static HTTPResponse addOrganisationsGenerateId(OrganisationRepository orgRep, OrganisationRequestObject[] orgs) {
        Organisation[] organisations = new Organisation[orgs.length];

        for (int i = 0; i < orgs.length; i++) {
            HTTPResponse<Organisation> response = addSingleOrganisationGenerateId(orgRep, orgs[i]);

            if (!response.getResponse().equals("SUCCESS"))
                return HTTPResponse.<Organisation[]>returnFailure(response.getError());

            organisations[i] = response.getData();
        }
        return HTTPResponse.<Organisation[]>returnSuccess(organisations);
    }

    /** adds a single organisation, generates a new id for it
     * @param orgRep the organisation repository
     * @param org the organisation object to take data from
     * @return a HTTPResponse object, either failure or success
     */
    public static HTTPResponse addSingleOrganisationGenerateId(OrganisationRepository orgRep, OrganisationRequestObject org) {
        Organisation o = new Organisation(org.getName());
        orgRep.save(o);
        return HTTPResponse.<Organisation>returnSuccess(o);
    }

    /** gets a list of organisations by their ids
     * @param orgRep the organisation repository
     * @param ids the ids to get
     * @return a HTTPResponse object, either failure or success, contains return data
     */
    public static HTTPResponse<Organisation[]> getOrganisationsByIds(OrganisationRepository orgRep, String[] ids) {
        Organisation[] organisations = new Organisation[ids.length];

        for (int i = 0; i < ids.length; i++) {
            Optional<Organisation> org = orgRep.findById(ids[i]);
            if (org.isEmpty())
                return HTTPResponse.<Organisation[]>returnFailure("organisation with id: " + ids[i] + " does not exist");
            organisations[i] = org.get();
        }
        return HTTPResponse.<Organisation[]>returnSuccess(organisations);
    }
}
