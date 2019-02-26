package pl.mps.quotes.mapper;

import pl.mps.quotes.model.Quote;
import pl.mps.quotes.model.QuoteDto;

public interface QuoteMapper {

    static Quote map(QuoteDto dto) {
        Quote quote = new Quote();
        quote.setAuthor(dto.getAuthor());
        quote.setContent(dto.getContent());
        return quote;
    }

    static QuoteDto map(Quote quote) {
        return new QuoteDto(quote.getAuthor(), quote.getContent());
    }
}
