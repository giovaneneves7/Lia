package br.com.vulcan.bot.lia.infrastructure.facade;

import br.com.vulcan.bot.lia.services.AvaliadorService;
import net.dv8tion.jda.api.entities.Member;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Facade implements IFacade{

    private final AvaliadorService avaliadorService = new AvaliadorService();


    /**
     * Retorna uma lista de avaliadores em ordem aletória.
     * @return lista de avaliadores aletória.
     */
    @Override
    public CompletableFuture<List<Member>> sortAvaliadores()
    {

        return this.avaliadorService.sortAvaliadores();

    }
}
