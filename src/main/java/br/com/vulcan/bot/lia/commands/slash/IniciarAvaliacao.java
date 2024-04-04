//========================{ Package }========================//
package br.com.vulcan.bot.lia.commands.slash;
//========================{ FIM Package }========================//

//========================{ IMPORTS }========================//
import br.com.vulcan.bot.lia.entidades.Saudacao;
import br.com.vulcan.bot.lia.infrastructure.interfaces.SlashCommand;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import org.jetbrains.annotations.NotNull;
//========================{ FIM IMPORTS }========================//

/**
 * @author NekoYasha
 * @since 30/07/2023 11:21
 */

public class IniciarAvaliacao extends ListenerAdapter implements SlashCommand{

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

            evento.reply(new Saudacao().pegarOlaAleatorio()
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
