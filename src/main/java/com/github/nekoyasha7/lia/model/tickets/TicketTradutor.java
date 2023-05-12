//========================{ PACKAGE }========================//
package com.github.nekoyasha7.lia.model.tickets;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import com.github.nekoyasha7.lia.model.server.VulcanServidorPrincipal;
import com.github.nekoyasha7.lia.model.server.VulcanServidorStaff;
import com.github.nekoyasha7.lia.model.tickets.Ticket;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
//========================{ FIM IMPORTS }========================//

/**
 /* @author NekoYasha
 */

public class TicketTradutor extends Ticket {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event){

        //--+ Verifica se é o evento para avaliação de tradutor +--//
        if(event.getComponentId().equals("comecarAvaliacaoTradutor")){

            VulcanServidorPrincipal vulcanServidorPrincipal = new VulcanServidorPrincipal();

            //========================{ NO CANAL DE RECRUTAMENTO }========================//
            event.reply("Ticket aberto com sucesso!")
                    .setEphemeral(true)
                    .queue();

            //========================{ CRIANDO NOVO TICKET }========================//
            Member usuario = event.getMember();
            Guild servidor = event.getGuild();
            Category categoria = servidor.getCategoryById(vulcanServidorPrincipal.getIdCategoriaRecrutamento());


            TextChannel ticketPrivado = vulcanServidorPrincipal.criarNovoTicketPrivado(
                    servidor,
                    usuario,
                    vulcanServidorPrincipal.getIdCargoAvaliador(),
                    categoria
            );

            String idDoTicketPrivado = ticketPrivado.getId();

            //--+ Envia uma mensagem inicial para o ticket +--//
        /*   ticketPrivado.sendMessage()
                    .addActionRow(Button.danger("closeTicket", "Fechar ticket"))
                    .queue();
*/
            //--+Pega o ID do servidor da staff e envia informações dos novos tickets em um canal específico+--//
            VulcanServidorStaff vulcanServidorStaff = new VulcanServidorStaff();
            notificarNovoTicketAberto("Tradutor", usuario.getUser().getName(), idDoTicketPrivado, vulcanServidorStaff.getIdCargoAvaliador());
        }
    }

}
