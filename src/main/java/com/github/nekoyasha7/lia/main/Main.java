//========================{ PACKAGE }======================//
package com.github.nekoyasha7.lia.main;
//========================{ FIM PACKAGE }======================//

//========================{ IMPORTS }======================//
import com.github.nekoyasha7.lia.setups.events.AddEvents;
import com.github.nekoyasha7.lia.setups.presence.Presenca;
import com.github.nekoyasha7.lia.setups.slashcommands.RegisterSlashCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;
//========================{ FIM IMPORTS }======================//

/**
/* @author Nekoyasha
 */

public class Main {

    public static JDA jda;
    public static void main(String[] args) {
        startBot();
    }

    public static void startBot(){

       //--+ Constroi o Bot--+//
        jda = JDABuilder.createDefault("MTA2MzQ3Njc0ODUwMDU5ODkwNQ.GwMo9o.x2OV7s9vVXmqeDS8BPcSMcw_XIKxWcjBYoxNLY")
                        .enableIntents((EnumSet.allOf(GatewayIntent.class)))
                        .build();

        //--+ Seleciona a Presença (atividade) do Bot +--//
        Presenca.atualizarPresenca("WATCHING", "Lendário Mecânico");

        //--+Registra os Slash Commands(comandos de barra) do Bot--+//
        RegisterSlashCommand.registerSlashCommand();

        //--+Adiciona a instância dos eventos que oceorrerão quando um Slash Command é usadp--+//
        AddEvents.addEvents();
    }
}