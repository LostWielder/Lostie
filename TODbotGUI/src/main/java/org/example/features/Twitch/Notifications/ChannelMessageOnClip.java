package org.example.features.Twitch.Notifications;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.common.events.domain.EventChannel;
import com.github.twitch4j.events.ChannelClipCreatedEvent;
import org.example.Bot;
import org.example.features.Discord.MessageEventListener;

public class ChannelMessageOnClip {

    private final Bot bot;
    private final MessageEventListener MEL = new MessageEventListener();


    /**
     * Register events of this class with the EventManager/EventHandler
     *
     * @param eventHandler SimpleEventHandler
     * @param bot bot
     */

    public ChannelMessageOnClip(Bot bot, SimpleEventHandler eventHandler) {
        this.bot = bot;
        eventHandler.onEvent(ChannelClipCreatedEvent.class, this::postClipInChat);
        eventHandler.onEvent(ChannelClipCreatedEvent.class, clip -> {
            try {
                postClipInDiscord(clip);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void postClipInChat(ChannelClipCreatedEvent clip){
        bot.getTwitchClient().getChat().sendMessage(clip.getChannel().getName(),clip.getCreatingUser()+" Has created a clip! Check it out: "+clip.getClip().getUrl());
    }

    public void postClipInDiscord(ChannelClipCreatedEvent clip) throws InterruptedException {
        MEL.postClipsToDiscord(clip.getClip().getUrl() + " Created by: " + clip.getCreatingUser());
    }
}
