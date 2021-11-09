package AfkoAPI.DAO;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.BlacklistEntry;
import AfkoAPI.Repository.BlacklistRepository;
import AfkoAPI.services.TrimListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BlacklistDao {

    @Autowired
    BlacklistRepository repository;

    /** get the entire blacklist
     * @param maxAmount the max amount of rows to return
     * @return HTTPResponse containing the returned data
     */
    public HTTPResponse getBlacklist(String maxAmount) {
        TrimListService<BlacklistEntry> service = new TrimListService<>();
        List<BlacklistEntry> data = repository.findAll();
        data = service.trimListByAmount(data, maxAmount);
        return HTTPResponse.<List<BlacklistEntry>>returnSuccess(data);
    }

    public HTTPResponse addMultipleToBlacklist(BlacklistEntry[] entries) {
        for (BlacklistEntry entry : entries) {
            HTTPResponse response = addSingleToBlacklist(entry);
            if (!response.isSuccess())
                return HTTPResponse.<BlacklistEntry[]>returnFailure(response.getError());
        }
        return HTTPResponse.<BlacklistEntry[]>returnSuccess(entries);
    }

    public HTTPResponse addSingleToBlacklist(BlacklistEntry entry) {
        repository.save(entry);
        return HTTPResponse.<BlacklistEntry>returnSuccess(entry);
    }

    /** edit an entry in the blacklist
     * @param entries an array with the entry to change at position 0, and the entry to change to at position 1
     * @return HTTPResponse
     */
    public HTTPResponse editBlacklistEntry(BlacklistEntry[] entries) {
        BlacklistEntry oldEntry = entries[0];
        BlacklistEntry newEntry = entries[1];

        // make sure the entry to replace exists
        Optional<BlacklistEntry> data = repository.findById(oldEntry.getEntry());
        if (data.isEmpty())
            return HTTPResponse.<BlacklistEntry[]>returnFailure("could not find entry: " + oldEntry.getEntry());

        // make sure the entry to be added does not already exist
        Optional<BlacklistEntry> newEntryData = repository.findById(newEntry.getEntry());
        if (newEntryData.isPresent())
            return HTTPResponse.<BlacklistEntry[]>returnFailure("entry: " + newEntry.getEntry() + " already exists");

        // remove the old entry
        HTTPResponse response = removeSingleFromBlacklist(oldEntry);
        if (!response.isSuccess())
            return HTTPResponse.<BlacklistEntry[]>returnFailure(response.getError());

        // add the new entry
        addSingleToBlacklist(newEntry);
        return HTTPResponse.<BlacklistEntry[]>returnSuccess(entries);
    }

    /** remove multiple entries from the blacklist
     * @param entries the entries to remove
     * @return HTTPResponse
     */
    public HTTPResponse removeMultipleFromBlacklist(BlacklistEntry[] entries) {
        for (BlacklistEntry entry : entries) {
            HTTPResponse response = removeSingleFromBlacklist(entry);
            if (!response.isSuccess())
                return HTTPResponse.<BlacklistEntry[]>returnFailure(response.getError());
        }
        return HTTPResponse.<BlacklistEntry[]>returnSuccess(entries);
    }

    /** remove a single entry from the blacklist
     * @param entry the entry to remove
     * @return HTTPResponse
     */
    public HTTPResponse removeSingleFromBlacklist(BlacklistEntry entry) {
        Optional<BlacklistEntry> data = repository.findById(entry.getEntry());
        if (data.isEmpty())
            return HTTPResponse.<BlacklistEntry>returnFailure("could not find entry: " + entry.getEntry());
        repository.delete(data.get());
        return HTTPResponse.<BlacklistEntry>returnSuccess(entry);
    }
}
