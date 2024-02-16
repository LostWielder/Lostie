package org.example.features.Twitch.Games;


import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.common.enums.CommandPermission;
import org.example.Bot;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TimedMessages {
    private final Bot bot;

    int messageNumber = 0;
    Random r = new Random();

    int duration = 1200000; // Default: 1200000 (20 min)

    Path randomMessages = Paths.get("","EventTexts/randoms.txt");

    public List<String> messages = new ArrayList<>();
    public static Timer timer = new Timer();


    /**
     * Register events of this class with the EventManager/EventHandler
     *
     * @param eventHandler SimpleEventHandler
     * @param bot
     */
    public TimedMessages(SimpleEventHandler eventHandler, Bot bot) {
        this.bot = bot;
        eventHandler.onEvent(ChannelMessageEvent.class, event -> {
            try {
                startRandoms(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private boolean runTimer = true;

    //Populates list for timed messages if moods is disabled
    public void populateRandoms() throws IOException {
        String randomsT = randomMessages.toAbsolutePath().toString();
        File randomText = new File(randomsT);
        BufferedReader BR = new BufferedReader(new FileReader(randomText));

        while (BR.ready()){
            messages.add(BR.readLine());
        }
        BR.close();
        Collections.shuffle(messages);
        System.out.println(messages);
    }

    //Adds message to .txt
    public void addRandom(String addedMessage) throws IOException {
        String randomsT = randomMessages.toAbsolutePath().toString();
        File randomText = new File(randomsT);
        BufferedReader BR = new BufferedReader(new FileReader(randomText));
        StringBuilder random = new StringBuilder();



        while (BR.ready()){
            random.append(BR.readLine()).append("\n");
        }
        random.append(addedMessage);
        FileWriter randomsOut = new FileWriter(randomsT);
        randomsOut.write(String.valueOf(random));
        BR.close();
        randomsOut.close();

    }
    public List<String> getMessages() {
        return messages;
    }

    //Auto start for timed messages
    public void sendMessageOnTimer() throws IOException {
        if(messages.isEmpty()){
            populateRandoms();


        }
            sendDefaultMessages();

    }

    //Ends timed messages
    public void endTimer(){
        timer.cancel();
        timer = new Timer();


    }
    //adds message to defaults through GUI
    public void addedRandom(String addedMessage) throws IOException {
        messages.add(addedMessage);
        addRandom(addedMessage);
    }

    //Backup Manual start for timed messages
    public void startRandoms(ChannelMessageEvent event) throws IOException {
        if (event.getMessage().contains("!start") && event.getMessageEvent().getClientPermissions().contains(CommandPermission.BROADCASTER)) {
            sendMessageOnTimer();
        }
    }

    //method to send messages if moods is disabled
    public void sendDefaultMessages(){
        TimerTask task = new TimerTask() {
            public void run() {
                messageNumber = r.nextInt(messages.size());


                bot.getTwitchClient().getChat().sendMessage("TheLostWielder", messages.get(messageNumber));
                // System.out.println(TM.getMessages().get(messageNumber));
                //System.out.println(TM.messages.size());
            }
        };

        timer.scheduleAtFixedRate(task, 0, duration );

    }





}//end of file
