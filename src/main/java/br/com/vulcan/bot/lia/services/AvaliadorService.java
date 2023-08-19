//========================{ PACKAGE }========================//
package br.com.vulcan.bot.lia.services;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import br.com.vulcan.bot.lia.entidades.Avaliador;
import br.com.vulcan.bot.lia.main.Main;
import br.com.vulcan.bot.lia.entidades.VulcanServidorStaff;
import br.com.vulcan.bot.lia.services.IAvaliadorService;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.*;
import java.util.concurrent.*;
//========================{ FIM IMPORTS }========================//

public class AvaliadorService implements IAvaliadorService {

    //========================{ CONSTRUTOR }========================//
    public AvaliadorService()
    {
    }

    //========================{ METÓDOS }========================//
    @Override
    public void salvar(Avaliador avaliador)
    {


    }

    @Override
    public List<Avaliador> listarTodos()
    {

        return null;
    }

    @Override
    public CompletableFuture<List<Member>> sortAvaliadores()
    {

        Random rand = new Random();
        int indiceRandom;

        Guild servidor = Main.jda.getGuildById(VulcanServidorStaff.ID);
        Role cargo = servidor.getRoleById(VulcanServidorStaff.CARGO_AVALIADOR_ID);

        CompletableFuture<List<Member>> avaliadoresFuture = new CompletableFuture<>();

        servidor.loadMembers().onSuccess(membros -> {

            List<Member> avaliadores = new ArrayList<>();

            for(Member membro : membros) {

                for(Role cargoMembro : membro.getRoles()){
                    if(cargoMembro.getId().equals(cargo.getId()) && !membro.getUser().isBot()){

                        avaliadores.add(membro);
                    }

                }
            }
            Collections.shuffle(avaliadores);

            avaliadoresFuture.complete(avaliadores);

        });

        return avaliadoresFuture;


    }

}
