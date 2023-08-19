//========================{ PACKAGE }========================//
package br.com.vulcan.bot.lia.entidades;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
//========================{ FIM IMPORTS }========================//

@Data
public class Novel {

    //========================{ ATRIBUTOS }========================//
    @JsonProperty("nome_projeto")
    private String titulo;
    @JsonProperty("autor_ou_tradutor")
    private User autorOuTradutor;
    private String autorOriginal;
    private String sinopse;
    private String nacionalidade;
    private String[] generos;
    Role cargo;
    @JsonProperty("id_cargo")
    private String idCargo;

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
