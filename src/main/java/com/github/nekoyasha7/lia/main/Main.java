//<<< Package >>>//
package com.github.nekoyasha7.lia.main;
//<<< End Package >>>//

//<<< Imports >>>//
import io.github.cdimascio.dotenv.Dotenv;

import com.github.nekoyasha7.lia.setups.AddEvents;
import com.github.nekoyasha7.lia.setups.Presence;
import com.github.nekoyasha7.lia.setups.RegisterSlashCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;
//<<< End Imports >>>//
/*
/*@author Nekoyasha
 */
//<<< Class >>>//
public class Main {

    public static JDA jda;
    public static void main(String[] args) {
        startBot();
    }

    public static void startBot(){

       Dotenv dotenv = Dotenv.load();
       String token = dotenv.get("TOKEN");

        jda = JDABuilder.createDefault(token)
                        .enableIntents((EnumSet.allOf(GatewayIntent.class)))
                        .build();

        Presence.setPresence("WATCHING", "Destino Subvertido");
        RegisterSlashCommand.registerSlashCommand();
        AddEvents.addEvents();
    }
}