package AfkoAPI;

import AfkoAPI.Controller.AbbreviationController;
import AfkoAPI.DAO.AbbreviationDao;
import AfkoAPI.Model.Abbreviation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class AfkoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AfkoApplication.class, args);
	}
}
