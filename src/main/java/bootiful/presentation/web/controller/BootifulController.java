package bootiful.presentation.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value="/cards/{cardId}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Card> getCard(@PathVariable String cardId) {
		Card foundCard = cardRepository.findOne(Long.parseLong(cardId));
		
		 if (foundCard == null) {
	            System.out.println("Card with id " + cardId + " not found");
	            return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
	        }
		 return new ResponseEntity<Card>(foundCard, HttpStatus.OK);
	}

}
