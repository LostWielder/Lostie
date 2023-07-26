package org.example.features.Twitch;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import org.example.Bot;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FinalFantasy {

    private final Bot bot;
    List<String> accessoriesL = new ArrayList<String>();
    List<String> armorL = new ArrayList<String>();
    List<String> commandL = new ArrayList<String>();
    List<String> independantL = new ArrayList<String>();
    List<String> itemsL = new ArrayList<String>();
    List<String> magicL = new ArrayList<String>();
    List<String> summonL = new ArrayList<String>();
    List<String> supportL = new ArrayList<String>();
    List<String> weaponsL = new ArrayList<String>();

    List<String> outputItems = new ArrayList<String>();
    List<String> outputEquip = new ArrayList<String>();
    List<String> outputMat = new ArrayList<String>();
    List<String> levels = new ArrayList<String>();
    public int potions = 0;
    /**
     * Register events of this class with the EventManager/EventHandler
     *
     * @param eventHandler SimpleEventHandler
     * @param bot
     */
    public FinalFantasy(SimpleEventHandler eventHandler, Bot bot) {
        this.bot = bot;
        eventHandler.onEvent(ChannelMessageEvent.class, ffReply -> finalFantasyReply(ffReply));
        eventHandler.onEvent(ChannelMessageEvent.class, outputStuff -> {
            try {
                outputSelections(outputStuff);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void readFinalFantasyItems() throws IOException, URISyntaxException {

            System.out.println("Quote Event Fired");
        Path accessories = Paths.get("","FFVII/Accessories.txt");
        Path armor = Paths.get("","FFVII/Armor.txt");
        Path command = Paths.get("","FFVII/Command.txt");
        Path independant = Paths.get("","FFVII/Independant.txt");
        Path items = Paths.get("","FFVII/Items.txt");
        Path magic = Paths.get("","FFVII/Magic.txt");
        Path summon = Paths.get("","FFVII/Summon.txt");
        Path support = Paths.get("","FFVII/Support.txt");
        Path weapons = Paths.get("","FFVII/Weapons.txt");

        String accessoriesP = accessories.toAbsolutePath().toString();
        File accessoriesF = new File(accessoriesP);
        BufferedReader accessoriesBR = new BufferedReader(new FileReader(accessoriesF));

        String accessory = "";
        // System.out.println(line);
        while (accessoriesBR.ready()){
            accessory = accessoriesBR.readLine();
            accessoriesL.add(accessory);

        }

        String armorsP = armor.toAbsolutePath().toString();
        File armorF = new File(armorsP);
        BufferedReader armorBR = new BufferedReader(new FileReader(armorF));

        String armors = "";
        // System.out.println(line);
        while (armorBR.ready()){
            armors = armorBR.readLine();
            armorL.add(armors);

        }

        String commandP = command.toAbsolutePath().toString();
        File commandF = new File(commandP);
        BufferedReader commandBR = new BufferedReader(new FileReader(commandF));

        String commands = "";
        // System.out.println(line);
        while (commandBR.ready()){
            commands = commandBR.readLine();
            commandL.add(commands);

        }
        String independantP = independant.toAbsolutePath().toString();
        File independantF = new File(independantP);
        BufferedReader independantBR = new BufferedReader(new FileReader(independantF));

        String independants = "";
        // System.out.println(line);
        while (independantBR.ready()){
            independants = independantBR.readLine();
            independantL.add(independants);

        }
        String itemsP = items.toAbsolutePath().toString();
        File itemsF = new File(itemsP);
        BufferedReader itemsBR = new BufferedReader(new FileReader(itemsF));

        String itemss = "";
        // System.out.println(line);
        while (itemsBR.ready()){
            itemss = itemsBR.readLine();
            itemsL.add(itemss);

        }
        String magicP = magic.toAbsolutePath().toString();
        File magicF = new File(magicP);
        BufferedReader magicBR = new BufferedReader(new FileReader(magicF));

        String magics = "";
        // System.out.println(line);
        while (magicBR.ready()){
            magics = magicBR.readLine();
            magicL.add(magics);

        }
        String summonP = summon.toAbsolutePath().toString();
        File summonF = new File(summonP);
        BufferedReader summonBR = new BufferedReader(new FileReader(summonF));

        String summons = "";
        // System.out.println(line);
        while (summonBR.ready()){
            summons = summonBR.readLine();
            summonL.add(summons);

        }
        String supportP = support.toAbsolutePath().toString();
        File supportF = new File(supportP);
        BufferedReader supportBR = new BufferedReader(new FileReader(supportF));

        String supports = "";
        // System.out.println(line);
        while (supportBR.ready()){
            supports = supportBR.readLine();
            supportL.add(supports);

        }
        String weaponsP = weapons.toAbsolutePath().toString();
        File weaponsF = new File(weaponsP);
        BufferedReader weaponsBR = new BufferedReader(new FileReader(weaponsF));

        String weaponss = "";
        // System.out.println(line);
        while (weaponsBR.ready()){
            weaponss = weaponsBR.readLine();
            weaponsL.add(weaponss);

        }






        accessoriesBR.close();
        armorBR.close();
        commandBR.close();
        independantBR.close();
        itemsBR.close();
        magicBR.close();
        summonBR.close();
        supportBR.close();
        weaponsBR.close();
        System.out.println(accessoriesL.size());
        System.out.println(armorL.size());
        System.out.println(commandL.size());
        System.out.println(independantL.size());
        System.out.println(itemsL.size());
        System.out.println(magicL.size());
        System.out.println(summonL.size());
        System.out.println(supportL.size());
        System.out.println(weaponsL.size());

        }



    public void finalFantasyReply(ChannelMessageEvent ffReply){

   int rInt;
   Random r = new Random();

   if (ffReply.getMessage().contains("!accessories") && ffReply.getUser().getName().equals("thelostwielder")) {
       rInt = r.nextInt(32)+1;
       this.bot.getTwitchClient().getChat().sendMessage(ffReply.getChannel().getName(),accessoriesL.get(rInt));
       outputEquip.add(accessoriesL.get(rInt)+"\n");
   }else if (ffReply.getMessage().contains("!armor") && ffReply.getUser().getName().equals("thelostwielder")) {
       rInt = r.nextInt(31)+1;
       this.bot.getTwitchClient().getChat().sendMessage(ffReply.getChannel().getName(),armorL.get(rInt));
       outputEquip.add(armorL.get(rInt)+"\n");
   }else if (ffReply.getMessage().contains("!command") && ffReply.getUser().getName().equals("thelostwielder")) {
       rInt = r.nextInt(13)+1;
       this.bot.getTwitchClient().getChat().sendMessage(ffReply.getChannel().getName(),commandL.get(rInt));
       outputMat.add(commandL.get(rInt)+"\n");
   }else if (ffReply.getMessage().contains("!independent") && ffReply.getUser().getName().equals("thelostwielder")) {
       rInt = r.nextInt(17)+1;
       this.bot.getTwitchClient().getChat().sendMessage(ffReply.getChannel().getName(),independantL.get(rInt));
       outputMat.add(independantL.get(rInt)+"\n");
   }else if (ffReply.getMessage().contains("!items") && ffReply.getUser().getName().equals("thelostwielder")) {
       rInt = r.nextInt(101)+1;
       this.bot.getTwitchClient().getChat().sendMessage(ffReply.getChannel().getName(),itemsL.get(rInt));
       outputItems.add(itemsL.get(rInt)+"\n");
   }else if (ffReply.getMessage().contains("!magic") && ffReply.getUser().getName().equals("thelostwielder")) {
       rInt = r.nextInt(21)+1;
       this.bot.getTwitchClient().getChat().sendMessage(ffReply.getChannel().getName(),magicL.get(rInt));
       outputMat.add(magicL.get(rInt)+"\n");
   }else if (ffReply.getMessage().contains("summon") && ffReply.getUser().getName().equals("thelostwielder")) {
       rInt = r.nextInt(15)+1;
       this.bot.getTwitchClient().getChat().sendMessage(ffReply.getChannel().getName(),summonL.get(rInt));
       outputMat.add(summonL.get(rInt)+"\n");
   }else if (ffReply.getMessage().contains("!support") && ffReply.getUser().getName().equals("thelostwielder")) {
       rInt = r.nextInt(13)+1;
       this.bot.getTwitchClient().getChat().sendMessage(ffReply.getChannel().getName(),supportL.get(rInt));
       outputMat.add(supportL.get(rInt)+"\n");
   }else if (ffReply.getMessage().contains("!weapons") && ffReply.getUser().getName().equals("thelostwielder")) {
       rInt = r.nextInt(127)+1;
       this.bot.getTwitchClient().getChat().sendMessage(ffReply.getChannel().getName(),weaponsL.get(rInt));
       outputEquip.add(weaponsL.get(rInt)+"\n");
   }else if (ffReply.getMessage().contains("!potion") && ffReply.getUser().getName().equals("thelostwielder")) {
       potions = potions+1;
   }else if (ffReply.getMessage().contains("lvl up") || ffReply.getMessage().contains("lvl down") && ffReply.getUser().getName().equals("thelostwielder")) {
       levels.add(ffReply.getMessage() +"\n");
   }

    }
    public void outputSelections(ChannelMessageEvent outputSelect) throws IOException {
        Path currentRelativePath = Paths.get("","FFVII/Output.txt");
        String s = currentRelativePath.toAbsolutePath().toString();
        // System.out.println("Current absolute path is: " + s);
        if (outputSelect.getMessage().contains("!update")&& outputSelect.getUser().getName().equals("thelostwielder")) {

            FileWriter outputSelections = new FileWriter(s);
            outputSelections.write(outputItems.toString() + "\n" + outputEquip.toString() + "\n" + outputMat.toString() + "\n" + "Potions + "+potions + "\n" + levels.toString());

            outputSelections.close();
            outputItems.clear();
            outputEquip.clear();
            outputMat.clear();
            potions = 0;
            levels.clear();
            System.out.println("List Updated");
            this.bot.getTwitchClient().getChat().sendMessage(outputSelect.getChannel().getName(),"List Updated! Please inject new values.");
        }
    }


}
