package org.example.features.Discord;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MessageEventListener extends ListenerAdapter {




    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);
        System.out.println("user sent " + event.getMessage().getContentRaw());
        if (event.getMessage().getContentRaw().equals("hi") && event.getChannel().getName().equals("test-channel-1")) {
            event.getChannel().sendMessage("Hi Buddy!" + "\n" + "testing returns").queue();




        }


    }

    public void sendMessageToDiscord(String message) throws InterruptedException {
        DiscordBot.jda.awaitReady();
        List<TextChannel> channels = DiscordBot.jda.getTextChannelsByName("test-channel-1", false);
        for (TextChannel ch : channels) {
            ch.sendMessage(message).queue();
        }

    }

    public void onGoingLive(String notification) throws InterruptedException {
        DiscordBot.jda.awaitReady();
        List<TextChannel> channels = DiscordBot.jda.getTextChannelsByName("\uD83D\uDCFDlost-going-live", false);
        for (TextChannel ch : channels) {
            ch.sendMessage(notification).queue();
        }

    }

    public void onSlashCommandInteraction(SlashCommandInteractionEvent toTwitch){
        toTwitch.getName();

        System.out.println(toTwitch.getOptions().get(0).getAsString());


    }

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {

        super.onMessageReactionAdd(event);

        if(event.getGuildChannel().asTextChannel().getName().equals("role-distribution")) {
            Message message = event.retrieveMessage().complete();
            String messageString = message.getContentStripped();
            Role role = event.getGuild().getRolesByName(messageString, false).get(0);
            event.getGuild().addRoleToMember(event.getUser(), role).queue();
            System.out.println(messageString);




        }

    }

    public void postStatsToDiscord(String message) throws InterruptedException {
        DiscordBot.jda.awaitReady();
        List<TextChannel> channels = DiscordBot.jda.getTextChannelsByName("test-channel-1", false);
        for (TextChannel ch : channels) {
            ch.sendMessage(message).queue();
        }

    }


}