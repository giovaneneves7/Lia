package br.com.vulcan.bot.lia.infrastructure.facade;

import net.dv8tion.jda.api.entities.Member;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IFacade {

    /**
     * Retorna uma lista de avaliadores em ordem aletória.
     * @return lista de avaliadores aletória.
     */
    CompletableFuture<List<Member>> sortAvaliadores();

}
