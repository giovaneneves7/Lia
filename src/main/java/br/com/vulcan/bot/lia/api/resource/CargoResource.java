package br.com.vulcan.bot.lia.api.resource;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CargoResource
{

    @JsonProperty("nome_cargo")
    private String nome;

    @JsonProperty("id_cargo")
    private String id;
}
