package com.github.nekoyasha7.lia.model.entity;

//--+ Imports +--//
import com.github.nekoyasha7.lia.infrastructure.model.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
//--+ FIM Imports +--//

@Entity
@Table(name = "usuarios")
@Data
public class User extends AbstractEntity {

    private String nome;
    private String tag;
    private Coin[] moedas;


}
