//<<< Package >>>//
package com.github.nekoyasha7.lia.commands.publicslashcommands;
//<<< End Package >>>//

//<<< Imports >>>//
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
//<<< End Imports >>>//
/*
 /* @author NekoYasha
 */
//<<< Class >>>//
public class SaySlashCommand extends ListenerAdapter{

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){

        if(event.getMember().getUser().isBot()) return;

        if(event.getName().equals("say")){

            event.getChannel().sendMessage(event.getOption("mensagem").getAsString())
                    .queue();

            event.reply("Mensagem enviada com sucesso!")
                    .setEphemeral(true)
                    .queue();
        }
    }
}
