package br.com.vulcan.bot.lia.singleton;

import br.com.vulcan.bot.lia.util.AppConstants;
import java.util.EnumSet;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

/**
 *
 * @author Giovane Neves
 * @since V1.0.3
 */
public class JdaSingleton {
    
    private static JDA instance;
    
    private JdaSingleton(){};
    
    public static synchronized JDA getInstance(){
        
        if(instance == null){
            
            instance = JDABuilder.createDefault(AppConstants.TOKEN_BOT_TEST)
                .enableIntents((EnumSet.allOf(GatewayIntent.class)))
                .build();
        }
    
        return instance;
        
    } 
}
