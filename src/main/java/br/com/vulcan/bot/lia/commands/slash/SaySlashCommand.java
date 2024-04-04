//<<< Package >>>//
package br.com.vulcan.bot.lia.commands.slash;
//<<< End Package >>>//

//<<< Imports >>>//
import br.com.vulcan.bot.lia.infrastructure.interfaces.SlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
//<<< End Imports >>>//
/**
 /* @author NekoYasha
 */

public class SaySlashCommand extends ListenerAdapter implements SlashCommand{

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){

        //--+ Verifica se o usuário é bot +--//
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
