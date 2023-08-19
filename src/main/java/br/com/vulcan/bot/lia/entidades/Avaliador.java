//========================{ PACKAGE }========================//
package br.com.vulcan.bot.lia.entidades;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//

import br.com.vulcan.bot.lia.infrastructure.model.AbstractEntity;
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
