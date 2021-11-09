package AfkoAPI.services;

import AfkoAPI.HTTPResponse;
import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Account;
import AfkoAPI.Repository.AccountRepository;

import java.util.Optional;

public class AccountService {

    /** checks if an account id is not equal to the string "null", if it is not checks if the account id actually exists
     *  it must be possible to not have an account, and seeing as our ids come from json, the id of an account that does
     *  not exist is "null"
     * @param accRep the repository to use
     * @param accountId the account id to check
     * @return a HTTPResponse object that either contains a failure or success
     */
    public static HTTPResponse getAccountIfIdNotNull(AccountRepository accRep, String accountId) {
        Account acc = null;
        if (!accountId.equals("null")) {
            Optional<Account> optAcc = accRep.findById(accountId);
            if (optAcc.isEmpty())
                return HTTPResponse.<Account>returnFailure("account with id: " + accountId + " does not exist");
            else acc = optAcc.get();
        }
        return HTTPResponse.<Account>returnSuccess(acc);
    }
}
