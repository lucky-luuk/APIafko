package AfkoAPI;

import AfkoAPI.Model.Abbreviation;
import AfkoAPI.Model.Organisation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class AbbreviationControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void checkDefaultResponseIsEmptyString() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(content().string(containsString("")));
    }

    @Test
    public void checkChangeAbbreviationFailsWithArrayOfSize3() throws Exception {
        String json = "[{\"name\": \"test\", \"description\":\"test\", \"organisations\":[],\"createdBy\":null,\"isUnderReview\":true}," +
                      "{\"name\": \"test\", \"description\":\"test\", \"organisations\":[],\"createdBy\":null,\"isUnderReview\":true}," +
                      "{\"name\": \"test\", \"description\":\"test\", \"organisations\":[],\"createdBy\":null,\"isUnderReview\":true}]";

        MvcResult result = mockMvc.perform(put("/abbreviation").contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andReturn();

        assertThat(result.getResponse().getContentAsString().equals("{\"response\":\"FAILURE\",\"error\":\"input length is not 2\",\"data\":null}")).isTrue();
    }

}
