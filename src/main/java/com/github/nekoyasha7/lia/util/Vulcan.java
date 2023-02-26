package com.github.nekoyasha7.lia.util;

import java.util.Calendar;

import com.github.nekoyasha7.lia.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;

public class Vulcan {

    //--+ IDs +--//
    private String categoryRecrutamentoId = "882517867755622430";
    private String roleRecrutaId = "432018125031211009";

    public String getRoleRecrutaId() {
        return roleRecrutaId;
    }

    public void setRoleRecrutaId(String roleRecrutaId) {
        this.roleRecrutaId = roleRecrutaId;
    }

    public String getRoleCidadaoVulcanicoId() {
        return roleCidadaoVulcanicoId;
    }

    public void setRoleCidadaoVulcanicoId(String roleCidadaoVulcanicoId) {
        this.roleCidadaoVulcanicoId = roleCidadaoVulcanicoId;
    }

    private  String roleCidadaoVulcanicoId = "769367753324101643";
    private String roleAvaliadorId = "874810883988127764";
    private String serverVulcanStaffId = "878870920817684490";
    private String channelTicketsLogId = "1076151069366833173";

    //--+ Nomes +--//
    private String roleAvaliadorName = "●❯─•〔Avaliador Novel〕•─❮●";

    //--+ Getters e Setters +--//
    public String getCategoryRecrutamentoId() {
        return categoryRecrutamentoId;
    }

    public void setCategoryRecrutamentoId(String categoryRecrutamentoId) {
        this.categoryRecrutamentoId = categoryRecrutamentoId;
    }

    public String getRoleAvaliadorId() {
        return roleAvaliadorId;
    }

    public void setRoleAvaliadorId(String roleAvaliadorId) {
        this.roleAvaliadorId = roleAvaliadorId;
    }

    public String getServerVulcanStaffId() {
        return serverVulcanStaffId;
    }

    public void setServerVulcanStaffId(String serverVulcanStaffId) {
        this.serverVulcanStaffId = serverVulcanStaffId;
    }

    public String getChannelTicketsLogId() {
        return channelTicketsLogId;
    }

    public void setChannelTicketsLogId(String channelTicketsLogId) {
        this.channelTicketsLogId = channelTicketsLogId;
    }

    public String getRoleAvaliadorName() {
        return roleAvaliadorName;
    }

    public void setRoleAvaliadorName(String roleAvaliadorName) {
        this.roleAvaliadorName = roleAvaliadorName;
    }

    //--+ Methods +--//
    //--+ Envia uma embed com informações de novo tickets ao servidor interno da vulcan +--//
    public EmbedBuilder createEmbedNewTicket(String tipoAvaliacao, String avaliando, String staffServerId, String avaliadorId, String ticketId){

        Calendar calendar = Calendar.getInstance();

        EmbedBuilder embed = new EmbedBuilder();

        Guild vulcanStaff = Main.jda.getGuildById(staffServerId);
        embed.setTitle("Novo Ticket de **" + tipoAvaliacao + "** aberto!");
        embed.setDescription("**Avaliando:** " + avaliando + "\n**Tipo de Avaliação:** " + tipoAvaliacao + "\n**Tipo de avaliador**: " + vulcanStaff.getRoleById("879079664306499585").getAsMention() + "\n**JumpLink:** <#" + ticketId + ">" + "\n" +
                                "**Data:** " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + "\n" +
                                "**Horário:** " + calendar.getTime());

        return embed;

    }

    public EmbedBuilder deletedTicketEmbed(String avaliador, String avaliando){

        Calendar calendar = Calendar.getInstance();

        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("Um ticket foi fechado!");
        embed.setDescription("**Avaliando:** " + avaliando + "\n" +
                             "**Avaliador:** "  +avaliador + "\n" +
                             "**Data:** " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" +  calendar.get(Calendar.YEAR) + "\n" +
                             "**Horário:** " + calendar.getTime());
        return embed;
    }
}
