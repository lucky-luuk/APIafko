package AfkoAPI.services;

import AfkoAPI.Model.Abbreviation;

import java.util.List;

public class TrimListService <T> {

    /** trim a list by, makes it of size x or does not change it, gives a string because the api gave us a string
     * @param input the list to trim
     * @param amount the max size of the new list
     * @return the trimmed list
     */
    public List<T> trimListByAmount(List<T> input, String amount) {
        if (!amount.equals("") && input.size() > 0) {
            int a = Integer.parseInt(amount);
            if (a > 0 && a < input.size())
                return input.subList(0, a);
        }
        return input;
    }
}
