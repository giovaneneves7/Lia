//========================{ PACKAGE }========================//
package com.github.nekoyasha7.lia.model.tickets;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import com.github.nekoyasha7.lia.model.server.VulcanServidorStaff;
import com.github.nekoyasha7.lia.model.users.Avaliando;
import com.github.nekoyasha7.lia.model.server.VulcanServidorPrincipal;

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
public class TicketAutor extends Ticket{

    //========================{ METÓDOS }========================//

    //--+ Evento de quando o usuário clica no botão +--//
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event){

        //--+ Verifica se o ID do botão clicado é igual ao de avaliação de autor +--//
        boolean comecarAlgumaAvaliacao = event.getComponentId().equals("comecarAvaliacaoAutor") ||
                                         event.getComponentId().equals("comecarAvaliacaoTradutor") ||
                                         event.getComponentId().equals("comecarAvaliacaoAvaliador") ||
                                         event.getComponentId().equals("comecarAvaliacaoDesigner");

        if(comecarAlgumaAvaliacao){

            //========================{ NO CANAL DE RECRUTAMENTO }========================//
            //--+ Envia mensagem ephemeral quando o ticket é aberto +--//
            event.reply("Ticket aberto com sucesso!")
                    .setEphemeral(true)
                    .queue();

            //========================{ NO NOVO TICKET ABERTO }========================//
            String tipoAvaliacao = selecionarTipoAvaliacao(event.getComponentId());
            Member usuario = event.getMember();
            Guild servidor = event.getGuild();
            VulcanServidorPrincipal vulcanServidorPrincipal = new VulcanServidorPrincipal();
            Category categoria = servidor.getCategoryById(vulcanServidorPrincipal.getIdCategoriaRecrutamento());
            String idAvaliador = (tipoAvaliacao.equals("designer") || tipoAvaliacao.equals("avaliador")) ? vulcanServidorPrincipal.getNomeCargoAvaliador() : vulcanServidorPrincipal.getIdCargoViceLider();

            //--+ Instancia o Avaliando e seta os dados dele +--//
            Avaliando avaliando = new Avaliando();

            avaliando.setNome(usuario.getUser().getName());
            avaliando.setId(usuario.getUser().getId());

            //--+ Cria um Ticket Privado e atualiza as permissões do usuário +--//
            TextChannel ticketPrivado = vulcanServidorPrincipal.criarNovoTicketPrivado(
                                                                                            servidor,
                                                                                            usuario,
                                                                                            idAvaliador,
                                                                                            categoria
                                                                                        );

            //--+ Pega o ID do Ticket Privado +--//
            String idDoTicketPrivado = ticketPrivado.getId();

            //--+ Envia mensagem no ticket privado para o avaliando +--//
            ticketPrivado.sendMessage(enviarMensagemInicial(tipoAvaliacao, usuario, servidor, idAvaliador))
                    .addActionRow(Button.danger("fecharTicket", "Fechar ticket"))
                    .queue();

            //========================{ NO CANAL DE LOGS DO SERVIDOR DA STAFF }========================//

            //--+ Pega o ID do servidor da staff e envia informações dos novos tickets em um canal específico+--//
            VulcanServidorStaff vulcanServidorStaff = new VulcanServidorStaff();
            notificarNovoTicketAberto("Autor", usuario.getUser().getName(), idDoTicketPrivado, vulcanServidorStaff.getIdCargoAvaliador());
        }

    }

    public String selecionarTipoAvaliacao(String idDoComponente){
        String tipoAvaliacao = "";

        switch (idDoComponente) {
            case "comecarAvaliacaoAutor"     -> tipoAvaliacao = "autor";
            case "comecarAvaliacaoTradutor"  -> tipoAvaliacao = "tradutor";
            case "comecarAvaliacaoAvaliador" -> tipoAvaliacao = "avaliador";
            case "comecarAvaliacaoDesigner"  -> tipoAvaliacao = "designer";
        }
        return tipoAvaliacao;
    }

    public String enviarMensagemInicial(String tipoAvaliacao, Member usuario, Guild servidor, String idAvaliador){

        switch (tipoAvaliacao) {
            case "autor" -> {
                return "> **Avaliando:** " + usuario.getUser().getName() + "\n" +
                        "> **Tipo de Avaliação:** Autor\n" +
                        "> **Tipo de Avaliador:** " + servidor.getRoleById(idAvaliador).getAsMention() +
                        "\n\n" +
                        "Eiiii, " + usuario.getAsMention() + ", tô felizona que tenha optado por fazer uma avaliação na Vulcan Novels para **Autor**.\n" +
                        "Para iniciarmos o processo, digite:\n ``/iniciar-avaliacao``\n";
            }
            case "tradutor" -> {
                return "> **Avaliando:** " + usuario.getUser().getName() + "\n" +
                        "> **Tipo de Avaliação:** Tradutor\n" +
                        "> **Tipo de Avaliador:** " +  servidor.getRoleById(idAvaliador).getAsMention() + "\n\n" +
                        "Olá, " + usuario.getAsMention() + ", fico contente que tenha optado por fazer uma avaliação na Vulcan Novels para **Tradutor**.\n" +
                        "Em breve um " + servidor.getRoleById(idAvaliador).getAsMention() + " iniciará o seu processo de avaliação!";
            }

            case "designer" ->{
                return "> **Avaliando:** " + usuario.getUser().getName() + "\n" +
                        "> **Tipo de Avaliação:** Designer\n" +
                        "> **Tipo de Avaliador:** " +  servidor.getRoleById(idAvaliador).getAsMention() + "\n\n" +
                        "Olá, " + usuario.getAsMention() + ", fico contente que tenha optado por fazer uma avaliação na Vulcan Novels para **Designer**.\n" +
                        "Em breve um " + servidor.getRoleById(idAvaliador)
                        .getAsMention() + " iniciará o seu processo de avaliação!";
            }

            case "avaliador" ->{
                return "> **Avaliando:** " + usuario.getUser().getName() + "\n" +
                        "> **Tipo de Avaliação:** Avaliador\n" +
                        "> **Tipo de Avaliador:** " +  servidor.getRoleById(idAvaliador).getAsMention() +
                        "\n\n" +
                        "Olá, " + usuario.getAsMention() + ", fico contente que tenha optado por fazer uma avaliação na Vulcan Novels para **Avaliador**.\n" +
                        "Em breve um " + servidor.getRoleById(idAvaliador).getAsMention() +  " iniciará o seu processo de avaliação!";
            }

            default -> {
                return  "Ocorreu um erro ao carregar a mensage inicial!";
            }
        }

    }
}
