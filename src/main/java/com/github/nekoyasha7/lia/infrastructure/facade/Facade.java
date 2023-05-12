package com.github.nekoyasha7.lia.infrastructure.facade;

import com.github.nekoyasha7.lia.avaliacao.avaliador.model.Avaliador;
import com.github.nekoyasha7.lia.avaliacao.avaliador.service.AvaliadorService;

import java.util.List;

public class Facade implements IFacade{

    private final AvaliadorService avaliadorService = new AvaliadorService();


    /**
     * Retorna uma lista de avaliadores em ordem aletória.
     * @return lista de avaliadores aletória.
     */
    @Override
    public List<Avaliador> sortAvaliadores(List<Avaliador> avaliadores)
    {

        return this.avaliadorService.sortAvaliadores(avaliadores);

    }
}
