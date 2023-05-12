//<<< Package >>>//
package com.github.nekoyasha7.lia.slashcommand.general;
//<<< End Package >>>//

//<<< Imports >>>//

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

//<<< End Imports >>>//

public class AskSlashCommand extends ListenerAdapter{

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        //--+ Verifica se o usuário é um Bot +--//
        if(event.getUser().isBot()) return;

        if(event.getName().equals("ask")){

            event.reply("Comando usado com sucesso!")
                    .setEphemeral(true)
                    .queue();

            //--+ Envia a resposta para o prompt do usuário+--//
            //String prompt = event.getOption("prompt").getAsString();
            //event.getChannel().sendMessage(OpenAiRequest.fazerRequisicao(prompt))
              //                .queue();

        }
    }
}
