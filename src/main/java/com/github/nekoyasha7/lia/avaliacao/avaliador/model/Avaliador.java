//========================{ PACKAGE }========================//
package com.github.nekoyasha7.lia.avaliacao.avaliador.model;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import com.github.nekoyasha7.lia.infrastructure.model.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//========================{ FIM IMPORTS }========================//

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avaliador extends AbstractEntity
{

    //========================{ ATRIBUTOS }========================//
    private String nome;
    private String tag;
    //========================{ MÉTODOS }========================//

}
