package com.github.nekoyasha7.lia.model.entity;

//--+ Imports +--//
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
//--+ FIM Imports +--//

@Entity
@Table(name="moedas")
@Data
public class Coin {

    //-------------------------------{Atributos}-------------------------------//
    private String nome;
    private String tradutor;
    private String obra;
    private long quantidade;

    //-------------------------------{ CONSTRUTOR }-------------------------------//

    /**
     *
     * @param nome da moeda.
     * @param tradutor da novel que possui a moeda.
     * @param obra da moeda.
     * @param quantidade de moedas do usuário.
     */
    public Coin(String nome, String tradutor, String obra, long quantidade){

        this.setNome(nome);
        this.setTradutor(tradutor);
        this.setObra(obra);
        this.setQuantidade(quantidade);

    }
}
