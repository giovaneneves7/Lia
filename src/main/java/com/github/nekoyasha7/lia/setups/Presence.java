//<<< Package >>>//
package com.github.nekoyasha7.lia.setups;
//<<< End Package >>>//

//<<< Imports >>>//
import com.github.nekoyasha7.lia.main.Main;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
//<<< End Imports >>>//
/*
/*@author Nekoyasha
 */
//<<< Class >>>//
public class Presence extends ListenerAdapter {
    public static boolean setPresence(String presence, String text){

        switch(presence){
            case "WATCHING":
                Main.jda.getPresence().setPresence(
                        OnlineStatus.ONLINE, Activity.of(Activity.ActivityType.WATCHING, ("") + text)
                );
                return true;
            case "PLAYING":
                Main.jda.getPresence().setPresence(
                        OnlineStatus.ONLINE, Activity.of(Activity.ActivityType.PLAYING, ("") + text)
                );
                return true;
            case "LISTENING":
                Main.jda.getPresence().setPresence(
                        OnlineStatus.ONLINE, Activity.of(Activity.ActivityType.LISTENING, ("") + text)
                );
                return true;

            case "STREAMING":
                Main.jda.getPresence().setPresence(
                        OnlineStatus.ONLINE, Activity.of(Activity.ActivityType.STREAMING, ("" + text), "https://discord.gg/vulcan-3k-410158562929803275")
                );
                return true;
        }

        return false;

    }
}
