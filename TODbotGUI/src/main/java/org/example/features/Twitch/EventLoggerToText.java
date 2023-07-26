package org.example.features.Twitch;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.CheerEvent;
import com.github.twitch4j.chat.events.channel.RaidEvent;
import com.github.twitch4j.chat.events.channel.SubscriptionEvent;
import org.example.Bot;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EventLoggerToText {

    private final Bot bot;
    /**
     * Register events of this class with the EventManager/EventHandler
     *
     * @param eventHandler SimpleEventHandler
     * @param bot
     */
    public EventLoggerToText(SimpleEventHandler eventHandler, Bot bot) {
        this.bot = bot;
        eventHandler.onEvent(SubscriptionEvent.class, event -> {
            try {
                onSubscription(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        eventHandler.onEvent(RaidEvent.class, event -> {
            try {
                onRaid(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        eventHandler.onEvent(CheerEvent.class, event -> {
            try {
                onBits(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }


    public void onSubscription(SubscriptionEvent event) throws IOException {
        Path currentRelativePath = Paths.get("","EventTexts/Subscriptions.txt");
        String s = currentRelativePath.toAbsolutePath().toString();
        FileWriter outputSelections = new FileWriter(s);
        outputSelections.write(event.getUser().getName());

        outputSelections.close();
    }

    public void onRaid(RaidEvent event) throws IOException {
        Path currentRelativePath = Paths.get("","EventTexts/Raid.txt");
        String s = currentRelativePath.toAbsolutePath().toString();
        FileWriter outputSelections = new FileWriter(s);
        outputSelections.write(event.getRaider().getName() + " is raiding with " + event.getViewers() + " viewers! ");

        outputSelections.close();
    }

    public void onBits(CheerEvent event) throws IOException {
        Path currentRelativePath = Paths.get("","EventTexts/Bits.txt");
        String s = currentRelativePath.toAbsolutePath().toString();
        FileWriter outputSelections = new FileWriter(s);
        outputSelections.write(event.getUser().getName() + " used " + event.getBits() + "Bits! Thanks!");

        outputSelections.close();
    }


}
