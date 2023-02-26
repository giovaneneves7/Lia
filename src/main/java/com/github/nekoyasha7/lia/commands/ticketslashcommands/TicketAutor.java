//<<< Package >>>//
package com.github.nekoyasha7.lia.commands.ticketslashcommands;
//<<< End Package >>>//

//<<< Imports >>>//
import com.github.nekoyasha7.lia.main.Main;
import com.github.nekoyasha7.lia.util.Vulcan;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.Color;

import java.util.EnumSet;
//<<< Imports >>>//
//<<< End Imports >>>//
/*
 /* @author NekoYasha
 */
//<<< Class >>>//
public class TicketAutor extends ListenerAdapter {

    EmbedBuilder eb = new EmbedBuilder();
    String buttonText;

    //--+Variáveis Globais de cargos (roles)+--//
    String recrutamentoID = "882517867755622430";
    String avaliando = "";
    String avaliadorId = "874810883988127764";

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){

        //--+Verifica se o usuário é Bot--+//
        if(event.getMember().getUser().isBot()) return;

        if(event.getName().equalsIgnoreCase("criar-painel-ticket-autor")){

            //--+Verifica as permissões do usuário+--//
            if(event.getMember().hasPermission(Permission.MANAGE_CHANNEL)){

                String title = event.getOption("titulo").getAsString();
                String description = event.getOption("descricao").getAsString();
                buttonText = event.getOption("botao").getAsString();

                //--+Embed setups+--//
                eb.setColor(Color.green);
                eb.setTitle(title);
                eb.setDescription(description);
                eb.addField("Requisitos para avaliação: ", "5 Capítulos para começar a avaliação - será avaliado 1 capítulo\n" +
                        "5 Capítulos para começar a postar no site.", true);
                eb.setFooter(title);
                eb.addBlankField(true);


                //--+ Envia e Embed Configurada +--//
                event.getChannel().sendMessageEmbeds(eb.build())
                        .addActionRow(startButton(buttonText))
                        .queue();

                event.reply("Embed construida com sucesso!")
                        .setEphemeral(true)
                        .queue();
                eb.clearFields();

            } else
                event.reply("Você não tem permissão para usar este comando! ;)")
                        .setEphemeral(true)
                        .queue();
        }
    }

    //--+ Evento de quando o usuário clica no botão +--//
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event){

        if(event.getComponentId().equals("startButtonAutor")){

            //--+ Instância a Class Vulcan +--//
            Vulcan vulcan = new Vulcan();

            //--+ Envia mensagem ephemeral quando o ticket é aberto +--//
            event.reply("Ticket aberto com sucesso!")
                    .setEphemeral(true)
                    .queue();

            Member member = event.getMember();
            Guild guild = event.getGuild();

            Category category = guild.getCategoryById(vulcan.getCategoryRecrutamentoId());

            //--+ Cria um Ticket Privado e atualiza as permissões do usuário +--//
            TextChannel privateTicket = guild.createTextChannel("ticket-" + member.getUser().getName())
                    .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .addPermissionOverride(guild.getRoleById(avaliadorId), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .setParent(category)
                    .complete();

            //--+ Pega o ID do Ticket Privado +--//
            String id = privateTicket.getId();

            //--+ Envia mensagem no ticket privado para o avaliando +--//

            privateTicket.sendMessage(
                                      "> **Avaliando:** " + member.getUser().getName() + "\n"                                                                    +
                                      "> **Tipo de Avaliação:** Autor\n"                                                                                              +
                                      "> **Tipo de Avaliador:** " +  guild.getRoleById(avaliadorId).getAsMention()                                                    +
                                      "\n\n"                                                                                                                            +
                                      "Eiiii, " + member.getAsMention() + ", tô felizona que tenha optado por fazer uma avaliação na Vulcan Novels para **Autor**.\n" +
                                      "Para iniciarmos o processo, digite:\n ``/iniciar-avaliacao``\n")
                    .addActionRow(Button.danger("closeTicket", "Fechar ticket"))
                    .queue();

            //--+Pega o ID do servidor da staff e envia informações dos novos tickets em um canal específico+--//
            Guild vulcanStaff = Main.jda.getGuildById(vulcan.getServerVulcanStaffId());
            MessageChannel ticketsLog = vulcanStaff.getTextChannelById(vulcan.getChannelTicketsLogId());
            ticketsLog.sendMessageEmbeds(vulcan.createEmbedNewTicket("Autor", member.getUser().getName(), vulcan.getServerVulcanStaffId(), vulcan.getRoleAvaliadorId(), id).build()).queue();

            //--+ Atribui o nome do avaliando à variável 'avaliand' +--//
            avaliando = event.getUser().getName();
        }

        //Close the ticket when the button is clicked
        if(event.getComponentId().equals("closeTicket")){

            try{
                if(event.getMember().hasPermission(Permission.MANAGE_CHANNEL)){
                    event.reply("Deseja realmente fechar este ticket?")
                            .addActionRow(yesCloseButton(), noCloseButton())
                            .queue();
                } else{
                    event.reply("Você não tem permissão para deletar este canal")
                            .setEphemeral(true)
                            .queue();
                }
            } catch(Exception ex){
                event.reply("Ocorreu um erro em **'event.getCompenetId.equal(\"closeTicket\")'**, notifique ao responsável pela aplicação\nTipo de Exceção: "  + ex)
                        .setEphemeral(false)
                        .queue();
            }


        }

        if(event.getComponentId().equals("yesClose")){

            event.reply("O canal será deletado").queue();

            //--+ Tenta mandar uma embed no canal de logs de ticket e fechar o ticket +--//
            try{

                Vulcan vulcan = new Vulcan();

                Guild vulcanStaff = Main.jda.getGuildById(vulcan.getServerVulcanStaffId());
                System.out.println("ok1");
                MessageChannel ticketsLog = vulcanStaff.getTextChannelById(vulcan.getChannelTicketsLogId());
                System.out.println("ok2");
                ticketsLog.sendMessageEmbeds(vulcan.deletedTicketEmbed(event.getUser().getName(), avaliando).build())
                        .queue();
                System.out.println("ok3");
                event.getChannel().delete().queue();
                System.out.println("ok4");

            } catch(Exception ex){
                event.reply("Ocorreu um erro em \"event.getChannel().delete.().queue()\", notifique o responsável pela aplicação!\n Exceção: " + ex)
                        .setEphemeral(false)
                        .queue();
            }
        }

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
            }
        }

    }

    //Buttons
    protected Button startButton(String buttonText){
        return Button.success("startButtonAutor", buttonText);
    }

    protected Button yesCloseButton(){
        return Button.danger("yesClose", "Sim");
    }

    protected Button noCloseButton(){
        return Button.success("noClose", "Não");
    }

}
