package pl.mps.quotes.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mps.quotes.model.Quote;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> {

    Optional<Quote> findById(Long id);

    List<Quote> findAllByAuthorContaining(String str);

}
