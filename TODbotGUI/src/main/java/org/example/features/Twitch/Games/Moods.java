package org.example.features.Twitch.Games;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import com.github.twitch4j.events.ChannelGoOfflineEvent;
import org.example.Bot;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Moods {
    Path dispositionsText = Paths.get("","EventTexts/dispositions.txt");

    private final Bot bot;
    Path neutralReplies = Paths.get("","EventTexts/NeutralReplies.txt");



    private String currentMood = "";
    private String name;
    private int happiness = 0;
    private int sadness = 0;
    private int anger = 0;
    private boolean liked;
    private int replyCounter = 0;

    public boolean enabled = false;

    Random rand = new Random();
    Random replyRand = new Random();


    private List<String> welcomedChatters = new ArrayList<>();

    private List<String> likedChatters = new ArrayList<>();
    private List<String> dislikedChatters = new ArrayList<>();

    private List<String> neutralRepliesList = new ArrayList<>();

    private HashMap<String,Disposition> chatters = new HashMap<String,Disposition>();


    /**
     * Register events of this class with the EventManager/EventHandler
     * @param eventHandler SimpleEventHandler
     * @param bot          the bot instance
     */


    public Moods(Bot bot, SimpleEventHandler eventHandler) {
        this.bot = bot;
        eventHandler.onEvent(ChannelMessageEvent.class, greeting -> replyHandler(greeting));
        eventHandler.onEvent(ChannelMessageEvent.class, moodCheck -> checkDisposition(moodCheck));
        eventHandler.onEvent(ChannelGoOfflineEvent.class, offline -> writeDispositionDataToText(offline));
        eventHandler.onEvent(ChannelGoLiveEvent.class, online -> {
            try {
                moodInitilization(online);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }




    public String getCurrentMood() {
        return currentMood;
    }

    public void setCurrentMood(String currentMood) {
        this.currentMood = currentMood;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getSadness() {
        return sadness;
    }

    public void setSadness(int sadness) {
        this.sadness = sadness;
    }

    public int getAnger() {
        return anger;
    }

    public void setAnger(int anger) {
        this.anger = anger;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }





    public void replyHandler(ChannelMessageEvent event){
        if (!welcomedChatters.contains(event.getUser().getName().toString())){ //add list of liked and disliked users and check, then change greetings based on that
            bot.getTwitchClient().getChat().sendMessage("TheLostWielder", "Welcome in "+event.getUser().getName().toString() +"!");
            welcomedChatters.add(event.getUser().getName().toString());
            if (!chatters.containsKey(event.getUser().getName().toString())) {
                chatters.put(event.getUser().getName().toString(), new Disposition());
            }


        }else{
            chatters.get(event.getUser().getName().toString()).AlterDisposition(event.getMessage());
            System.out.println(chatters.get(event.getUser().getName()));
            float chance = rand.nextFloat();
            if (chance <= 0.10f) {
                messageHandler(event.getUser().getName().toString());
            }
        }
    }

    public void messageHandler(String name) {
        if (likedChatters.contains(name)) {
                bot.getTwitchClient().getChat().sendMessage("TheLostWielder", "I like you");
            } else if (dislikedChatters.contains(name)) {
                bot.getTwitchClient().getChat().sendMessage("TheLostWielder", "I don't like you very much");
            } else {
                bot.getTwitchClient().getChat().sendMessage("TheLostWielder", String.format(neutralRepliesList.get(replyRand.nextInt(neutralRepliesList.size())),name));
            }
        }


    public void checkDisposition(ChannelMessageEvent event){
        if(event.getMessage().equalsIgnoreCase("How do you feel about me TODbot?")) {
            bot.getTwitchClient().getChat().sendMessage("TheLostWielder", "This is how I currently feel about you, " + event.getUser().getName().toString() + "! " + chatters.get(event.getUser().getName().toString()).getDispo());
        }
    }

    public void writeDispositionDataToText(ChannelGoOfflineEvent event){
            BufferedWriter bf = null;

            try {

                // create new BufferedWriter for the output file
                bf = new BufferedWriter(new FileWriter(dispositionsText.toFile()));

                // iterate map entries
                for (Map.Entry<String, Disposition> entry :
                        chatters.entrySet()) {

                    // put key and value separated by a colon
                    bf.write(entry.getKey() + ":"
                            + entry.getValue().getDispo());

                    // new line
                    bf.newLine();
                }

                bf.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {

                try {

                    // always close the writer
                    bf.close();
                }
                catch (Exception e) {
                }
            }
        }
    public void populateNeutralReplies() throws IOException {
        String randomsT = neutralReplies.toAbsolutePath().toString();
        File randomText = new File(randomsT);
        BufferedReader BR = new BufferedReader(new FileReader(randomText));

        while (BR.ready()){
            neutralRepliesList.add(BR.readLine());
        }
        BR.close();
        Collections.shuffle(neutralRepliesList);
        System.out.println(neutralReplies);
    }

    public void moodInitilization(ChannelGoLiveEvent event) throws IOException {
        populateNeutralReplies();
    }




    /* use this example to interject names into pre determined strings from lists

    String EXPECTED_STRING = "String Interpolation in Java with some Java examples.";
    String first = "Interpolation";
    String second = "Java";
    String result = String.format("String %s in %s with some %s examples.", first, second, second);
    assertEquals(EXPECTED_STRING, result);

     */



}// end of file
