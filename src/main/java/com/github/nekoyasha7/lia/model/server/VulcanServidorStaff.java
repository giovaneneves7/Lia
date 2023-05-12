package com.github.nekoyasha7.lia.model.server;

//========================{ IMPORTS }========================//
import lombok.Data;
//========================{ FIM IMPORTS }========================//

@Data
public class VulcanServidorStaff {

    //========================{ ATRIBUTOS }========================//
    private String idCargoAvaliador;
    private String idCargoVice;

    //========================{ CONSTRUTOR }========================//
    public VulcanServidorStaff(){

        this.setIdCargoAvaliador("879079664306499585");
        this.setIdCargoVice("901941961869639691");
    }
}
