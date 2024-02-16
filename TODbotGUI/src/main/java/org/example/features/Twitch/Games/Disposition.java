package org.example.features.Twitch.Games;

import java.util.Arrays;
import java.util.List;

public class Disposition {

    private int Happy;
    private int Sad;
    private int Angry;
    private boolean liked;

    private List<String> happyPhrases = Arrays.asList("cat", "cheese", "java", "program", "metal",  "jrpg", "bridget", "giovanna", "ramlethal", "fun",
            "interactive", "bits", "subs", "happy", "good", "thelos23mashed", "thelos23risotto", "thelos23radish");
    private List<String> sadPhrases = Arrays.asList("spider","dead", "hate", "water", "shutdown", "debug", "sql", "tekken", "faust", "lonely", "neglected",
            "abandoned", "died", "dead", "thelos23sad");
    private List<String> angryPhrases = Arrays.asList("ads", "elden", "elphelt", "inferior", "error", "emp", "lag", "spam", "ban",
            "hack", "thelos23stop", "wutface", "hankwtf", "pepepoint");

    public Disposition(int happy, int sad, int angry, boolean liked)
    {
        this.Happy = happy;
        this.Sad = sad;
        this.Angry = angry;
        this.liked = liked;
    }
    public Disposition(){

    }

    public String getDispo()
    {
        return "Happiness:" + this.Happy + " Sadness: " + this.Sad + " Anger: " + this.Angry;
    }

    public String addHappy(int amt)
    {
        this.Happy += amt * 5;
        this.Sad -= amt * 3;
        this.Angry -= amt;
        return getDispo();
    }

    public int setHappy(int amt)
    {
        this.Happy = amt;

        return this.Happy;
    }

    public String addSad(int amt)
    {
        this.Happy -= amt * 3;
        this.Sad += amt * 5;
        this.Angry += amt;

        return getDispo();
    }

    public int setSad(int amt)
    {
        this.Sad = amt;

        return this.Sad;
    }

    public String addAngry(int amt)
    {
        this.Happy -= amt * 5;
        this.Sad += amt * 3;
        this.Angry += amt * 5;

        return getDispo();
    }

    public int setAngry(int amt)
    {
        this.Angry = amt;

        return this.Angry;
    }

    public void checkMoodLevels(){
        if(Happy>50){
            setHappy(50);

        }
        if(Happy<0){
            setHappy(0);
        }
        if(Sad>50){
            setSad(50);

        }
        if(Sad<0){
            setSad(0);
        }
        if(Angry>50){
            setAngry(50);

        }
        if(Angry<0){
            setAngry(0);
        }
    }

    public void AlterDisposition(String modifier){

        List<String> altered = Arrays.asList(modifier.toLowerCase().split("\\s+"));

        if(happyPhrases.stream().anyMatch(altered::contains)){
            addHappy(1);

        }
        if (sadPhrases.stream().anyMatch(altered::contains)){
            addSad(1);

        }
        if (angryPhrases.stream().anyMatch(altered::contains)){
            addAngry(1);

        }
        checkMoodLevels();
        System.out.println(Happy + " " + Sad + " " + Angry + altered);
        //messages are worth 5 points
        //for every 5 point to happiness -1 to anger -3 to sadness
        //for every 5 point to sadness -3 happiness +1 anger
        //when anger hits 50 happiness and sadness go to 0, anger -5 sadness +5 for every message
        //possibly rework scaling for numbers


    }


}//end of file