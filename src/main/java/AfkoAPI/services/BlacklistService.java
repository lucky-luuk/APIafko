package AfkoAPI.services;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.BlacklistEntry;
import AfkoAPI.Repository.AbbreviationRepository;
import AfkoAPI.Repository.BlacklistRepository;

import java.util.List;

public class BlacklistService {

    /** checks if an abbreviation contains words from the blacklist, adds it to the database if it does not otherwise returns HTTPResult FAILURE
     * @param blacklistRepository the blacklistRepository
     * @param abbreviationRepository the abbreviationRepository
     * @param abbr the abbreviation to check and add
     * @return HTTPResponse SUCCESS or FAILURE
     */
    public static HTTPResponse<Abbreviation> filterAbbreviationAndSaveToRepository(BlacklistRepository blacklistRepository, AbbreviationRepository abbreviationRepository, Abbreviation abbr) {
        List<BlacklistEntry> blacklist = blacklistRepository.findAll();
        for (BlacklistEntry entry : blacklist) {
            if (abbr.getName().contains(entry.getEntry()))
                return HTTPResponse.<Abbreviation>returnFailure("abbreviation name contains forbidden word: " + entry.getEntry());
            if (abbr.getDescription().contains(entry.getEntry()))
                return HTTPResponse.<Abbreviation>returnFailure("abbreviation definition contains forbidden word: " + entry.getEntry());
        }
        abbreviationRepository.save(abbr);
        return HTTPResponse.<Abbreviation>returnSuccess(abbr);
    }
}
