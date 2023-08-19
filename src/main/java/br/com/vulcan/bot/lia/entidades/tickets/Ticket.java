//========================{ PACKAGE }========================//
package br.com.vulcan.bot.lia.entidades.tickets;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import br.com.vulcan.bot.lia.main.Main;
import br.com.vulcan.bot.lia.entidades.VulcanServidorPrincipal;
import br.com.vulcan.bot.lia.model.users.Avaliando;
import br.com.vulcan.bot.lia.model.users.Avaliador;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Color;
//========================{ FIM IMPORTS }========================//

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Ticket extends ListenerAdapter {

    //========================{ ATRIBUTOS }========================//
    private Button botaoTipoAvaliacao;
    private Button botaoConfirmarFechamento;
    private Button botaoCancelarFechamento;
    private Avaliando avaliando;
    private Avaliador avaliador;
    private String tipoAvaliacao;

    //========================{ GETTERS E SETTERS }========================//

    public void setBotaoTipoAvaliacao(Button botaoTipoAvaliacao) {
        this.botaoTipoAvaliacao = botaoTipoAvaliacao;
        this.setTipoAvaliacao();
    }

    private void setBotaoConfirmarFechamento() {
        this.botaoConfirmarFechamento = Button.danger("simFechar", "SIM");
    }

    private void setBotaoCancelarFechamento() {
        this.botaoCancelarFechamento = Button.success("naoFechar", "NÃO");
    }

    private void setTipoAvaliacao() {

        try{

            switch (this.getBotaoTipoAvaliacao().getId()) {
                case "comecarAvaliacaoAutor" -> this.tipoAvaliacao = "Autor";
                case "comecarAvaliacaoTradutor" -> this.tipoAvaliacao = "Tradutor";
                case "comecarAvaliacaoAvaliador" -> this.tipoAvaliacao = "Avaliador";
                case "comecarAvaliacaoDesigner" -> this.tipoAvaliacao = "Designer";
            }

        } catch(NullPointerException ex){
            System.out.println("O Id do tipo de avaliação é Nulo!");
            ex.printStackTrace();
        }

    }

    //========================{ CONSTRUTOR }========================//

    public Ticket(){

        this.setBotaoConfirmarFechamento();
        this.setBotaoCancelarFechamento();

    }

    //========================{ METÓDOS }========================//

    /**
     * Cria uma embed com as informações passadas por parâmetro.
     * @param titulo do painel de tickets.
     * @param descricao do painel de tickets.
     * @param temRequisitos verifica se há requisitos par a avaliação.
     * @return uma embed com o painel de tickets pronto.
     */
    public EmbedBuilder criarPainelTicket(String titulo, String descricao, String tipoAvaliacao, boolean temRequisitos){

        EmbedBuilder eb = new EmbedBuilder();

        //--+ Configurações da embed +--//
        eb.setColor(Color.green);
        eb.setTitle(titulo);
        eb.setDescription(descricao);
        eb.setFooter(titulo);


        if(temRequisitos){

            switch (tipoAvaliacao) {
                case "autor" ->
                        eb.addField("Requisitos para avaliação: ", "5 Capítulos para começar a avaliação - será avaliado 1 capítulo\n" +
                                "5 Capítulos para começar a postar no site.", true);
                case "tradutor" ->
                        eb.addField("Requisitos para avaliação: ", "Conhecimento em inglês\nConseguir traduzir sem a necessidade de um revisor", true);
                case "designer" ->
                        eb.addField("Requisitos para avaliação: ", "de criar novas imagens a partir de outras já existentes\n Boas habilidades em Lettering", true);
                case "avaliador" ->
                        eb.addField("Requisitos para avaliação: ", "Conhecimento avançado em Língua Portuguesa (brasileira)\n", true);

            }
        }

        return eb;
    }

    /**
     * Envia uma embed com informações do ticket no canal de logs
     * do servidor da staff.
     */
    public void notificarNovoTicketAberto(String tipoAvaliacao, String nomeAvaliando, String idDoTicket, String idCargoAvaliador){

        VulcanServidorPrincipal vulcanServidorPrincipal = new VulcanServidorPrincipal();

        Guild servidorDaStaff = Main.jda.getGuildById(vulcanServidorPrincipal.getIdServidorVulcanStaff());
        MessageChannel canalLogDeTickets = servidorDaStaff.getTextChannelById(vulcanServidorPrincipal.getIdCanalLogTickets());

        canalLogDeTickets.sendMessageEmbeds(vulcanServidorPrincipal.enviarEmbedTicketCriado(tipoAvaliacao, nomeAvaliando, vulcanServidorPrincipal.getIdServidorVulcanStaff(), idCargoAvaliador, idDoTicket).build()).queue();

    }
    public void onButtonInteraction(ButtonInteractionEvent event){

        //--+ Exibe as opção de fechamento de ticket quando o botão "fechar" é clicado +--//
        if(event.getComponentId().equals("fecharTicket")){

            try{

                //--+ Verifica se o usuário possui permissão de avaliador +--//
                if(event.getMember().hasPermission(Permission.MANAGE_CHANNEL)){
                    event.reply("Deseja realmente fechar este ticket?")
                            .addActionRow(this.getBotaoConfirmarFechamento(), this.getBotaoCancelarFechamento())
                            .queue();
                } else{
                    event.reply("Você não tem permissão para deletar este canal")
                            .setEphemeral(true)
                            .queue();
                }
            } catch(Exception ex){
                event.reply("Ocorreu um erro em ``event.getCompenetId.equal(\"closeTicket\")``, notifique ao responsável pela aplicação\nTipo de Exceção: "  + ex)
                        .setEphemeral(true)
                        .queue();
            }


        }

        //--+ Verifica a opção escolhida pelo usuário para fechar o ticket +--//
        if(event.getComponentId().equals("simFechar")){

            event.reply("O canal será deletado").queue();

            //--+ Tenta mandar uma embed no canal de logs de ticket e fechar o ticket +--//
            try{

                VulcanServidorPrincipal servidorPrincipal = new VulcanServidorPrincipal();

                Guild vulcanStaff = Main.jda.getGuildById(servidorPrincipal.getIdServidorVulcanStaff());
                MessageChannel ticketsLog = vulcanStaff.getTextChannelById(servidorPrincipal.getIdCanalLogTickets());
                ticketsLog.sendMessageEmbeds(servidorPrincipal.enviarEmbedTicketDeletado(event.getUser().getName()).build())
                        .queue();
                event.getChannel().delete().queue();

            } catch(Exception ex){
                event.reply("Ocorreu um erro em ´´event.getChannel().delete.().queue()´´, notifique o responsável pela aplicação!\n Exceção: " + ex)
                        .setEphemeral(false)
                        .queue();
            }
        }

        //--+ Cancela o evento de fechar o ticket privado +--//
        if(event.getComponentId().equals("noClose")){

            try{
                event.getMessage().delete().queue();
                event.reply("Comando cancelado com sucesso")
                        .setEphemeral(true)
                        .queue();
            } catch(Exception ex){
                event.reply("Ocorreu um erro em ``event.getMessage().delete().queue()``, notifique o responsável pela aplicação\nExceção: " + ex)
                        .setEphemeral(false)
                        .queue();
                ex.printStackTrace();
            }
        }
    }

}
