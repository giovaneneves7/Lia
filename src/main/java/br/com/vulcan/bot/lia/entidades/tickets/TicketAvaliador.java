//========================{ PACKAGE }========================//
package br.com.vulcan.bot.lia.entidades.tickets;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import br.com.vulcan.bot.lia.entidades.VulcanServidorPrincipal;
import br.com.vulcan.bot.lia.entidades.VulcanServidorStaff;
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
public class TicketAvaliador extends Ticket {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event){

        if(event.getComponentId().equals("comecarAvaliacaoAvaliador")){

            //--+ Instância da Classe Vulcan +--//
            VulcanServidorPrincipal vulcanServidorPrincipal = new VulcanServidorPrincipal();

            event.reply("Ticket aberto com sucesso!")
                    .setEphemeral(true)
                    .queue();

            Member usuario = event.getMember();
            Guild servidor = event.getGuild();

            Category categoria = servidor.getCategoryById(vulcanServidorPrincipal.getIdCategoriaRecrutamento());

            //--+ Cria um ticket privado +--//
            TextChannel ticketPrivado = vulcanServidorPrincipal.criarNovoTicketPrivado(
                    servidor,
                    usuario,
                    vulcanServidorPrincipal.getIdCargoViceLider(),
                    categoria
            );

            String idTicketPrivado = ticketPrivado.getId();

            //--+ Manda uma mensagem inicial no ticket privado +--//

            ticketPrivado.sendMessage(
                                "> **Avaliando:** " + usuario.getUser().getName() + "\n" +
                                    "> **Tipo de Avaliação:** Avaliador\n" +
                                    "> **Tipo de Avaliador:** " +  servidor.getRoleById(vulcanServidorPrincipal.getIdCargoViceLider()).getAsMention() +
                                    "\n\n" +
                                    "Olá, " + usuario.getAsMention() + ", fico contente que tenha optado por fazer uma avaliação na Vulcan Novels para **Avaliador**.\n" +
                                    "Em breve um " + servidor.getRoleById(vulcanServidorPrincipal.getIdCargoViceLider()).getAsMention() +  " iniciará o seu processo de avaliação!")
                    .addActionRow(Button.danger("closeTicket", "Fechar ticket"))
                    .queue();

            //--+ Manda as informação do ticket no log de tickets do servidor da staff +--//
            VulcanServidorStaff vulcanServidorStaff = new VulcanServidorStaff();
            notificarNovoTicketAberto("Avaliador", usuario.getUser().getName(), idTicketPrivado, VulcanServidorStaff.CARGO_VICE_ID);
        }
    }

}
