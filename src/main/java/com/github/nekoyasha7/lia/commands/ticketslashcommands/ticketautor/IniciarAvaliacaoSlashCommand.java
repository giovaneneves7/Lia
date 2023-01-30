//<<< Package >>>//
package com.github.nekoyasha7.lia.commands.ticketslashcommands.ticketautor;
//<<< End Package >>>//

//<<< Imports >>>//
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

//<<< End Imports >>>//
/*
/*@author Nekoyasha
 */
//<<< Class >>>//
public class IniciarAvaliacaoSlashCommand extends ListenerAdapter {

    //Variables
    String formLink = "https://forms.gle/HQrosxxW9khNzZ1NA";
    String docInicialLink = "https://forms.gle/HQrosxxW9khNzZ1NA";

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){

        if(event.getMember().getUser().isBot()) return;

        if(event.getName().equalsIgnoreCase("iniciar-avaliacao")){

            event.reply(
                            "Para iniciarmos a sua avaliação, é necessário que você preencha o nosso formulário para conhecermos um pouco melhor sobre você!\n" +
                                    "> " + formLink)
                    .addActionRow(
                            Button.primary("sendForm", "Já enviei o form!")
                    )
                    .setEphemeral(false)
                    .queue();
        }

    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event){
        if(event.getComponentId().equals("sendForm")){
            event.reply("Ótimo! No formulário que você preencheu havia um documento chamado Documento Inicial. "
                            + "Neste documento havia uma palavra-passe (senha).\n" +
                            "Por favor, insira o comando **/senha-avaliacao** junto à senha.")
                    .addActionRow(
                            Button.link(docInicialLink, "Ler o documento inicial novamente")
                    )
                    .setEphemeral(false)
                    .queue();
        }
    }
}
