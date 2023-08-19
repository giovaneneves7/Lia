//========================{ PACKAGE }========================//
package br.com.vulcan.bot.lia.entidades;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import java.util.Calendar;
import java.util.EnumSet;

import br.com.vulcan.bot.lia.main.Main;

import lombok.Data;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

//========================{ FIM IMPORTS }========================//

@Data
public class VulcanServidorPrincipal {

    // ========================{ ATRIBUTOS }========================//
    public static final String ID = "410158562929803275";
    public static final String CARGO_AVALIADOR = "●❯─•〔Avaliador Novel〕•─❮●";
    public static final String CARGO_AVALIADOR_ID = "874810883988127764";
    private String idCargoRecruta;
    private String idCargoCidadaoVulcanico;
    private String idCategoriaRecrutamento;
    private String idCargoAvaliador;
    private String idCargoViceLider;
    private String idServidorVulcanStaff;
    private String idCanalLogTickets;
    private String idCanalTags;
    // ========================{ CONSTRUTOR }========================//

    /**
     * Insere os dados do servidor da Vulcan.
     */
    public VulcanServidorPrincipal() {

        // --+ IDs de Cargos +--//
        this.setIdCargoRecruta("432018125031211009");
        this.setIdCargoCidadaoVulcanico("769367753324101643");
        this.setIdCargoViceLider("771617590853369896");

        // --+ IDs de Categorias +--//
        this.setIdCategoriaRecrutamento("882517867755622430");

        // --+ IDs de Servidores +--//
        this.setIdServidorVulcanStaff("878870920817684490");

        // --+ Ids de Canais +--//
        // -+ Servidor Staff +-//
        this.setIdCanalLogTickets("1076151069366833173");

        // -+ Servidor Principal +-//
        this.setIdCanalTags("857365277082779658");
    }

    // ========================{ METÓDOS }========================//

    /**
     *
     * @param servidorPrincipal onde o ticket privado será aberto.
     * @param avaliando         que será avaliado no ticket privado.
     * @param idCargoAvaliador  que avaliará o avaliado.
     * @param categoria         onde o ticket privado será aberto.
     * @return um ticket privado configurado com as informações passadas por
     *         parâmetro.
     */
    public TextChannel criarNovoTicketPrivado(Guild servidorPrincipal, Member avaliando, String idCargoAvaliador,
            Category categoria) {

        TextChannel ticketPrivado = servidorPrincipal.createTextChannel("ticket-" + avaliando.getUser().getName())
                .addPermissionOverride(avaliando,
                        EnumSet.of(Permission.VIEW_CHANNEL, Permission.USE_APPLICATION_COMMANDS), null)
                .addPermissionOverride(servidorPrincipal.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                .addPermissionOverride(servidorPrincipal.getRoleById(idCargoAvaliador),
                        EnumSet.of(Permission.VIEW_CHANNEL), null)
                .setParent(categoria)
                .complete();

        return ticketPrivado;

    }

    /**
     * Envia uma embed com informaçõs quando um ticket privado é aberto.
     * 
     * @param tipoAvaliacao     do ticket privado.
     * @param avaliando         que será avaliado pelo avaliador.
     * @param idDoServidorStaff onde a embed será enviada.
     * @param avaliadorId       que avaliará o avaliando.
     * @param ticketId
     * @return uma embed com informaçõs do ticket aberto.
     */
    public EmbedBuilder enviarEmbedTicketCriado(String tipoAvaliacao, String avaliando, String idDoServidorStaff,
            String avaliadorId, String ticketId) {

        Calendar calendar = Calendar.getInstance();

        EmbedBuilder embed = new EmbedBuilder();

        Guild vulcanStaff = Main.jda.getGuildById(idDoServidorStaff);
        embed.setTitle("Novo Ticket de **" + tipoAvaliacao + "** aberto!");

        embed.setDescription("**Avaliando:** " + avaliando + "\n**Tipo de Avaliação:** " + tipoAvaliacao
                + "\n**Tipo de avaliador**: " + vulcanStaff.getRoleById(avaliadorId).getAsMention()
                + "\n**ID do Ticket:** ".concat(ticketId) + "\n**JumpLink:** <#" + ticketId + ">" + "\n" +
                "**Data:** " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/"
                + calendar.get(Calendar.YEAR) + "\n" +
                "**Horário:** " + calendar.getTime());

        return embed;

    }

    /**
     * Envia uma Embed no canal de log de tickets quando um ticket privado é
     * deletado.
     * 
     * @param avaliador que fechou o ticket privado.
     * @return Embed com informações do ticket privado fechado.
     */
    public EmbedBuilder enviarEmbedTicketDeletado(String avaliador) {

        Calendar calendar = Calendar.getInstance();

        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("Um ticket foi fechado!");
        embed.setDescription(
                "**Avaliador:** " + avaliador + "\n" +
                        "**Data:** " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/"
                        + calendar.get(Calendar.YEAR) + "\n" +
                        "**Horário:** " + calendar.getTime());

        return embed;
    }
}
