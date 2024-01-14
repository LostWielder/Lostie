package org.example;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import org.example.features.Discord.MessageEventListener;
import org.example.features.Discord.TwitchToDiscordInteractions;
import org.example.features.Twitch.Systems.CommandReplySystem;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TODHome extends JFrame {
    CommandReplySystem crs = new CommandReplySystem(new SimpleEventHandler(), new Bot());
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
                crs.addedRandom(randomToAdd.getText());
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

    }

    public void Display() {


        this.setIconImage(img.getImage());
        this.setContentPane(this.panel1);
        this.setTitle("TODBot 0.1.1");
        connected.setForeground(Color.red);
        this.setSize(550,250);
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