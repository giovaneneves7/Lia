//========================{ Package }========================//
package br.com.vulcan.bot.lia.commands.slash;
//========================{ FIM Package }========================//

//========================{ IMPORTS }========================//
import br.com.vulcan.bot.lia.entidades.Saudacao;
import br.com.vulcan.bot.lia.infrastructure.facade.FacadeInstance;
import br.com.vulcan.bot.lia.infrastructure.interfaces.SlashCommand;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
//========================{ FIM IMPORTS }========================//

/**
 * @author NekoYasha
 */

public class SenhaAvaliacao extends ListenerAdapter implements SlashCommand{

    //========================{ ATRIBUTOS }========================//
    private final String LINK_TOFE = "https://docs.google.com/document/d/1PGIebJHXwJGONP5f4A5b6EisVEz8XZj4-Wl7OKL3Y6A/edit";
    private final Button BTN_JA_LI_O_TOFE = Button.primary("leituraCompleta", "Já Li o TOFE");
    private final Button BTN_LER_TOFE = Button.link(LINK_TOFE, "Ler o TOFE");

    //========================{ METÓDOS }========================//
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent evento) {

        //--+ Verifica se quem usou o Slash Command é bot +--//
        if(evento.getMember().getUser().isBot()) return;

        if(evento.getName().equals("senha-avaliacao")){

            if(evento.getOption("senha").getAsString().equalsIgnoreCase("banana")){

                evento.reply("*Palmas, palmas!* Boa! Podemos continuar!\n Este aqui é o TOFE (Treinamento orientado a futuros escravos '-')."
                                .concat("Preciso que leia ele para dar início à avaliação. E leia com atenção, viu?"))
                        .addActionRow(BTN_JA_LI_O_TOFE, BTN_LER_TOFE)
                        .setEphemeral(false)
                        .queue();

            } else{

                Saudacao mensagem = new Saudacao();
                evento.reply(mensagem.pegarDesgostoAleatorio())
                        .setEphemeral(false)
                        .queue();

            }
        }


    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent evento) {

        if(evento.getComponentId().equals("leituraCompleta")){

            evento.reply("E aí, depois de ler o TOFE, você acha que precisa de 24 horas para deixar o capítulo adequado aos requisitos mínimos(TOFE) ou podemos prosseguir com a avaliação?\n"
                    .concat("*Responda com ``preciso de tempo, Lia`` ou ``podemos prosseguir, Lia``*"))
                    .setEphemeral(false)
                    .queue();
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent evento) {


        Message mensagem = evento.getMessage();
        String conteudo = mensagem.getContentRaw();
        CompletableFuture<List<Member>> avaliadoresFuture = FacadeInstance.getInstance().sortAvaliadores();


        if(conteudo.equalsIgnoreCase("preciso de tempo, Lia")){

            String msgEscolhaAvaliador = "Precisa de tempo, não é? Beleza, a partir de agora você tem 24 Horas para revisar o seu capítulo.\n Ah, para acabarmos aqui, escolha um dos avaliadores para dar inicio à sua avaliação: \n\n";

            evento.getChannel().sendMessage(msgEscolhaAvaliador.concat("``CARREGANDO...``"))
                    .queue(message -> {

                        StringBuilder lista = new StringBuilder();
                        AtomicReference<String> msg = new AtomicReference<>("");

                        avaliadoresFuture.thenAccept(avaliadores -> {


                            for(Member  avaliador : avaliadores){

                                lista.append("- ".concat(avaliador.getEffectiveName())
                                        .concat(" (")
                                        .concat(avaliador.getUser().getAsTag()
                                                .concat(")\n")));

                            }

                            msg.set(msgEscolhaAvaliador.concat("** - LISTA DE AVALIADORES - **\n".concat("```").concat(lista.toString()).concat("```")));

                            message.editMessage(msg.get()).queue();

                        });

                    });
        }

        if(conteudo.equalsIgnoreCase("podemos prosseguir, Lia")){
            String msgEscolhaAvaliador = "Ótimo! Para acabarmos aqui, escolha um dos avaliadores para dar inicio à sua avaliação:\n\n";

            evento.getChannel().sendMessage(msgEscolhaAvaliador.concat("``CARREGANDO...``"))
                    .queue(message -> {

                        StringBuilder lista = new StringBuilder();
                        AtomicReference<String> msg = new AtomicReference<>("");

                        avaliadoresFuture.thenAccept(avaliadores -> {


                            for(Member  avaliador : avaliadores){

                                lista.append("- ".concat(avaliador.getEffectiveName())
                                        .concat(" (")
                                        .concat(avaliador.getUser().getAsTag()
                                                .concat(") \n")));

                            }

                            msg.set(msgEscolhaAvaliador.concat("** - LISTA DE AVALIADORES - **\n".concat("```").concat(lista.toString()).concat("```")));

                           message.editMessage(msg.get()).queue();

                        });

                    });

        }
    }
}


