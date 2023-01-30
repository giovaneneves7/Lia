//<<< Package >>>//
package com.github.nekoyasha7.lia.commands.admslashcommands;
//<<< End Package >>>//

//<<< Imports >>>//
import com.github.nekoyasha7.lia.setups.Presence;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
//<<< End Imports >>>//
/*
/*@author Nekoyasha
 */
//<<< Class >>>//
public class SelecionarPresenca extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){

        if(event.getMember().getUser().isBot()) return;

        if(event.getName().equalsIgnoreCase("selecionar-presenca")){

            if(event.getMember().hasPermission(Permission.BAN_MEMBERS)){

                Presence.setPresence(event.getOption("atividade").getAsString(), event.getOption("descricao").getAsString());

                event.reply("Atividade atualizada para: " + event.getOption("atividade").getAsString() + " " + event.getOption("descricao").getAsString() + "!")
                        .setEphemeral(true)
                        .queue();
            } else{
                event.reply("Você não tem permissão para usar este comando!")
                        .setEphemeral(true)
                        .queue();
            }
        }
    }

}
