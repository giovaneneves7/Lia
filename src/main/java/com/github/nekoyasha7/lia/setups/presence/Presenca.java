//<<< Package >>>//
package com.github.nekoyasha7.lia.setups.presence;
//<<< End Package >>>//

//========================{ IMPORTS }========================//
import com.github.nekoyasha7.lia.main.Main;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
//========================{ FIM IMPORTS }========================//

/**
 * @author NekoYasha
 */
public class Presenca extends ListenerAdapter {

    /**
     * Atualiza a atividade do bot para a que foi passada por parametro.
     * @param atividade O nome da atividade da aplicação (WATCHING, PLAYING, LISTENING, STREAMING).
     * @param descricao A descrição da atividade.
     * @return A nova atividade.
     */
    public static boolean atualizarPresenca(String atividade, String descricao){

        //+--Verifica qual a atividade passada como parâmetro pelo usuário e retorna 'true'--+//
        switch (atividade) {
            case "WATCHING" -> {
                Main.jda.getPresence().setPresence(
                        OnlineStatus.ONLINE, Activity.of(Activity.ActivityType.WATCHING, descricao)
                );
                return true;
            }
            case "PLAYING" -> {
                Main.jda.getPresence().setPresence(
                        OnlineStatus.ONLINE, Activity.of(Activity.ActivityType.PLAYING, descricao)
                );
                return true;
            }
            case "LISTENING" -> {
                Main.jda.getPresence().setPresence(
                        OnlineStatus.ONLINE, Activity.of(Activity.ActivityType.LISTENING, descricao)
                );
                return true;
            }
            case "STREAMING" -> {
                Main.jda.getPresence().setPresence(
                        OnlineStatus.ONLINE, Activity.of(Activity.ActivityType.STREAMING, descricao, "https://discord.gg/vulcan-3k-410158562929803275")
                );
                return true;
            }
        }

        //+--Retorna 'false' caso a atividade seja inválida--+//
        return false;

    }
}
