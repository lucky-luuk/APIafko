package AfkoAPI;

import AfkoAPI.services.TrimListService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class TrimListServiceTest {

    @Test
    public void trimListBy3ReturnsListOfSize3() {
        TrimListService<Integer> service = new TrimListService<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) { list.add(i); }
        assert(service.trimListByAmount(list, 3).size() == 3);
    }
}
