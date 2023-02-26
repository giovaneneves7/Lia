//<<< Package >>>//
package com.github.nekoyasha7.lia.commands.admslashcommands;
//<<< End Package >>>//

//<<< Imports >>>//
import com.github.nekoyasha7.lia.util.Vulcan;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.Color;
//<<< End Imports >>>//
/*
/*@author Nekoyasha
 */
//<<< Class >>>//
public class AnunciarNovel extends ListenerAdapter {

    //--+ Attributes +--//
    Role novelRole;

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){

        //--+ Verifica se o usuário é bot +--//
        if(event.getMember().getUser().isBot()) return;

        if(event.getName().equalsIgnoreCase("anunciar-novel")){

            //--+ Instancia a Classe Vulcan +--//
            Vulcan vulcan = new Vulcan();
            String idString = event.getOption("cargo").getAsRole().getId();

            novelRole = event.getGuild().getRoleById(idString);

            String tagsChannelId = "857365277082779658";

            Channel tagsChannel = event.getGuild().getTextChannelById(tagsChannelId);

            String tagsChannelMention = tagsChannel.getAsMention();

            String embedMessage = "**Título original:** " + event.getOption("cargo").getAsRole().getAsMention() + "\n\n" +
                    "**Autor original:** " + event.getOption("autor").getAsMember().getAsMention() + "\n\n" +
                    "**Gêneros da novel:** " + event.getOption("generos").getAsString() + "\n\n" +
                    "**Nacionalidade da novel:** " + event.getOption("nacionalidade").getAsString() + "\n\n" +
                    "**Sinopse:** " + event.getOption("sinopse").getAsString() + "\n\n" +
                    "Tag disponível em: " + tagsChannelMention;

            EmbedBuilder embed = new EmbedBuilder();

            //--+ Verifica se é necessário marcar everyone +--//
            Guild guild = event.getGuild();
            embedMessage += (event.getOption("everyone").getAsBoolean())
                    ?
                        "\n\n|| " +
                        "@everyone " +
                        guild.getRoleById(vulcan.getRoleCidadaoVulcanicoId()).getAsMention() + " " +
                        guild.getRoleById(vulcan.getRoleRecrutaId()).getAsMention() + " " +
                        " ||"
                    :
                        "";

            //--+ Embed setups +--//
            embed.setColor(corNacionalidade(event.getOption("nacionalidade").getAsString()));
            embed.setAuthor("Autor da novel: " + event.getOption("autor").getAsUser().getName());
            embed.setTitle(event.getOption("cargo").getAsRole().getName(), event.getOption("link").getAsString());
            embed.setDescription(embedMessage);
            embed.setFooter("Clique no título da novel para ler", "https://user-images.githubusercontent.com/5415001/39671960-87970a08-5143-11e8-8126-20b421cc00a4.png");
            embed.setImage(event.getOption("capa").getAsString());
            embed.addBlankField(false);

            event.replyEmbeds(embed.build())
                    .addActionRow(linkNovelButton(event.getOption("link").getAsString()), getCargoButton())
                    .queue();
        }
    }

    private Button linkNovelButton(String link){
        return Button.link(link, "Ler novel");
    }

    private Button getCargoButton(){
        return Button.success("getCargo", "Pegar tag");
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event){

        if(event.getComponentId().equals("getCargo")){
            try{

                event.getGuild().addRoleToMember(event.getMember(), novelRole)
                        .queue();
                event.reply("Cargo " + novelRole.getAsMention() + " adquirido com sucesso!")
                        .setEphemeral(true)
                        .queue();

            } catch(Exception ex){

                event.reply("Ops, acho que aconteceu um erro do lado do servidor\n Exceção: ``"  + ex  +"``")
                        .setEphemeral(true)
                        .queue();

            }
        }

    }

    public Color corNacionalidade(String nacionalidade){

        //--+ Retorna uma cor com base na nacionalidade da novel +--//
        switch(nacionalidade){

            case "brasileira":
                return Color.green;
            case "japonesa":
                return Color.white;
            case "ocidental":
                return Color.cyan;
            case "coreana":
                return Color.MAGENTA;
            case "chinesa":
                return Color.red;
            default:
                return Color.orange;

        }
    }
}
