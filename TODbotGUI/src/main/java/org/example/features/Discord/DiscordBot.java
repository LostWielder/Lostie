package org.example.features.Discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DiscordBot {
    public String Token;
    JDABuilder jdaBuilder = JDABuilder.createDefault(Token);
    private final MessageEventListener MEL = new MessageEventListener();
    private final EventListeners EL = new EventListeners();

    Path token = Paths.get("","LoginTexts/DiscordLogin.txt");

    static JDA jda;



    public JDA LaunchDiscordBot() throws IOException {

        String tokenT = token.toAbsolutePath().toString();
        File LoginTexts = new File(tokenT);
        BufferedReader loginInfo = new BufferedReader(new FileReader(LoginTexts));

        while (loginInfo.ready()){
            Token = loginInfo.readLine();
        }
        loginInfo.close();

        jda = jdaBuilder
                .createDefault(Token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(EL, MEL)
               // .setEventPassthrough(true)
                .build();

        //jda.upsertCommand("test","Testing the Slash command function")
               // .setGuildOnly(true)
               // .queue();
        return jda;
    }

}
