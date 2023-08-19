//========================{ PACKAGE }========================//
package br.com.vulcan.bot.lia.services;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import br.com.vulcan.bot.lia.entidades.Avaliador;
import br.com.vulcan.bot.lia.services.AvaliadorService;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.jetbrains.annotations.NotNull;
//========================{ FIM IMPORTS }========================//

public class CadastrarAvaliador extends ListenerAdapter {

    AvaliadorService avaliadorService;

    public CadastrarAvaliador(){
        avaliadorService = new AvaliadorService();
    }
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent evento) {

        //--+ Retorna se o usuário for bot. +--//
        if(evento.getMember().getUser().isBot()) return;

        if(evento.getName().equals("cadastrar-avaliador")){

            Member quemUsouOComando = evento.getMember();

            if(!quemUsouOComando.hasPermission(Permission.BAN_MEMBERS)){
                evento.reply("Você não tem permissão para usar este comando!")
                        .setEphemeral(true)
                        .queue();

                return;
            }

            User informacoesDoUsuario = evento.getOption("usuario").getAsMember().getUser();
            Avaliador novoAvaliador = new Avaliador();

            novoAvaliador.setNome(informacoesDoUsuario.getName());
            novoAvaliador.setId(Long.parseLong(informacoesDoUsuario.getId()));
            novoAvaliador.setTag(informacoesDoUsuario.getAsTag());

            avaliadorService.salvar(novoAvaliador);
            evento.reply("Novo avaliador cadastrado com sucesso!")
                    .setEphemeral(true)
                    .queue();
        }
    }
}
