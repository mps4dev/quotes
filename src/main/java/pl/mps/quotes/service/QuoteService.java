package pl.mps.quotes.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mps.quotes.mapper.QuoteMapper;
import pl.mps.quotes.model.Quote;
import pl.mps.quotes.model.QuoteDto;
import pl.mps.quotes.repository.QuoteRepository;
import pl.mps.quotes.utils.RandomUtils;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class QuoteService {

    private static final Random RANDOM = new Random();

    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Optional<Quote> findRandom() {

        long count = quoteRepository.count();
        long id = RandomUtils.nextLong(0, count) + 1;
        return count > 0 ? quoteRepository.findById(id) : Optional.empty();
    }

    public Quote add(QuoteDto dto) {
        return quoteRepository.save(QuoteMapper.map(dto));
    }

    public boolean delete(Long id) {
        Optional<Quote> oQuote = quoteRepository.findById(id);
        if (oQuote.isPresent()) {
            quoteRepository.delete(oQuote.get());
            return true;
        }
        return false;
    }

    public List<Quote> findByAuthor(String author) {
        return quoteRepository.findAllByAuthorContaining(author);
    }

    public Optional<Quote> findById(Long id) {
        return quoteRepository.findById(id);
    }
}
