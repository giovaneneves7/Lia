//<<< Package >>>//
package com.github.nekoyasha7.lia.main;
//<<< End Package >>>//

//<<< Imports >>>//
import io.github.cdimascio.dotenv.Dotenv;

import com.github.nekoyasha7.lia.setups.*;

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

        //--+Carrega o Token do Bot+--//
       Dotenv dotenv = Dotenv.load();
       String token = dotenv.get("TOKEN");

       //--+ Constroi o Bot--+//
        jda = JDABuilder.createDefault(token)
                        .enableIntents((EnumSet.allOf(GatewayIntent.class)))
                        .build();

        //--+Seleciona a Presença (atividade) do Bot+--//
        Presence.setPresence("WATCHING", "Lendário Mecânico");

        //--+Registra os Slash Commands(comandos de barra) do Boy--+//
        RegisterSlashCommand.registerSlashCommand();

        //--+Adiciona a instância dos eventos que oceorrerão quando um Slash Command é usadp--+//
        AddEvents.addEvents();
    }
}