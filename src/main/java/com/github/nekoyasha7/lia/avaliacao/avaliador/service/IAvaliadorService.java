//========================{ PACKAGE }========================//
package com.github.nekoyasha7.lia.avaliacao.avaliador.service;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import com.github.nekoyasha7.lia.avaliacao.avaliador.model.Avaliador;

import java.util.List;
//========================{ FIM IMPORTS }========================//
public interface IAvaliadorService {

    /**
     * Salva uma entidade no banco de dados.
     * @param avaliador A entidade que será guardada no banco de dados.
     */
    void salvar(Avaliador avaliador);

    /**
     * Lista todos os avaliadores presentes na base de dados.
     * @return Lista com todos os avaliadores.
     */
    List<Avaliador> listarTodos();

    List<Avaliador> sortAvaliadores(List<Avaliador> avaliadores);
}
