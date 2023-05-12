//========================{ PACKAGE }========================//
package com.github.nekoyasha7.lia.avaliacao.avaliador.dao;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import com.github.nekoyasha7.lia.avaliacao.avaliador.model.Avaliador;
import com.github.nekoyasha7.lia.infrastructure.dao.DAOGenerico;

import java.util.List;
//========================{ FIM IMPORTS }========================//
public abstract class AvaliadorDAO extends DAOGenerico<Avaliador> {

    /**
     * Lista todos os avaliadores presentes na base de dados.
     * @return Lista com todos os avaliadores.
     */
    public abstract List<Avaliador> listarTodos();

}
