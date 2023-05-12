//========================{ PACKAGE }========================//
package com.github.nekoyasha7.lia.model.tickets;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
//========================{ FIM IMPORTS }========================//

/**
 * @author NekoYasha
 */
public class CreateTicketPanel extends Ticket {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        //--+ Verifica se o usuário é Bot +--//
        if (event.getMember().getUser().isBot()) return;

        if (event.getName().equalsIgnoreCase("criar-painel-ticket-avaliacao")) {

            //--+ Verifica as permissões do usuário +--//
            if (event.getMember().hasPermission(Permission.MANAGE_CHANNEL)) {

                String tipoAvaliacao;
                String titulo;
                String descricao;
                String textoBotao;
                boolean temRequisitos;

                //--+ Extração de dados dos parâmetros do SlashCommands +--//
                try {

                    tipoAvaliacao = event.getOption("tipo").getAsString().toLowerCase();
                    titulo = event.getOption("titulo").getAsString();
                    descricao = event.getOption("descricao").getAsString();
                    textoBotao = event.getOption("botao").getAsString();
                    temRequisitos = event.getOption("requisitos").getAsBoolean();

                } catch (NullPointerException ex) {
                    event.reply("Ops, parece que você não preencheu os parâmetros corretamente...")
                            .setEphemeral(true)
                            .queue();
                    ex.printStackTrace();
                    return;
                }

                //--+ Seta o tipo de avaliação +--//
                switch (tipoAvaliacao) {
                    case "autor" -> this.setBotaoTipoAvaliacao(Button.success("comecarAvaliacaoAutor", textoBotao));
                    case "tradutor" ->
                            this.setBotaoTipoAvaliacao(Button.success("comecarAvaliacaoTradutor", textoBotao));
                    case "designer" ->
                            this.setBotaoTipoAvaliacao(Button.success("comecarAvaliacaoDesigner", textoBotao));
                    case "avaliador" ->
                            this.setBotaoTipoAvaliacao(Button.success("comecarAvaliacaoAvaliador", textoBotao));
                }

                //--+ Configura a Embed +--//
                EmbedBuilder eb;
                eb = criarPainelTicket(titulo, descricao, tipoAvaliacao, temRequisitos);

                //--+ Envia e Embed Configurada +--//
                event.getChannel().sendMessageEmbeds(eb.build())
                        .addActionRow(this.getBotaoTipoAvaliacao())
                        .queue();

                //--+ Envia mensagem de confirmação da operação +--//
                event.reply("Embed construida com sucesso!")
                        .setEphemeral(true)
                        .queue();

                //--+ Limpa a embed +--//
                eb.clearFields();

            } else
                event.reply("Você não tem permissão para usar este comando! ;)")
                        .setEphemeral(true)
                        .queue();
        }
    }

}
