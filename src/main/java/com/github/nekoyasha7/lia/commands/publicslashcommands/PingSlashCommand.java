//<<< Package >>>//
package com.github.nekoyasha7.lia.commands.publicslashcommands;
//<<< End Package >>>//

//<<< Imports >>>//
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
//<<< End Imports >>>//
/*
/*@author Nekoyasha
 */
//<<< Class >>>//
public class PingSlashCommand extends ListenerAdapter{

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){

        if(event.getMember().getUser().isBot()) return;

        if(event.getName().equalsIgnoreCase("ping")){
            event.reply("🏓 Pong!").setEphemeral(false).queue();
        }
    }

}
