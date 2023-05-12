//========================{ Package }========================//
package com.github.nekoyasha7.lia.slashcommand.avaliacao;
//========================{ FIM Package }========================//

//========================{ IMPORTS }========================//
import com.github.nekoyasha7.lia.setups.interacao.Saudacao;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import org.jetbrains.annotations.NotNull;
//========================{ FIM IMPORTS }========================//

/**
 * @author NekoYasha
 */

public class IniciarAvaliacao extends ListenerAdapter {

    //========================{ ATRIBUTOS }========================//
    private final String LINK_FORMULARIO = "https://forms.gle/HQrosxxW9khNzZ1NA";
    private final String LINK_DOC_INICIAL = "https://docs.google.com/document/d/1urZ9E7JVjhCIbLxEZXGkx65lOKadjlQPEH6raIjI2Fc/edit?usp=sharing";
    private final Button BTN_LER_DOC_INICIAL = Button.link(LINK_DOC_INICIAL, "Ler Documento Inicial Novamente");
    private  final Button BTN_ENVIEI_FORMULARIO = Button.primary("enviarForm", "Já Enviei o Formulário!");

    //========================{ METÓDOS }========================//

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent evento){


        //--+ Verifica se quem usou o Slash Command é bot +--//
        if(evento.getMember().getUser().isBot()) return;

        if(evento.getName().equals("iniciar-avaliacao")){

            //--+ Envia mensagem com o link do formulario +--//
            Saudacao saudacao = new Saudacao();
            evento.reply(saudacao.pegarOlaAleatorio()
                            .concat(" Para começar a sua avaliação, preciso que você preencha o nosso formulário para conhecermos um pouco melhor sobre você!\n Ah! Já ia esquecendo, clique no botão **'Já enviei o form'** quando terminar de preencher, viu?\n"
                            .concat("> ".concat(LINK_FORMULARIO))))
                    .addActionRow(BTN_ENVIEI_FORMULARIO)
                    .setEphemeral(false)
                    .queue();
        }
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent evento) {

        if(evento.getComponentId().equals("enviarForm")){

            evento.reply("Ehhh! Foi tudo bem rápido, certo, docinho? ^-^\nVamos prosseguir! No formulário que você preencheu havia um documento chamado **Documento Inicial**. "
                            .concat("Neste documento, havia uma palavra-passe, uma senha. Segurança de ponta, né não?\n")
                            .concat("Por favor, insira o comando ``/senha-avaliacao`` junto à senha ;)"))
                    .addActionRow(BTN_LER_DOC_INICIAL)
                    .setEphemeral(false)
                    .queue();
        }
    }
}
