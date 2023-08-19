//========================{ PACKAGE }========================//
package br.com.vulcan.bot.lia.services;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import br.com.vulcan.bot.lia.entidades.Avaliador;
import net.dv8tion.jda.api.entities.Member;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    CompletableFuture<List<Member>> sortAvaliadores();
}
