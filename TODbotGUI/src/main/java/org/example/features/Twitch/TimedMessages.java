package org.example.features.Twitch;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import org.example.Bot;

import java.util.Timer;
import java.util.TimerTask;

public class TimedMessages {
    private final Bot bot;

    /**
     * Register events of this class with the EventManager/EventHandler
     *
     * @param eventHandler SimpleEventHandler
     * @param bot
     */

    public TimedMessages(SimpleEventHandler eventHandler, Bot bot) {
        this.bot = bot;
       // eventHandler.onEvent(ChannelMessageEvent.class, ffReply -> sendMessageOnTimer());


    }
    public void sendMessageOnTimer() {
        TimerTask task = new TimerTask() {
            public void run() {
                bot.getTwitchClient().getChat().sendMessage("TheLostWielder", "LostDealer is a bot currently being developed by Lost," +
                        " and is written in Java using JDA and Twitch4J. It connects and posts messages and stats from Twitch to Discord!" +
                        " If you're interested feel free to join the testing server on discord for updates," +
                        " and a future public release date! https://discord.gg/bthp7JAgxA ");

            }
        };
        Timer timer = new Timer("Timer");
        timer.scheduleAtFixedRate(task, 0, 1200000);
    }

}
