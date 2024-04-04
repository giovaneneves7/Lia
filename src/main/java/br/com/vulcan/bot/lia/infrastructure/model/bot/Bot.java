package br.com.vulcan.bot.lia.infrastructure.model.bot;

import br.com.vulcan.bot.lia.feature.presenca.domain.model.Presenca;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

/**
 *
 * @author Giovane Neves
 * @since V1.0.3
 */
@Data
@Getter
@Setter
public class Bot {
    
    private JDA instancia;
    private OnlineStatus status;
    private Activity atividade;

    public JDA getInstancia() {
        return instancia;
    }

    public void setInstancia(JDA instancia) {
        this.instancia = instancia;
    }

    public OnlineStatus getStatus() {
        return status;
    }

    public void setStatus(OnlineStatus status) {
        this.status = status;
    }

    public Activity getAtividade() {
        return atividade;
    }

    public void setAtividade(Activity atividade) {
        this.atividade = atividade;
    }
    
    
}
