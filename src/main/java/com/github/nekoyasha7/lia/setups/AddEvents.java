//<<< Package >>>//
package com.github.nekoyasha7.lia.setups;
//<<< End Package >>>//

//<<< Imports >>>//
import com.github.nekoyasha7.lia.commands.publicslashcommands.*;
import com.github.nekoyasha7.lia.commands.ticketslashcommands.*;
import com.github.nekoyasha7.lia.commands.ticketslashcommands.ticketautor.*;
import com.github.nekoyasha7.lia.commands.admslashcommands.*;

import com.github.nekoyasha7.lia.main.Main;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
//<<< End Imports >>>//
/*
/*@author Nekoyasha
 */
//<<< Class >>>//
public class AddEvents extends ListenerAdapter {

    public static void addEvents(){
        //'Setup' Commands
        Main.jda.addEventListener(new SelecionarPresenca());

        //Public Commands
        Main.jda.addEventListener(new PingSlashCommand());
        Main.jda.addEventListener(new ShipSlashCommand());
        Main.jda.addEventListener(new SaySlashCommand());

        //'Avaliação' Commands
        Main.jda.addEventListener(new SenhaAvaliacaoSlashCommand());
        Main.jda.addEventListener(new IniciarAvaliacaoSlashCommand());

        //'Tickets' Commands
        Main.jda.addEventListener(new TicketAutor());
        Main.jda.addEventListener(new TicketDesigner());
        Main.jda.addEventListener(new TicketTradutor());
        Main.jda.addEventListener(new TicketAvaliador());

        //'Administration' Commands
        Main.jda.addEventListener(new AnunciarNovel());
        //jda.addEventListener(new BrasileirasSlashCommand());
    }
}
