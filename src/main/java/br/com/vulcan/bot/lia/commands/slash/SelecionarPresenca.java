//========================{ Package }========================//
package br.com.vulcan.bot.lia.commands.slash;
//========================{ FIM Package }========================//

//========================{ IMPORTS }========================//

import br.com.vulcan.bot.lia.config.Presenca;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

//========================{ FIM IMPORTS }========================//

/**
 *
 * @author NekoYasha
 */

public class SelecionarPresenca extends ListenerAdapter {

    /**
     * Atualiza a presença do bot.
     * @param event O evento de interação.
     */
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){

        //--+ Verifica se o usuário é Bot --+//
        if(event.getMember().getUser().isBot()) return;

        if(event.getName().equalsIgnoreCase("selecionar-presenca")){

            //--+ Verifica se o usuário tem permissões de ADM +--//
            if(event.getMember().hasPermission(Permission.BAN_MEMBERS)){

                //--+ Tenta Atualizar a atividade para a que foi passada por parâmetro --+//
                if(!Presenca.atualizarPresenca(event.getOption("atividade").getAsString(), event.getOption("descricao").getAsString())){

                    //--+ Envia uma mensagem de erro --+//
                    event.reply("Opa! A atividade ´´" + event.getOption("atividade").getAsString() + "´´ é inválida!")
                            .setEphemeral(true)
                            .queue();

                } else{

                    //--+ Envia uma mensagem de sucesso --+//
                    event.reply("Atividade atualizada para: " + event.getOption("atividade").getAsString() + " " + event.getOption("descricao").getAsString() + "!")
                            .setEphemeral(true)
                            .queue();

                }



            } else{
                //--+ Resposta caso o usuário não tenha as permissões de ADM +--//
                event.reply("Você não tem permissão para usar este comando!")
                        .setEphemeral(true)
                        .queue();
            }
        }
    }

}
