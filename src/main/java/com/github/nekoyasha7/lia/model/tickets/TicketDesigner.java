//========================{ PACKAGE }========================//
package com.github.nekoyasha7.lia.model.tickets;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import com.github.nekoyasha7.lia.model.server.VulcanServidorPrincipal;
import com.github.nekoyasha7.lia.model.server.VulcanServidorStaff;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
//========================{ FIM IMPORTS }========================//

/**
 * @author NekoYasha
 */
public class TicketDesigner extends Ticket {

        //@Override
        public void onButtonInteraction(ButtonInteractionEvent event){

            if (event.getComponentId().equals("comecarAvaliacaoDesigner")) {

                VulcanServidorPrincipal vulcanServidorPrincipal = new VulcanServidorPrincipal();

                event.reply("Ticket aberto com sucesso!")
                        .setEphemeral(true)
                        .queue();

                Member usuario = event.getMember();
                Guild servidor = event.getGuild();

                Category categoria = servidor.getCategoryById(vulcanServidorPrincipal.getIdCategoriaRecrutamento());

                TextChannel ticketPrivado = vulcanServidorPrincipal.criarNovoTicketPrivado(
                        servidor,
                        usuario,
                        vulcanServidorPrincipal.getIdCargoViceLider(),
                        categoria
                );

                String idTicketPrivado = ticketPrivado.getId();


                //--+ Envia uma mensagem inicial no ticket +--//

                ticketPrivado.sendMessage(
                            "> **Avaliando:** " + usuario.getUser().getName() + "\n" +
                                 "> **Tipo de Avaliação:** Designer\n" +
                                 "> **Tipo de Avaliador:** " +  servidor.getRoleById(vulcanServidorPrincipal.getIdCargoViceLider()).getAsMention() + "\n\n" +
                                 "Olá, " + usuario.getAsMention() + ", fico contente que tenha optado por fazer uma avaliação na Vulcan Novels para **Designer**.\n" +
                                 "Em breve um " + servidor.getRoleById(vulcanServidorPrincipal.getIdCargoViceLider())
                                    .getAsMention() + " iniciará o seu processo de avaliação!")
                                    .addActionRow(Button.danger("closeTicket", "Fechar ticket"))
                        .queue();

                //--+Pega o ID do servidor da staff e envia informações dos novos tickets em um canal específico+--//
                VulcanServidorStaff vulcanServidorStaff = new VulcanServidorStaff();
                notificarNovoTicketAberto("Designer", usuario.getUser().getName(), idTicketPrivado, vulcanServidorStaff.getIdCargoVice());

            }
        }

    }
