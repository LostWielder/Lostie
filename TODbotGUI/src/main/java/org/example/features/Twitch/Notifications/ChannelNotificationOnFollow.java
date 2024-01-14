package org.example.features.Twitch.Notifications;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.FollowEvent;

public class ChannelNotificationOnFollow {

    /**
     * Register events of this class with the EventManager/EventHandler
     *
     * @param eventHandler SimpleEventHandler
     */
    public ChannelNotificationOnFollow(SimpleEventHandler eventHandler) {
        eventHandler.onEvent(FollowEvent.class, event -> onFollow(event));
    }

    /**
     * Subscribe to the Follow Event
     */
    public void onFollow(FollowEvent event) {


        event.getTwitchChat().sendMessage(event.getChannel().getName(), "Thank you for the Follow! Feel free to say hi!");
    }

}