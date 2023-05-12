//========================{ PACKAGE }========================//
package com.github.nekoyasha7.lia.novel.model;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import lombok.Data;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
//========================{ FIM IMPORTS }========================//

@Data
public class Novel {

    //========================{ ATRIBUTOS }========================//
    private String titulo;
    private User autorOuTradutor;
    private String autorOriginal;
    private String sinopse;
    private String nacionalidade;
    private String[] generos;
    Role cargo;

    //========================{ CONSTRUTOR }========================//

    public Novel(String titulo, User autorOuTradutor, String autorOriginal, String sinopse, String nacionalidade, String generos, Role cargo){

        this.setTitulo(titulo);
        this.setAutorOuTradutor(autorOuTradutor);
        this.setAutorOriginal(autorOriginal);
        this.setSinopse(sinopse);
        this.setNacionalidade(nacionalidade);
        this.setGeneros(generos);
        this.setCargo(cargo);

    }

    //========================{ GETTERS E SETTERS }========================//
    public void setGeneros(String generos) {
        this.generos = generos.split(", ");
    }
}
