package pl.mps.quotes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuoteDto {

    private String author;
    private String content;

}
