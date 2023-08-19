//========================{ PACKAGE }======================//
package br.com.vulcan.bot.lia.main;
//========================{ FIM PACKAGE }======================//

//========================{ IMPORTS }======================//
import br.com.vulcan.bot.lia.config.AddEvents;
import br.com.vulcan.bot.lia.config.Presenca;
import br.com.vulcan.bot.lia.config.RegisterSlashCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;
//========================{ FIM IMPORTS }======================//

/**
 * @author Nekoyasha
 * @version 1.0.2 (Beta)
 */

public class Main {

    private static String token ="MTAyNjYxMDMzODg4NTU1NDI3OA.GZlkFK.HLJb73Bgum7U0zkkrXe6Z3TV4WnO0iZNvO4VL4";
    public static JDA jda;

    public static void main(String[] args) {
        startBot();
    }

    public static void startBot() {

        // --+ Constroi o Bot--+//
        jda = JDABuilder.createDefault(token/*"MTA2MzQ3Njc0ODUwMDU5ODkwNQ.Gt1I2q.6whd_xE0Id2QsbDqxEiA-fV5shobmrRk1qxojo"*/)
                .enableIntents((EnumSet.allOf(GatewayIntent.class)))
                .build();

        // --+ Seleciona a Presença (atividade) do Bot +--//
        Presenca.atualizarPresenca("WATCHING", "Lendário Mecânico");

        // --+Registra os Slash Commands(comandos de barra) do Bot--+//
        RegisterSlashCommand.registerSlashCommand();

        // --+Adiciona a instância dos eventos que oceorrerão quando um Slash Command é
        // usadp--+//
        AddEvents.addEvents();
    }
}