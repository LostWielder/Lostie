package org.example.features.Twitch.Games;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.common.enums.CommandPermission;
import org.example.Bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Giveaway {
    private final Bot bot;
    Random random = new Random();

    public String winner;

    List <String> enteredChatters = new ArrayList<>();
    public static List <String> gamesList = new ArrayList<>();


    /**
     * Register events of this class with the EventManager/EventHandler
     *
     * @param eventHandler SimpleEventHandler
     * @param bot
     */

    public Giveaway(SimpleEventHandler eventHandler,Bot bot) {
        this.bot = bot;
        eventHandler.onEvent(ChannelMessageEvent.class, enter -> GiveawayEntrance(enter));
        eventHandler.onEvent(ChannelMessageEvent.class, draw -> GiveawayDraw(draw));
    }

    public void GiveawayEntrance(ChannelMessageEvent event){
        if (event.getMessage().contains("!giveaway") && !enteredChatters.contains(event.getUser().getName())){
            enteredChatters.add(event.getUser().getName());
            bot.getTwitchClient().getChat().sendMessage(event.getChannel().getName(),event.getUser().getName() + " Has entered the Giveaway! Good Luck!");
        }else if (event.getMessage().contains("!giveaway") && enteredChatters.contains(event.getUser().getName())){
            bot.getTwitchClient().getChat().sendMessage(event.getChannel().getName(),"Sorry " +event.getUser().getName() +", but you've already entered!");
        }
    }

    public void GiveawayDraw(ChannelMessageEvent event){

        if (event.getMessage().contains("!draw") && event.getMessageEvent().getClientPermissions().contains(CommandPermission.MODERATOR)){
            int randomChatter = random.nextInt(enteredChatters.size());
            bot.getTwitchClient().getChat().sendMessage(event.getChannel().getName(),enteredChatters.get(randomChatter) + "! You Won!");
            winner = enteredChatters.get(randomChatter);
            enteredChatters.remove(randomChatter);

        }
    }
    public void GiveawayEvent(ChannelMessageEvent event){
        if (event.getMessage().contains("!bonus") && event.getMessageEvent().getClientPermissions().contains(CommandPermission.BROADCASTER)){

        }
    }

}//end of file
