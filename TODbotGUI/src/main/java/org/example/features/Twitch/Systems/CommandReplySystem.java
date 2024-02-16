package org.example.features.Twitch.Systems;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.common.enums.CommandPermission;
import com.github.twitch4j.extensions.domain.Channel;
import com.github.twitch4j.helix.domain.ChannelInformation;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.example.Bot;
import org.example.features.Twitch.Games.TimedMessages;

import java.io.IOException;
import java.util.*;
import java.util.List;

public class CommandReplySystem extends ListenerAdapter {
    private final Bot bot;
    String inputMessage;






    /**
     * Register events of this class with the EventManager/EventHandler
     *
     * @param eventHandler SimpleEventHandler
     * @param bot
     */
    public CommandReplySystem(SimpleEventHandler eventHandler, Bot bot) {
        this.bot = bot;
        eventHandler.onEvent(ChannelMessageEvent.class, shoutout -> shoutoutCommand(shoutout));
        eventHandler.onEvent(ChannelMessageEvent.class, socials -> socialLinks(socials));
        eventHandler.onEvent(ChannelMessageEvent.class, promo -> botPromo(promo));
        eventHandler.onEvent(ChannelMessageEvent.class, conversion -> internationalConversion(conversion));

        System.out.println("CRS Bot Loaded");

    }




    /**
     * Respond to chat messages based on input
     */

    public void shoutoutCommand(ChannelMessageEvent input)  {

        inputMessage = input.getMessage();
        String name;


        if(inputMessage.contains("!so") && input.getMessageEvent().getClientPermissions().contains(CommandPermission.MODERATOR)) {
            if (inputMessage.contains("@")){
                name = inputMessage.substring(5);
            }else{
                name = inputMessage.substring(4);
            }



            this.bot.getTwitchClient().getChat().sendMessage(input.getChannel().getName(), name + " Can be found at https://twitch.tv/"+name);



        }

    }

    public void socialLinks(ChannelMessageEvent input) {
        inputMessage = input.getMessage();

        if (inputMessage.contains("!discord")) {
            this.bot.getTwitchClient().getChat().sendMessage(input.getChannel().getName(), "https://discord.gg/4XyKTjKjs3");
        }
    }

    public void botPromo(ChannelMessageEvent input){
       inputMessage = input.getMessage();
        if (inputMessage.trim().equalsIgnoreCase("!bot")) {
            this.bot.getTwitchClient().getChat().sendMessage(input.getChannel().getName(),"LostDealer is a bot currently being developed by Lost," +
                    " and is written in Java using JDA and Twitch4J. It connects and posts messages and stats from Twitch to Discord!" +
                    " If you're interested feel free to join the testing server on discord for updates," +
                    " and a future public release date! https://discord.gg/bthp7JAgxA ");
        }

    }

    public void internationalConversion(ChannelMessageEvent input) {
         inputMessage = input.getMessage().toLowerCase();
        if (inputMessage.contains("mph")) {
            String s = inputMessage;
            String clean = s.replaceAll("\\D+", "");
            Double converted = Integer.parseInt(clean) * 1.609344;
            this.bot.getTwitchClient().getChat().sendMessage(input.getChannel().getName(), Math.round(converted) + " km/h");

        } else if (inputMessage.contains("kmph")) {
            String s = inputMessage;
            String clean = s.replaceAll("\\D+", "");
            Double converted = Integer.parseInt(clean) / 1.609344;
            this.bot.getTwitchClient().getChat().sendMessage(input.getChannel().getName(), Math.round(converted) + " mph");

        } else if (inputMessage.contains("gallons")) {
            String s = inputMessage;
            String clean = s.replaceAll("\\D+", "");
            Double converted = Integer.parseInt(clean) * 3.785412;
            this.bot.getTwitchClient().getChat().sendMessage(input.getChannel().getName(), Math.round(converted) + " liters");
        } else if (inputMessage.contains("liters")) {
            String s = inputMessage;
            String clean = s.replaceAll("\\D+", "");
            Double converted = Integer.parseInt(clean) * 0.264172;
            this.bot.getTwitchClient().getChat().sendMessage(input.getChannel().getName(), Math.round(converted) + " gallons");

        }else if (inputMessage.contains("lbs")) {
            String s = inputMessage;
            String clean = s.replaceAll("\\D+", "");
            Double converted = Integer.parseInt(clean) * 0.45359237;
            this.bot.getTwitchClient().getChat().sendMessage(input.getChannel().getName(), Math.round(converted) + " kgs");

        }else if (inputMessage.contains("kgs")) {
            String s = inputMessage;
            String clean = s.replaceAll("\\D+", "");
            Double converted = Integer.parseInt(clean) * 2.2046226218;
            this.bot.getTwitchClient().getChat().sendMessage(input.getChannel().getName(), Math.round(converted) + " lbs");

        }
    }

    public void sendMessage(String message){
        this.bot.getTwitchClient().getChat().sendMessage("TheLostWielder",message);

    }












/*
        UserList resultList = this.bot.getTwitchClient().getHelix().getUsers(null, null, Arrays.asList(works.substring(5).trim())).execute();
            resultList.getUsers().forEach(user -> {
                System.out.println(user);
            });
*/
    }
