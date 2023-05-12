//<<< Package >>>//
package com.github.nekoyasha7.lia.model.slashcommands.adm;
//<<< End Package >>>//

//<<< Imports >>>//
import com.github.nekoyasha7.lia.novel.model.Novel;
import com.github.nekoyasha7.lia.model.server.VulcanServidorPrincipal;

import lombok.Data;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.Color;
//<<< End Imports >>>//
/**
/*@author Nekoyasha
 */
@Data
public class AnunciarNovel extends ListenerAdapter {

    //-------------------------------{Atributos}-------------------------------//

    private Role cargoNovel;
    private Button BotaoLinkNovel;

    //-------------------------------{Getters e Setters}-------------------------------//

    private Button setBotaoLinkNovel(String link){
        return Button.link(link, "Ler novel");
    }

    private Button getBotaoLinkNovel(){
        return Button.success("pegarCargo", "Pegar tag");
    }

    //-------------------------------{Metódos}-------------------------------//

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){

        //--+ Verifica se o usuário é bot +--//
        if(event.getMember().getUser().isBot()) return;

        if(event.getName().equalsIgnoreCase("anunciar-novel")){

            //--+ Instancia a Classe Novel +--//
            Novel novel = new Novel(event.getOption("cargo").getAsRole().getName(),
                                    event.getOption("autor-ou-tradutor").getAsUser(),
                                    event.getOption("autor-original").getAsString(),
                                    event.getOption("sinopse").getAsString(),
                                    event.getOption("nacionalidade").getAsString(),
                                    event.getOption("generos").getAsString(),
                                    event.getOption("cargo").getAsRole());

            this.setCargoNovel(event.getGuild().getRoleById(novel.getCargo().getId()));

            VulcanServidorPrincipal vulcanPrincipal = new VulcanServidorPrincipal();

            Channel tagsChannel = event.getGuild().getTextChannelById(vulcanPrincipal.getIdCanalTags());
            String mencaoCanalTags = tagsChannel.getAsMention();

            Guild guild = event.getGuild();
            String mensagemEmbed = criarMensagemEmbed(novel.getTitulo(), novel.getAutorOuTradutor(), novel.getAutorOriginal(), novel.getGeneros(),
                                                      novel.getNacionalidade(), novel.getSinopse(),
                                                      mencaoCanalTags);

            //--+ Verifica se é necessário marcar everyone +--//
            mensagemEmbed += (event.getOption("everyone").getAsBoolean())
                    ?
                        "\n\n|| "    +
                        "@everyone " +
                        guild.getRoleById(vulcanPrincipal.getIdCargoCidadaoVulcanico()).getAsMention() + " " +
                        guild.getRoleById(vulcanPrincipal.getIdCargoRecruta()).getAsMention() + " " +
                        " ||"
                    :
                        "";

            //--+ Configura a Embed +--//
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(getCorNacionalidade(event.getOption("nacionalidade").getAsString()));
            embed.setAuthor("Autor ou tradutor da novel: " + event.getOption("autor-ou-tradutor").getAsUser().getName());
            embed.setTitle(event.getOption("cargo").getAsRole().getName(), event.getOption("link").getAsString());
            embed.setDescription(mensagemEmbed);
            embed.setFooter("Clique no título da novel para ler", "https://user-images.githubusercontent.com/5415001/39671960-87970a08-5143-11e8-8126-20b421cc00a4.png");
            embed.setImage(event.getOption("capa").getAsString());
            embed.addBlankField(false);

            //--+ Envia a Embed +--//
            event.replyEmbeds(embed.build())
                    .addActionRow(this.setBotaoLinkNovel((event.getOption("link").getAsString())), this.getBotaoLinkNovel())
                    .queue();
        }
    }

    /**
     *
     * @param tituloOriginal da novel a ser anunciada.
     * @param autorOuTradutor da novel a ser anunciada.
     * @param autorOriginal da novel a ser anunciada.
     * @param generos da novel.
     * @param nacionalidade da novel.
     * @param sinopse da novel.
     * @param canalTags onde estará disponível a assinatura da novel.
     * @return mensagem com as informações da novel a ser anunciada.
     */
    private String criarMensagemEmbed(String tituloOriginal, User autorOuTradutor, String autorOriginal, String[] generos,
                                      String nacionalidade, String sinopse, String canalTags){

        String generosNovel = "";
        for(String genero : generos){

            generosNovel += genero + ", ";

        }

        String mensagem = "**Título original:** " + tituloOriginal                + "\n\n" +
                "**Autor ou Tradutor:** "  + autorOuTradutor.getAsMention()       + "\n\n" +
                "**Autor original:** " + autorOriginal                            + "\n\n" +
                "**Gêneros da novel:** " + generosNovel                           + "\n\n" +
                "**Nacionalidade da novel:** " + nacionalidade                    + "\n\n" +
                "**Sinopse:** " + sinopse                                         + "\n\n" +
                "Tag disponível em: " + canalTags;

        return mensagem;

    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event){

        //--+ INFO: Dar o cargo da novel ao usuário que clicou o botão +--//
        if(event.getComponentId().equals("pegarCargo")){

            try{

                event.getGuild().addRoleToMember(event.getMember(), this.getCargoNovel())
                        .queue();

                event.reply("Cargo " + this.getCargoNovel().getAsMention() + " adquirido com sucesso!")
                        .setEphemeral(true)
                        .queue();

                System.out.println(event.getUser().getName() + " Pegou o cargo " + this.getCargoNovel().getName());

            } catch(Exception ex){

                event.reply("Ops, acho que aconteceu um erro do lado do servidor\n Exceção: ``"  + ex  +"``")
                        .setEphemeral(true)
                        .queue();

            }
        }

    }

    /**
     * Retorna uma cor com base na nacionalidade da novel.
     * @param nacionalidade da novel que será anunciada.
     * @return cor da nacionalidade.
     */
    public Color getCorNacionalidade(String nacionalidade){

        return switch (nacionalidade) {
            case "brasileira" -> Color.green;
            case "japonesa" -> Color.white;
            case "ocidental" -> Color.cyan;
            case "coreana" -> Color.MAGENTA;
            case "chinesa" -> Color.red;
            default -> Color.orange;
        };
    }
}
