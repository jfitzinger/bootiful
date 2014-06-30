package bootiful.presentation.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bootiful.persistence.entity.Card;
import bootiful.persistence.repository.CardRepository;

@RestController
public class BootifulController {
	
	@Autowired
	CardRepository cardRepository;


	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@RequestMapping("/cards/{cardId}")
	public @ResponseBody Card getCard(@PathVariable String cardId) {
		Card foundCard = cardRepository.findOne(Long.parseLong(cardId));
		return foundCard;
	}

}
