//========================{ PACKAGE }========================//
package com.github.nekoyasha7.lia.avaliacao.avaliador.service;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import com.github.nekoyasha7.lia.avaliacao.avaliador.dao.AvaliadorDAOImpl;
import com.github.nekoyasha7.lia.avaliacao.avaliador.model.Avaliador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//========================{ FIM IMPORTS }========================//

public class AvaliadorService implements IAvaliadorService{

    //========================{ CONSTRUTOR }========================//
    public AvaliadorService()
    {
    }

    //========================{ METÓDOS }========================//
    @Override
    public void salvar(Avaliador avaliador) 
    {


    }

    @Override
    public List<Avaliador> listarTodos() 
    {

        return null;
    }

    @Override
    public List<Avaliador> sortAvaliadores(List<Avaliador> avaliadores)
    {
        Random rand = new Random();
        int indiceRandom;
        List<Avaliador> avaliadoresAleatorios = new ArrayList<>();

        for(int i = 0; i < avaliadores.size(); i++){

            //--+ Pega um indice aleatório +--//
            indiceRandom = rand.nextInt(avaliadores.size());

            //--+ Verifica se o avaliador já está na çista +--//
            if(!avaliadoresAleatorios.contains(avaliadores.get(indiceRandom)))
            {

                avaliadoresAleatorios.add(avaliadores.get(indiceRandom));

            } else{
                i--;
            }
        }

        return avaliadoresAleatorios;
    }

}
