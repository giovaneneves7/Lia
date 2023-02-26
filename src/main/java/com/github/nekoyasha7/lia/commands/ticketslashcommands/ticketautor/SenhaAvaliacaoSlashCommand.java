//<<< Package >>>//
package com.github.nekoyasha7.lia.commands.ticketslashcommands.ticketautor;
//<<< End Package >>>//

//<<< Imports >>>//

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.Random;

//<<< End Imports >>>//
/*
/*@author Nekoyasha
 */
//<<< Class >>>//
public class SenhaAvaliacaoSlashCommand extends ListenerAdapter {

    //Variables
    String tofeLink = "https://docs.google.com/document/d/1PGIebJHXwJGONP5f4A5b6EisVEz8XZj4-Wl7OKL3Y6A/edit";

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){

        if(event.getMember().getUser().isBot()) return;

        if(event.getName().equalsIgnoreCase("senha-avaliacao")){
            if(event.getOption("senha").getAsString().equalsIgnoreCase("banana")){
                event.reply("*Palmas, palmas!* Boa! Podemos continuar!\n Este aqui é o TOFE (Treinamento orientado a futuros escravos '-'). "
                                + "Preciso que leia ele para dar início à avaliação. E leia com atenção, viu?")
                        .addActionRow(lerOTofe(), jaLiOTofe())
                        .setEphemeral(false)
                        .queue();
            }else{
                event.reply("Kyaaa! ≧ ﹏ ≦ Seu rude! Eu te dei tanta atenção e você nem leu o documento com atenção!\n" +
                                "A senha está nele! Só iniciarão sua avaliação quando enviá-la, hmpf <( ￣^￣)")
                        .setEphemeral(false)
                        .queue();
            }
        }
    }

    public Button lerOTofe(){
        return Button.link(tofeLink, "Ler o TOFE");
    }

    public Button jaLiOTofe(){
        return Button.primary("completeRead", "Já li o TOFE");
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event){

        //--+ Run if 'TOFE' is read +--//
        if(event.getComponentId().equals("completeRead")){
            event.reply("E aí, depois de ler o TOFE, você acha que precisa de 24 horas para deixar o capítulo adequado aos requisitos mínimos(TOFE) ou podemos prosseguir com a avaliação?")
                    .addActionRow(
                            Button.primary("letsGo", "Podemos prosseguir")
                    )
                    .addActionRow(
                            Button.danger("needTime", "Preciso de tempo para revisar!")
                    )
                    .queue();
        }

        String[] names = {"NekoYasha", "Galo", "Vento Leste", "Glauber", "YEisu"};
        String[] tags = {"NekoYasha#9735", "Bernardo Monteiro#1417", "Vento Leste#7361", "Glauber1907#1325", "YEisu#2508"};
        Avaliador[] avaliador = new Avaliador[names.length];

        for(int i = 0; i < avaliador.length; i++)
            avaliador[i] = new Avaliador();

        avaliadorSetNames(names, avaliador, tags);

        //--+ Run if author is ready +--qq
        if(event.getComponentId().equals("letsGo")){

            event.reply(sortAvaliador(avaliador))
                    .setEphemeral(false)
                    .queue();
        }

        //Run if author need time
        if(event.getComponentId().equals("needTime")){

            event.reply(sortAvaliador(avaliador) + "\n```Hmm... Vejo aqui que você precisa de tempo para revisar. Você tem o prazo de 24 horas para revisar seu capítulo!```")
                    .setEphemeral(false)
                    .queue();

        }
    }

    public void avaliadorSetNames(String[] name, Avaliador[] avaliador, String[] tag){

        for(int i  = 0; i < name.length; i++){
            avaliador[i].setName(name[i]);
            avaliador[i].setTag(tag[i]);
        }
    }

    public String sortAvaliador(Avaliador[] av){

        String message = "Ehhh! Chegamos ao fim! Agora, marca aí um dos ~~escravos~~ Avaliadores online para que ele possa dar início à sua avaliação.\n\n``";
        int index = 0;
        Random rand = new Random();

        for(int i  = 0; i < av.length; i++){

            index = rand.nextInt(av.length);

            //--+ Decrementa caso o avaliador já tenha sido adicionado à String message ou adiciona caso não
            if(message.contains(av[index].getName()))
                i--;
            else{
                String localMessage = (i + 1) + ". " + av[index].getName() + " (" + av[index].getTag() + ")" + "\n";
                message+= localMessage;
            }

        }

        message += "``";
        return message;
    }
}
