package org.example.features.Twitch.Games;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TimedMessages {
    Path randomMessages = Paths.get("","EventTexts/randoms.txt");






    public List<String> messages = new ArrayList<>();

private boolean runTimer = true;

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


}
