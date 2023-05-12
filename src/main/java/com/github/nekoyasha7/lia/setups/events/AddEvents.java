//========================{ PACKAGE }========================//
package com.github.nekoyasha7.lia.setups.events;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import com.github.nekoyasha7.lia.model.tickets.*;
import com.github.nekoyasha7.lia.slashcommand.adm.CadastrarAvaliador;
import com.github.nekoyasha7.lia.slashcommand.avaliacao.IniciarAvaliacao;
import com.github.nekoyasha7.lia.slashcommand.avaliacao.SenhaAvaliacao;
import com.github.nekoyasha7.lia.slashcommand.general.*;

import com.github.nekoyasha7.lia.main.Main;
import com.github.nekoyasha7.lia.model.slashcommands.publiccommands.PingSlashCommand;
import com.github.nekoyasha7.lia.model.slashcommands.adm.AnunciarNovel;
import com.github.nekoyasha7.lia.model.slashcommands.publiccommands.SaySlashCommand;
import com.github.nekoyasha7.lia.model.slashcommands.publiccommands.ShipSlashCommand;
import com.github.nekoyasha7.lia.slashcommand.adm.SelecionarPresenca;
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

        //--+ Gerenciar entidades +-//
        Main.jda.addEventListener(new CadastrarAvaliador());

        //--+ Comando de configuração +--//
        Main.jda.addEventListener(new SelecionarPresenca());

        //--+ Comandos de usuário +--//
        Main.jda.addEventListener(new PingSlashCommand());
        Main.jda.addEventListener(new ShipSlashCommand());
        Main.jda.addEventListener(new SaySlashCommand());
        Main.jda.addEventListener(new AskSlashCommand());

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
