//<<< Package >>>//
package com.github.nekoyasha7.lia.model.slashcommands.publiccommands;
//<<< End Package >>>//

//<<< Imports >>>//
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
//<<< End Imports >>>//

/**
/*@author Nekoyasha
 */

public class PingSlashCommand extends ListenerAdapter{

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){

        //--+ Verifica se o usuário é bot. +--//
        if(event.getMember().getUser().isBot()) return;

        long tempoDeInicio = System.currentTimeMillis();

        if(event.getName().equalsIgnoreCase("ping")){

            event.reply("🏓 Pong!").setEphemeral(false).queue(resposta -> {
                long tempoDeFinalizacao = System.currentTimeMillis();
                long latencia = (tempoDeFinalizacao - tempoDeInicio);
                String reacao = (latencia < 100)? "Oh, isso é ótimo 😃"  : (latencia < 300)? "Hmm, tudo normal por aqui 🙃"  : "Ih, talvez possamos ter instabilidades! 😥";
                event.getChannel().sendMessageFormat("Latência da aplicação: ``%dms``. %s", latencia, reacao).queue();
            });

        }
    }

}
