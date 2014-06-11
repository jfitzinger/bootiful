package bootiful.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import bootiful.persistence.entity.Card;


public interface CardRepository extends CrudRepository<Card, Long> {
}
