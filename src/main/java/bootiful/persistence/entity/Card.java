package bootiful.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Card {

	@Id
	private long id;
	private String subject;
	private String text;

	protected Card() {
	}

	public Card(String subject, String text) {
		this.subject = subject;
		this.text = text;
	}

	@Override
	public String toString() {
		return String.format("Card[id=%d, subject='%s', text='%s']", id,
				subject, text);
	}

}
