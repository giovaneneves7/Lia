package br.com.vulcan.bot.lia.infrastructure.dao;

import br.com.vulcan.bot.lia.infrastructure.model.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDAO<Entity extends AbstractEntity>{

    //-------------------------------{Atributos}-------------------------------//
    private static EntityManager generenciadorDeEntidade;

    //-------------------------------{ GETTERS E SETTERS }-------------------------------//

    public static EntityManager getGenerenciadorDeEntidade() {
        return generenciadorDeEntidade;
    }

    public static void setGenerenciadorDeEntidade(EntityManager generenciadorDeEntidade) {
        GenericDAO.generenciadorDeEntidade = generenciadorDeEntidade;
    }

    //-------------------------------{ MÉTODOS ESTÁTICOS }-------------------------------//

    static{
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("MY_PU");
        setGenerenciadorDeEntidade(fabrica.createEntityManager());
    }

    //-------------------------------{ MÉTODOS }-------------------------------//

    public boolean adicionar(Entity entidade){

        boolean adicionadoComSucesso = false;

        try{

            getGenerenciadorDeEntidade().getTransaction().begin();
            getGenerenciadorDeEntidade().persist(entidade);
            getGenerenciadorDeEntidade().getTransaction().commit();

            adicionadoComSucesso = true;

        } catch(Exception ex){

            System.out.println("Erro ao tentar persistir no banco de dados!");
            ex.printStackTrace();
            getGenerenciadorDeEntidade().getTransaction().rollback();

        }

        return adicionadoComSucesso;

    }
}
