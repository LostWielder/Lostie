package org.example;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import org.example.features.Discord.MessageEventListener;
import org.example.features.Discord.TwitchToDiscordInteractions;
import org.example.features.Twitch.CommandReplySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TODHome extends JFrame {
    CommandReplySystem crs = new CommandReplySystem(new SimpleEventHandler(), new Bot());
    private final MessageEventListener MEL = new MessageEventListener();
    TwitchToDiscordInteractions ttdi = new TwitchToDiscordInteractions( new Bot(),new SimpleEventHandler());
    private JPanel panel1;
    private JTextField txtname;
    private JButton twitchButton;
    private JTextField goingLiveTextInput;
    private JButton updateButton;
    private JButton sendToDiscordButton;
    private JTextField messageToDiscord;
    private JLabel discordMessageSent;
    private JLabel latestAction;

    private  JLabel connected;
    private JLabel TODbot;
    Path currentRelativePath = Paths.get("", "Icons/1_56.png");
    String s = currentRelativePath.toAbsolutePath().toString();
    private ImageIcon img = new ImageIcon(s);


    public TODHome()  {



        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ttdi.liveMessage = goingLiveTextInput.getText();
                latestAction.setText("Discord live message updated");
                goingLiveTextInput.setText("");
            }
        });
        sendToDiscordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MEL.sendMessageToDiscord(messageToDiscord.getText());
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                latestAction.setText( "Message sent to Discord");
                messageToDiscord.setText("");
            }
        });
    }

    public void Display() {

        this.setIconImage(img.getImage());
        this.setContentPane(this.panel1);
        this.setTitle("TODBot");connected.setForeground(Color.red);
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

}