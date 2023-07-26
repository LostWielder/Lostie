package org.example;

import org.example.features.Discord.DiscordBot;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {



    public static void main(String[] args) throws IOException, URISyntaxException {
        TODHome todHome = new TODHome();


        todHome.Display();
        Bot bot = new Bot();
        DiscordBot dBot = new DiscordBot();
        bot.registerFeatures();
        dBot.LaunchDiscordBot();
        bot.start();
        todHome.showConnected();



/*
        JFrame f=new JFrame();//creating instance of JFrame

        JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height

        f.add(b);//adding button in JFrame

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

 */
    }
}