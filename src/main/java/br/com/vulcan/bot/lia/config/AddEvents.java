//========================{ PACKAGE }========================//
package br.com.vulcan.bot.lia.config;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import br.com.vulcan.bot.lia.commands.slash.*;
import br.com.vulcan.bot.lia.entidades.tickets.TicketAutor;
import br.com.vulcan.bot.lia.entidades.tickets.TicketAvaliador;
import br.com.vulcan.bot.lia.entidades.tickets.TicketDesigner;
import br.com.vulcan.bot.lia.entidades.tickets.TicketTradutor;
import br.com.vulcan.bot.lia.api.AtualizarCargosCommand;
import br.com.vulcan.bot.lia.services.CadastrarAvaliador;

import br.com.vulcan.bot.lia.main.Main;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
//========================{ FIM IMPORTS }========================//

/**
 * @author NekoYasha
 */

public class AddEvents extends ListenerAdapter {

    /**
     * Adiciona novos listeners
     */
    public static void addEvents(){

        //--+ Interação com a API +--//
        // -> Atualizar cargos
        Main.jda.addEventListener(new AtualizarCargosCommand());
        // -> Cadastrar Cargo
        Main.jda.addEventListener(new CadastrarCargo());

        //--+ Gerenciar entidades +--//
        Main.jda.addEventListener(new CadastrarAvaliador());

        //--+ Comando de configuração +--//
        Main.jda.addEventListener(new SelecionarPresenca());

        //--+ Comandos de usuário +--//
        Main.jda.addEventListener(new PingSlashCommand());
        Main.jda.addEventListener(new ShipSlashCommand());
        Main.jda.addEventListener(new SaySlashCommand());

        //--+ Comandos de avaliação +--//
       Main.jda.addEventListener(new SenhaAvaliacao());
       Main.jda.addEventListener(new IniciarAvaliacao());

        //--+ Comandos de tickets +--//
        Main.jda.addEventListener(new CreateTicketPanel());
        Main.jda.addEventListener(new TicketAutor());
        Main.jda.addEventListener(new TicketDesigner());
        Main.jda.addEventListener(new TicketTradutor());
        Main.jda.addEventListener(new TicketAvaliador());

        //--+ Comandos de anuncios +--//
        Main.jda.addEventListener(new AnunciarNovel());

    }
}
