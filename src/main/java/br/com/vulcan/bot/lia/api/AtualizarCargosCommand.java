package br.com.vulcan.bot.lia.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.vulcan.bot.lia.main.Main;

import br.com.vulcan.bot.lia.entidades.Novel;
import br.com.vulcan.bot.lia.api.resource.CargoResource;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.ArrayList;
import java.util.List;

public class AtualizarCargosCommand extends ListenerAdapter
{

    /**
     * Atualiza o cargo das novels no banco de dados com base nos cargos do
     * servidor principal.
     * @param evento O evento disparado pelo usuário.
     */
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent evento) {

        if(evento.getMember().getUser().isBot()) return;

        if(evento.getName().equals("atualizar-cargos"))
        {

            if(evento.getMember().hasPermission(Permission.MANAGE_ROLES)) {

                Guild servidorDiscord = Main.jda.getGuildById("410158562929803275");

                if (servidorDiscord != null) {


                    List<Role> cargos = servidorDiscord.getRoles();
                    List<CargoResource> cargosASeremEnviados = new ArrayList<>();

                    for (Role cargo : cargos) {

                        CargoResource resource = new CargoResource();

                        resource.setNome(cargo.getName());
                        resource.setId(cargo.getId());

                        cargosASeremEnviados.add(resource);

                    }

                    String urlEndpoint = evento.getOption("api-url").getAsString();
                    urlEndpoint = urlEndpoint.concat("nekoyasha7/jvulcan-api/v1/novels/novel");

                    String cargosJson = "";
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        cargosJson = objectMapper.writeValueAsString(cargosASeremEnviados);

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        evento.reply("Ops, ocorreu um erro ao serializar a lista!")
                                .setEphemeral(true)
                                .queue();
                    }

                    HttpClient httpCliente = HttpClient.newHttpClient();
                    HttpRequest requisicao = HttpRequest.newBuilder()
                            .uri(URI.create(urlEndpoint))
                            .header("Content-Type", "application/json")
                            .PUT(HttpRequest.BodyPublishers.ofString(cargosJson))
                            .build();
                    try {

                        HttpResponse<String> resposta = httpCliente.send(requisicao, HttpResponse.BodyHandlers.ofString());
                        int codigoResposta = resposta.statusCode();

                        switch (codigoResposta) {
                            case 200:

                                String responseBody = resposta.body();

                                List<Novel> novels = objectMapper.readValue(responseBody, new TypeReference<List<Novel>>() {
                                });

                                evento.reply("Requisicao enviada com sucesso!\n")
                                        .setEphemeral(true)
                                        .queue();

                                StringBuilder listaNovels = new StringBuilder();
                                for (Novel novel : novels) {
                                    listaNovels.append(novel.getTitulo()).append(" - ").append(novel.getIdCargo()).append(" - ").append(novel.getAutorOuTradutor()).append("\n");
                                }

                                evento.getChannel().sendMessage("```".concat(listaNovels.toString()).concat("```"))
                                        .queue();
                            default:
                                evento.reply("Opa, a requisição retornou um ".concat(String.valueOf(codigoResposta)))
                                        .queue();
                        }
                    } catch (IOException | InterruptedException e) {
                        System.out.println(e.getMessage());
                    }

                }
            } else
            {
                evento.reply("Bleh! Você é fraco, falta-lhe permissão para usar este comando 😜")
                        .queue();
            }

        }

    }
}
