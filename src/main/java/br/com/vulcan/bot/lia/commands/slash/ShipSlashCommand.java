//<<< Package >>>//
package br.com.vulcan.bot.lia.commands.slash;
//<<< End Package >>>//


//<<< Imports >>>//
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;
//<<< End Imports >>>//
/**
/*@author Nekoyasha
 */

public class ShipSlashCommand extends ListenerAdapter{

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){

        //--+ Verifica se o usuário é bot +--//
        if(event.getMember().getUser().isBot()) return;

        int randomNumber = getRandomNumber();

        if(event.getName().equalsIgnoreCase("ship")){

            if(randomNumber < 10)
                replyMessage(event, "Hmmm, não vão dar certo, não", randomNumber);
            else if(randomNumber < 30)
                replyMessage(event, "Acho que precisam se conhecer um pouco mais", randomNumber);
            else if(randomNumber < 50)
                replyMessage(event, "Pode dar certo se um deles der um passo à frente", randomNumber);
            else if(randomNumber < 70)
                replyMessage(event, "O amor está no ar!", randomNumber);
            else if(randomNumber < 99)
                replyMessage(event, "Wow, espero que me convidem para o casamento!", randomNumber);
            else
                replyMessage(event, "Acho que temos um casal aqui!", randomNumber);

        }
    }

    public void replyMessage(SlashCommandInteractionEvent event, String text, int percent){
        event.reply("O usuário **" + event.getOption("usuario1").getAsMember().getUser().getName() +
                "** Tem " + percent + "% de compatibilidade com **" +
                event.getOption("usuario2").getAsMember().getUser().getName() + "** \n" +
                text
        ).setEphemeral(false).queue();
    }

    /**
     *
     * @return um númeor aleatório entre 0 e 100.
     */
    public int getRandomNumber(){

        Random rand = new Random();

        return rand.nextInt(100);
    }
}
