package org.example.features.Twitch.Notifications;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import org.example.Bot;
import org.example.features.Twitch.Games.TimedMessages;
import org.example.features.Twitch.Systems.CommandReplySystem;

import java.io.IOException;

public class ChannelNotificationOnLive {


    private final Bot bot;


    /**
     * Register events of this class with the EventManager/EventHandler
     *
     * @param bot          the bot instance
     * @param eventHandler SimpleEventHandler
     */
    public ChannelNotificationOnLive(Bot bot, SimpleEventHandler eventHandler) {
        this.bot = bot;
        eventHandler.onEvent(ChannelGoLiveEvent.class, event -> {
            try {
                onGoLive(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    /**
     * Subscribe to the Follow Event
     */
    public void onGoLive(ChannelGoLiveEvent event) throws IOException {
        String message = String.format(
                "%s is now live!",
                event.getChannel().getName()

        );
        System.out.println("Live");
        //bot.getTwitchClient().getChat().sendMessageToDiscord(event.getChannel().getName(), message);
    }




}