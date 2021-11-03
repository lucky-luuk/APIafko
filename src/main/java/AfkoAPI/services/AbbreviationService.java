package AfkoAPI.services;

import AfkoAPI.Model.Abbreviation;

import java.util.List;

public class AbbreviationService {

    public static List<Abbreviation> trimListByAmount(List<Abbreviation> input, String amount) {
        if (!amount.equals("") && input.size() > 0) {
            int a = Integer.parseInt(amount);
            if (a > 0 && a < input.size())
                return input.subList(0, a);
        }
        return input;
    }
}
