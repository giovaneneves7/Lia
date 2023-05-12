package com.github.nekoyasha7.lia.avaliacao.avaliador.dao;

//========================{ IMPORTS }========================//
import com.github.nekoyasha7.lia.avaliacao.avaliador.model.Avaliador;
import com.github.nekoyasha7.lia.infrastructure.dao.DAOGenerico;

import java.util.List;
//========================{ FIM IMPORTS }========================//
public class AvaliadorDAOImpl extends AvaliadorDAO{


    /**
     * Lista todos os avaliadores presentes na base de dados.
     * @return Lista com todos os avaliadores.
     */
    @Override
    public List<Avaliador> listarTodos() {
        return DAOGenerico.gerenciadorDeEntidade.createQuery("SELECT a FROM Avaliador a", Avaliador.class).getResultList();
    }
}
