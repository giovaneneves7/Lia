//<<< Package >>>//
package com.github.nekoyasha7.lia.commands.ticketslashcommands;
//<<< End Package >>>//

//<<< Imports >>>//
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
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

    //Variables
    String buttonText;
    String recrutamentoID = "882517867755622430";
    String avaliadorRoleName = "●❯─•〔Avaliador Novel〕•─❮●";
    String avaliadorId = "874810883988127764";

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){

        if(event.getMember().getUser().isBot()) return;

        if(event.getName().equalsIgnoreCase("criar-painel-ticket-autor")){

            if(event.getMember().hasPermission(Permission.MANAGE_CHANNEL)){
                String title = event.getOption("titulo").getAsString();
                String description = event.getOption("descricao").getAsString();
                buttonText = event.getOption("botao").getAsString();

                //Embed setups
                eb.setColor(Color.green);
                eb.setTitle(title);
                eb.setDescription(description);
                eb.addField("Requisitos para avaliação: ", "5 Capítulos para começar a avaliação - será avaliado 1 capítulo\n" +
                        "5 Capítulos para começar a postar no site.", true);
                eb.setFooter(title);
                eb.addBlankField(true);


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

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event){

        if(event.getComponentId().equals("startButtonAutor")){

            String roles = String.valueOf(event.getMember().getRoles());

            event.reply("Ticket aberto com sucesso!")
                    .setEphemeral(true)
                    .queue();

            Member member = event.getMember();
            Guild guild = event.getGuild();

            Category category = guild.getCategoryById(recrutamentoID);

            TextChannel privateTicket = guild.createTextChannel("ticket-" + member.getUser().getName())
                    .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .addPermissionOverride(guild.getRoleById(avaliadorId), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .setParent(category)
                    .complete();


            Role role = guild.getRolesByName(avaliadorRoleName, true)
                    .get(0);


            for(Member roleMember : guild.getMembersWithRoles(role)){
                roleMember.getUser().openPrivateChannel().queue((channel) -> {
                    channel.sendMessage("Um novo ticket para **Autor** foi aberto por " + member.getUser().getName() + "\n Verifique!")
                            .queue();
                });
            }


            privateTicket.sendMessage("Olá, " + member.getAsMention() + " Fico contente que tenha optado por fazer uma avaliação na Vulcan Novels pra **Autor**.\n" +
                            "Para iniciarmos o processo, digite **/iniciar-avaliacao**\n" + guild.getRoleById(avaliadorId).getAsMention())
                    .addActionRow(Button.danger("closeTicket", "Fechar ticket"))
                    .queue();
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

            try{
                event.getChannel().delete().queue();
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
                event.reply("Ocorreu um erro em \"event.getMessage().delete().queue()\", notifique o responsável pela aplicação\nExceção: " + ex)
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
