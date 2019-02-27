package pl.mps.quotes.service;

import pl.mps.quotes.mapper.QuoteMapper;
import pl.mps.quotes.model.Quote;
import pl.mps.quotes.model.QuoteDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

public class Quoter {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws InterruptedException, IOException {
        List<Quote> quotes = getQuotes();
        checkArgument(!quotes.isEmpty(), "No quotes");

        while(true) {
            Quote randomQuote = quotes.get(RANDOM.nextInt(quotes.size()));
            System.out.println(randomQuote.getContent() + "\n" + randomQuote.getAuthor() + "\n");
            Thread.sleep(5000);
        }

        //writeToFile(quotes);
    }

    private static List<Quote> getQuotes() {
        InputStream is = Quoter.class.getClassLoader().getResourceAsStream("quotes.txt");
        return new BufferedReader(new InputStreamReader(is))
                .lines()
                .filter(line -> !line.isEmpty())
                .filter(line -> !line.startsWith("#"))
                .map(Quoter::lineToQuote)
                .map(QuoteMapper::map)
                .collect(Collectors.toList());
    }

    private static QuoteDto lineToQuote(String line) {
        String[] splittedLine = line.split("\\|");
        checkArgument(splittedLine.length == 2, "Invalid line: %s", line);
        return new QuoteDto(splittedLine[1], splittedLine[0]);
    }

    private static void writeToFile(List<Quote> quotes) throws IOException {
        Path out = Paths.get("quotes.sql");
        List<String> collect = new LinkedList<>();
        for (int i = 0; i < quotes.size(); i++) {
            Quote quote = quotes.get(i);
            quote.setId((long) i + 1);
            collect.add(parse(quote));
        }
        Files.write(out, collect, Charset.defaultCharset());
    }

    private static String parse(Quote quote) {
        return "INSERT INTO quotes VALUES (" + quote.getId() + ", '" + quote.getAuthor() + "', '" + quote.getContent() + "', current_timestamp, NULL);";
    }
}
