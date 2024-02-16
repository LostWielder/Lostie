package org.example;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import org.example.features.Discord.MessageEventListener;
import org.example.features.Discord.TwitchToDiscordInteractions;
import org.example.features.Twitch.Games.Giveaway;
import org.example.features.Twitch.Games.TimedMessages;
import org.example.features.Twitch.Systems.CommandReplySystem;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class TODHome extends JFrame {
    CommandReplySystem crs = new CommandReplySystem(new SimpleEventHandler(), new Bot());
    TimedMessages timedMessages = new TimedMessages(new SimpleEventHandler(),new Bot());
    private final MessageEventListener MEL = new MessageEventListener();
    TwitchToDiscordInteractions ttdi = new TwitchToDiscordInteractions( new Bot(),new SimpleEventHandler(),crs);
    // TTS hw = new TTS();

    private JPanel panel1;
    private JPanel About;
    private JPanel Bot;
    private JTabbedPane BaseFrame;
    private JButton updateButton;
    private JButton sendToDiscordButton;
    private JButton addRandom;
    private JTextField goingLiveTextInput;
    private JTextField randomToAdd;
    private JTextField messageToDiscord;
    private JLabel latestAction;
    private JLabel Version;
    private  JLabel connected;
    private JLabel twitchApiDev;
    private JLabel discordapiDev;
    private JLabel myDev;
    private JPanel Giveaway;
    private JTextField giveawayGame4;
    private JTextField giveawayGame3;
    private JTextField giveawayGame2;
    private JTextField giveawayGame1;
    private JLabel giveawayStatus;
    private JButton submitButton1;
    private JButton submitButton2;
    private JButton submitButton3;
    private JButton submitButton4;
    private JButton giveawayShuffle;
    private JButton revealButton;
    private JButton revealButton1;
    private JButton revealButton2;
    private JButton removeButton;
    private JButton removeButton1;
    private JButton removeButton2;
    private JButton removeButton3;

    Path currentRelativePath = Paths.get("", "Icons/1_56.png");
    String s = currentRelativePath.toAbsolutePath().toString();
    private ImageIcon img = new ImageIcon(s);
    int i = 0;


    public TODHome()  {


        twitchApiDev.setText("Twitch4J: https://twitch4j.github.io");
        discordapiDev.setText("Discord API: https://jda.wiki");
        myDev.setText("TODbot developed by: Jaron Swartz - https://github.com/LostWielder");

        addRandom.addActionListener(e -> {
            try {
                timedMessages.addedRandom(randomToAdd.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            randomToAdd.setText("");
        });


        updateButton.addActionListener(e -> {
            ttdi.liveMessage = goingLiveTextInput.getText();
            latestAction.setText("Discord live message updated");
            goingLiveTextInput.setText("");
        });
        sendToDiscordButton.addActionListener(e -> {

            try {
                MEL.sendMessageToDiscord(messageToDiscord.getText());
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            latestAction.setText( "Message sent to Discord");
            messageToDiscord.setText("");
        });

        submitButton1.addActionListener(e-> {
            org.example.features.Twitch.Games.Giveaway.gamesList.add(giveawayGame1.getText());
            giveawayGame1.setText("");
        });
        submitButton2.addActionListener(e-> {
            org.example.features.Twitch.Games.Giveaway.gamesList.add(giveawayGame2.getText());
            giveawayGame2.setText("");
        });

        submitButton3.addActionListener(e-> {
            org.example.features.Twitch.Games.Giveaway.gamesList.add(giveawayGame3.getText());
            giveawayGame3.setText("");
        });

        submitButton4.addActionListener(e-> {
            org.example.features.Twitch.Games.Giveaway.gamesList.add(giveawayGame4.getText());
            giveawayGame4.setText("");
        });
        giveawayShuffle.addActionListener(e->{
            Collections.shuffle(org.example.features.Twitch.Games.Giveaway.gamesList);
            giveawayStatus.setText(org.example.features.Twitch.Games.Giveaway.gamesList.get(0));
            giveawayGame2.setText("******");
            giveawayGame3.setText("******");
            giveawayGame4.setText("******");
        });
        revealButton.addActionListener(e->{
            giveawayGame2.setText(org.example.features.Twitch.Games.Giveaway.gamesList.get(1));
        });
        revealButton1.addActionListener(e->{
            giveawayGame3.setText(org.example.features.Twitch.Games.Giveaway.gamesList.get(2));
        });
        revealButton2.addActionListener(e->{
            giveawayGame4.setText(org.example.features.Twitch.Games.Giveaway.gamesList.get(3));
        });
        removeButton.addActionListener(e->{
            org.example.features.Twitch.Games.Giveaway.gamesList.remove(0);

        });
        removeButton1.addActionListener(e->{
            org.example.features.Twitch.Games.Giveaway.gamesList.remove(1);
            giveawayGame2.setText("");

        });
        removeButton2.addActionListener(e->{
            org.example.features.Twitch.Games.Giveaway.gamesList.remove(2);
            giveawayGame3.setText("");

        });
        removeButton3.addActionListener(e->{
            org.example.features.Twitch.Games.Giveaway.gamesList.remove(3);
            giveawayGame4.setText("");

        });


    }

    public void Display() {


        this.setIconImage(img.getImage());
        this.setContentPane(this.panel1);
        this.setTitle("TODBot 0.1.1");
        connected.setForeground(Color.red);
        this.setSize(550,550);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void showConnected(){
        connected.setVisible(true);
        connected.setText("Connected");
        connected.setForeground(Color.GREEN);
    }



} //End of file