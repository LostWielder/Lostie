package org.example.features.Discord;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.*;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import com.github.twitch4j.events.ChannelGoOfflineEvent;
import org.example.Bot;
import org.example.features.Twitch.Games.TimedMessages;
import org.example.features.Twitch.Systems.CommandReplySystem;

import java.io.IOException;

public class TwitchToDiscordInteractions  {

    private final Bot bot;
    private final MessageEventListener MEL = new MessageEventListener();
    private final CommandReplySystem CRS;

    private TimedMessages timedMessages;


    public  int raidCount = 0;
   public String raiders = "Raiders: ";
    public int followCount = 0;
    public String followers = "Followers: ";
    public int bitsEarned = 0;
    public String bitDonators = "Bit Donators: ";
    public int subCount = 0;
    public String subscribers = "Subscribers: ";
    public int messages = 0;
    public static String liveMessage = "Lost has gone live!";


    /**
     * Register events of this class with the EventManager/EventHandler
     *
     * @param bot          the bot instance
     * @param eventHandler SimpleEventHandler
     */
    public TwitchToDiscordInteractions(Bot bot, SimpleEventHandler eventHandler, CommandReplySystem CRS) {
        this.bot = bot;
        this.CRS = CRS;
        eventHandler.onEvent(ChannelMessageEvent.class, this::twitchToDiscord);
        eventHandler.onEvent(FollowEvent.class, this::statsToDiscordFollow);
        eventHandler.onEvent(SubscriptionEvent.class, this::statsToDiscordSubs);
        eventHandler.onEvent(CheerEvent.class, this::statsToDiscordBits);
        eventHandler.onEvent(RaidEvent.class, this::statsToDiscordRaids);
        eventHandler.onEvent(ChannelGoOfflineEvent.class, this::sendStatsToDiscord);
        eventHandler.onEvent(ChannelGoLiveEvent.class, event -> {
            try {
                onGoLive(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void twitchToDiscord(ChannelMessageEvent input) {
        messages++;

        String inputMessage = input.getMessage();
        String messageToPost = inputMessage.substring(6);
        if (inputMessage.contains("!post")) {

            try {
                MEL.sendMessageToDiscord(messageToPost);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onGoLive(ChannelGoLiveEvent event) throws IOException {

        try {
            MEL.onGoingLive( "@everyone "+liveMessage+" https://www.twitch.tv/thelostwielder");
        } catch (InterruptedException e) {
           throw new RuntimeException(e);
        }
        System.out.println("You are now live");
        timedMessages.sendMessageOnTimer();
        //bot.getTwitchClient().getChat().sendMessageToDiscord(event.getChannel().getName(), message);
    }

    public void statsToDiscordFollow(FollowEvent  event){

            followCount++;
            followers = followers + event.getUser().getName() + " ";


    }

    public void statsToDiscordSubs(SubscriptionEvent event){
            subCount++;
            subscribers = subscribers + event.getUser().getName() + " ";
            System.out.println(event.getUser().getName() + " Subscribed");
    }

    public void statsToDiscordBits(CheerEvent event){
            bitsEarned = bitsEarned + event.getBits();
            bitDonators = bitDonators + event.getUser().getName() + " used " + event.getBits() + " ";
            System.out.println(event.getBits() + " Donated by " + event.getUser().getName());

    }

    public void statsToDiscordRaids(RaidEvent  event){
            raidCount++;
            raiders = raiders + event.getRaider().getName() + " raided with " + event.getViewers() + " ";
            System.out.println("Raid from " + event.getRaider().getName() + " with " + event.getViewers() + " raiders ");

    }

    public void sendStatsToDiscord(ChannelGoOfflineEvent event){
        try {
            MEL.postStatsToDiscord(
            "Follows: " + followCount +"\n"
                    + followers + "\n"
            + "Subs: " + subCount + "\n"
                    + subscribers + "\n"
            + "Bits: "+ bitsEarned + "\n"
                    + bitDonators + "\n"
            + "Raids: "+ raidCount + "\n"
                    + raiders + "\n"
            + "Messages: " + messages +"\n"
                    +"\n"
                    +"\n"
            + "Good stream today! Keep it up! :thumbsup: ");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        timedMessages.endTimer();
    }




}
