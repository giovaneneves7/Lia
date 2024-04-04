package br.com.vulcan.bot.lia.infrastructure.builder;

import br.com.vulcan.bot.lia.infrastructure.model.bot.Bot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

/**
 *
 * @author Giovane Neves
 * @since V1.0.3
 */
public class BotBuilder {
    
    private JDA instancia;
    private OnlineStatus status;
    private Activity atividade;
    
    public BotBuilder setInstancia(JDA instancia){
        
        this.instancia = instancia;
        return this;
        
    }
    
    public BotBuilder setStatus(OnlineStatus status){
        
        this.status = status;
        return this;
        
    }
    
    public BotBuilder setAtividade(Activity atividade){
        
        this.atividade = atividade;
        return this;
        
    }
    
    public Bot build(){
        
        Bot bot = new Bot();
        bot.setInstancia(instancia);
        bot.setStatus(status);
        bot.setAtividade(atividade);

        return bot;
        
    }
    
    
}
