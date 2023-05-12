package com.github.nekoyasha7.lia.infrastructure.facade;

import com.github.nekoyasha7.lia.avaliacao.avaliador.model.Avaliador;
import java.util.List;

public interface IFacade {

    /**
     * Retorna uma lista de avaliadores em ordem aletória.
     * @return lista de avaliadores aletória.
     */
    List<Avaliador> sortAvaliadores(List<Avaliador> avaliadores);

}
