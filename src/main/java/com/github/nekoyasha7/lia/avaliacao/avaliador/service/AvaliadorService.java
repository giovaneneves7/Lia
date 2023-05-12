package com.github.nekoyasha7.lia.avaliacao.avaliador.service;

import com.github.nekoyasha7.lia.avaliacao.avaliador.dao.AvaliadorDAOImpl;
import com.github.nekoyasha7.lia.avaliacao.avaliador.model.Avaliador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AvaliadorService implements IAvaliadorService{

    //========================{ ATRIBUTO }========================//

    //========================{ CONSTRUTOR }========================//
    public AvaliadorService(){
    }

    //========================{ METÓDOS }========================//
    @Override
    public void salvar(Avaliador avaliador) {


    }

    @Override
    public List<Avaliador> listarTodos() {

        return null;
    }

    @Override
    public List<Avaliador> sortAvaliadores(List<Avaliador> avaliadores)
    {
        Random rand = new Random();
        int indiceRandom;
        List<Avaliador> avaliadoresAleatorios = new ArrayList<>();

        for(int i = 0; i < avaliadores.size(); i++){

            indiceRandom = rand.nextInt(avaliadores.size());
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
