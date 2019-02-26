package pl.mps.quotes.controller;

import org.springframework.web.bind.annotation.*;
import pl.mps.quotes.model.Quote;
import pl.mps.quotes.model.QuoteDto;
import pl.mps.quotes.service.QuoteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("quote")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @RequestMapping(path = "find", method = RequestMethod.GET)
    public Optional<Quote> findRandom() {
        return quoteService.findRandom();
    }

    @RequestMapping(path = "author", method = RequestMethod.GET)
    public List<Quote> findByAuthor(@RequestParam("author") String author) {
        return quoteService.findByAuthor(author);
    }

    @RequestMapping(path = "add", method = RequestMethod.POST)
    public Quote add(@RequestBody QuoteDto dto) {
        return quoteService.add(dto);
    }


    @RequestMapping(path = "find", method = RequestMethod.DELETE)
    public boolean deactivate(Long id) {
        return quoteService.delete(id);
    }

}
