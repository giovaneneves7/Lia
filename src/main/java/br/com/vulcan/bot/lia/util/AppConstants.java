//========================{ PACKAGE }======================//
package br.com.vulcan.bot.lia.util;
//========================{ FIM PACKAGE }======================//

import br.com.vulcan.bot.lia.commands.slash.AnunciarNovel;
import br.com.vulcan.bot.lia.commands.slash.CadastrarCargo;
import br.com.vulcan.bot.lia.commands.slash.CreateTicketPanel;
import br.com.vulcan.bot.lia.commands.slash.IniciarAvaliacao;
import br.com.vulcan.bot.lia.commands.slash.PingSlashCommand;
import br.com.vulcan.bot.lia.commands.slash.SaySlashCommand;
import br.com.vulcan.bot.lia.commands.slash.SenhaAvaliacao;
import br.com.vulcan.bot.lia.commands.slash.ShipSlashCommand;
import br.com.vulcan.bot.lia.infrastructure.interfaces.SlashCommand;
import java.util.List;


/**
 *
 * @author Giovane Neves
 * @since 1.0.3
 */
public class AppConstants {
    
    // Tokens
    public static final String TOKEN_BOT_PROD = "MTAyNjYxMDMzODg4NTU1NDI3OA.GZlkFK.HLJb73Bgum7U0zkkrXe6Z3TV4WnO0iZNvO4VL4";
    public static final String TOKEN_BOT_TEST = "MTA2MzQ3Njc0ODUwMDU5ODkwNQ.Gt1I2q.6whd_xE0Id2QsbDqxEiA-fV5shobmrRk1qxojo";
    
    // API
    public static final String BASE_URL = "http://apill-vulcan.vps-kinghost.net/";
    public static final String NOVELS_URI = "nekoyasha7/jvulcan-api/v1/novels/novel";
    
    // Discord
    public static final String SERVIDOR_VULCAN_ID = "";
    public static final String SERVIDOR_VULCAN_STAFF_ID = "";
    public static final String CARGO_AVALIADOR_ID = "";
    public static final String CARGO_VICE_LIDER_ID = "";
    public static final String CARGO_AUTOR_ID = "";
    
    // Docs
    public static final String DOC_DOCUMENTO_INICIAL = "";
    public static final String DOC_TOFE = "";
    
    // Comandos
    public static final List<SlashCommand> INSTANCIA_COMANDOS_SLASH = List.of(
            new AnunciarNovel(),
            new CadastrarCargo(),
            new SenhaAvaliacao(),
            new CreateTicketPanel(),
            new IniciarAvaliacao(),
            new ShipSlashCommand(),
            new PingSlashCommand(),
            new SaySlashCommand()
    );
}
