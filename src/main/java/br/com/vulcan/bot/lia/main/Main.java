//========================{ PACKAGE }======================//
package br.com.vulcan.bot.lia.main;
//========================{ FIM PACKAGE }======================//

//========================{ IMPORTS }======================//
import br.com.vulcan.bot.lia.config.AddEvents;
import br.com.vulcan.bot.lia.feature.presenca.domain.model.Presenca;
import br.com.vulcan.bot.lia.config.RegisterSlashCommand;
import br.com.vulcan.bot.lia.infrastructure.builder.BotBuilder;
import br.com.vulcan.bot.lia.infrastructure.model.bot.Bot;
import br.com.vulcan.bot.lia.infrastructure.service.BotServiceImpl;
import br.com.vulcan.bot.lia.infrastructure.service.IBotService;
import br.com.vulcan.bot.lia.singleton.JdaSingleton;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
//========================{ FIM IMPORTS }======================//

/**
 * @author Giovane Neves
 * @version 1.0.2 (Beta)
 */

public class Main {

    
    public static void main(String[] args) {
        startBot();
    }

    public static void startBot() {

        // --+ Constroi o Bot--+//
        Bot bot = new BotBuilder()
            .setInstancia(JdaSingleton.getInstance())
            .setStatus(OnlineStatus.ONLINE)
            .setAtividade(Activity.of(Activity.ActivityType.WATCHING, "O Lendário Mecânico"))
            .build();
        
        IBotService botService = new BotServiceImpl();
        botService.registrarComandosSlash(bot);
        
        AddEvents.addEvents();
        
    }
}