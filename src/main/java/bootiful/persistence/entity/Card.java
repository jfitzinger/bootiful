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


	public Card(String subject, String text) {
		this.subject = subject;
		this.text = text;
	}
	
	public long getId() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

	public String getText() {
		return text;
	}

	protected Card() {
	}

	@Override
	public String toString() {
		return String.format("Card[id=%d, subject='%s', text='%s']", id,
				subject, text);
	}

}
