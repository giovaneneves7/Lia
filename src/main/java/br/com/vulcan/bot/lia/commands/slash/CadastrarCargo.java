package br.com.vulcan.bot.lia.commands.slash;

import br.com.vulcan.bot.lia.infrastructure.interfaces.SlashCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CadastrarCargo extends ListenerAdapter implements SlashCommand{


    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent evento) {

        if(evento.getName().equals("cadastrar-cargo")){

            if(!evento.getMember().hasPermission(Permission.BAN_MEMBERS)){

                evento.reply("Bleh! Você é fraco, falta-lhe permissões para usar este comando!")
                        .setEphemeral(true)
                        .queue();

                return;

            }

            evento.reply("Carregando... papis Neko pediu para ter paciência com a API ;)")
                    .setEphemeral(true)
                    .queue(reply -> {

                        Role cargo = Objects.requireNonNull(evento.getOption("cargo")).getAsRole();


                        OkHttpClient cliente = new OkHttpClient();

                        MediaType mediaType = MediaType.parse("application/json");

                        String requestBody = "{\"cargo\":"
                                .concat("\"".concat(cargo.getName())).concat("\","
                                        .concat("\"id\":")
                                        .concat("\"".concat(cargo.getId()).concat("\""))
                                        .concat("}"));

                        RequestBody body = RequestBody.create(mediaType, requestBody);

                        Request requisicao = new Request.Builder()
                                .url("http://apill-vulcan.vps-kinghost.net:8080/nekoyasha7/jvulcan-api/v1/novels/novel/cargo")
                                .post(body)
                                .addHeader("Api-Key", "CHAVE_DA_API")
                                .build();

                        try{

                            Response resposta = cliente.newCall(requisicao).execute();
                            String responseBody = resposta.body().string();

                            int code = resposta.code();
                            reply.editOriginal(code >= 200 && code < 300 ? "Requisição enviada com sucesso!" :
                                            "Ops, a requisição retornou o código ".concat(String.valueOf(code)))
                                    .queue();

                            System.out.println("Resposta da API: ".concat(responseBody));

                        } catch(Exception ex){
                            ex.printStackTrace();
                        }
                    });


        }

    }
}
