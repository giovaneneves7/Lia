//========================{ PACKAGE }========================//
package com.github.nekoyasha7.lia.infrastructure.dao;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import com.github.nekoyasha7.lia.infrastructure.model.AbstractEntity;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Data;

import jakarta.persistence.EntityManager;

import java.util.List;
//========================{ FIM IMPORTS }========================//

@Data
public class DAOGenerico<Entidade extends AbstractEntity> {

    //========================{ ATRIBUTOS }========================//
    public static EntityManager gerenciadorDeEntidade;

    //========================{ STATIC }========================//
    static {
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("vulcan");
        gerenciadorDeEntidade = fabrica.createEntityManager();
    }

    //========================{ METÓDOS GENÉRICOS }========================//

    /**
     * Salva uma entidade no banco de dados.
     * @param entidade A entidade que será guardada no banco de dados.
     */
    public void salvar(Entidade entidade){

        try{

            gerenciadorDeEntidade.getTransaction().begin();
            gerenciadorDeEntidade.persist(entidade);
            gerenciadorDeEntidade.getTransaction().commit();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
