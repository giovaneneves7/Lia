//========================{ PACKAGE }========================//
package com.github.nekoyasha7.lia.setups.interacao;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import lombok.Data;

import java.util.List;
import java.util.Random;
//========================{ FIM IMPORTS }========================//

@Data
public class Saudacao {

    //========================{ ATRIBUTOS }========================//
    private List<String> listaDeOla;
    private List<String> listaSenhaErrada;

    //========================{ CONSTRUTOR }========================//
    public Saudacao(){

        listaDeOla = List.of("Muchi muchi?", "Hello!", "Ohayoo!", "Opa, opa!", "Olá!");
        listaSenhaErrada = List.of(
                "Kyaaa! ≧ ﹏ ≦ Seu rude! Eu te dei tanta atenção e você nem leu o documento com atenção!\n"
                        .concat("A senha está nele! Só vamos começar sua avaliação quando enviá-la, hmpf <( ￣^￣)"),

                "Você é uma decepção ┳━┳ ノ( ゜-゜ノ)\n".concat("https://media.tenor.com/aN5ls1OnheYAAAAC/maid.gif")
        );
    }

    //========================{ METÓDOS }========================//
    public String pegarOlaAleatorio(){

        Random aleatorializador = new Random();
        int indiceAleatorio = aleatorializador.nextInt(listaDeOla.size());

        return this.getListaDeOla().get(indiceAleatorio);
    }

    public String pegarDesgostoAleatorio(){

        Random aleatorializador = new Random();
        int indiceAleatorio = aleatorializador.nextInt(listaSenhaErrada.size());

        return this.getListaSenhaErrada().get(indiceAleatorio);
    }
}
