package org.example.features.Twitch;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import org.example.Bot;

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
        eventHandler.onEvent(ChannelGoLiveEvent.class, this::onGoLive);

    }

    /**
     * Subscribe to the Follow Event
     */
    public void onGoLive(ChannelGoLiveEvent event)  {
        String message = String.format(
                "%s is now live!",
                event.getChannel().getName()
        );
        System.out.println("Live");
        //bot.getTwitchClient().getChat().sendMessageToDiscord(event.getChannel().getName(), message);
    }




}