package org.example.features.Twitch.Games;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import org.example.Bot;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class QuoteSystem {

    private final Bot bot;
    /**
     * Register events of this class with the EventManager/EventHandler
     *
     * @param eventHandler SimpleEventHandler
     * @param bot
     */
    public QuoteSystem(SimpleEventHandler eventHandler, Bot bot) {
        this.bot = bot;
        eventHandler.onEvent(ChannelMessageEvent.class, quote -> {
            try {
                readQuotesFromTxt(quote);
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });

        eventHandler.onEvent(ChannelMessageEvent.class, addQuote -> {
            try {
                addQuotesToTxt(addQuote);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void readQuotesFromTxt(ChannelMessageEvent quote) throws IOException, URISyntaxException {



        if(quote.getMessage().contains("!quote")){
            System.out.println("Quote Event Fired");

            Path currentRelativePath = Paths.get("","Quotes.txt");
            String s = currentRelativePath.toAbsolutePath().toString();
           // System.out.println("Current absolute path is: " + s);


            File quotes = new File(s);
            if(quotes.exists()){
                System.out.println("Found Quotes file");
            }

            BufferedReader quotesIn = new BufferedReader(new FileReader(quotes));
            int quoteNumber = Integer.parseInt(quote.getMessage().substring(7));
            System.out.println("Loaded");
            List<String> listOfQuotes = new ArrayList<String>();
            String quoteToAdd = "";
           // System.out.println(line);
            while (quotesIn.ready()){
                quoteToAdd = quotesIn.readLine();
                listOfQuotes.add(quoteToAdd);

            }
            quotesIn.close();
            System.out.println(listOfQuotes);
            this.bot.getTwitchClient().getChat().sendMessage(quote.getChannel().getName(), listOfQuotes.get(quoteNumber));
        }

    }

    public void addQuotesToTxt(ChannelMessageEvent addQuote) throws IOException {
        Path currentRelativePath = Paths.get("","Quotes.txt");
        String s = currentRelativePath.toAbsolutePath().toString();
       // System.out.println("Current absolute path is: " + s);
        if (addQuote.getMessage().contains("!addquote")) {
            File quotes = new File(s);
            BufferedReader quotesIn = new BufferedReader(new FileReader(quotes));
            StringBuilder listQuotes = new StringBuilder();
            while (quotesIn.ready()) {
                listQuotes.append(quotesIn.readLine()).append("\n");
            }
            listQuotes.append(addQuote.getMessage().substring(10)+"\n");
            FileWriter quotesOut = new FileWriter(s);
            quotesOut.write(String.valueOf(listQuotes));
            quotesIn.close();
            quotesOut.close();
            System.out.println(listQuotes);
        }
    }
}
