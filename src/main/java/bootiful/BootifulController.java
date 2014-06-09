package bootiful;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BootifulController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}
