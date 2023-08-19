//========================{ PACKAGE }========================//
package br.com.vulcan.bot.lia.config;
//========================{ FIM PACKAGE }========================//

//========================{ IMPORTS }========================//
import br.com.vulcan.bot.lia.main.Main;
import net.dv8tion.jda.api.interactions.commands.OptionType;
//========================{ FIM PACKAGE }========================//

/**
 * @author NekoYasha
 */
public class RegisterSlashCommand {

    /**
     * Registra SlashCommands da aplicação com seus parâmetro (obrigatórios ou opcionais)
     */
    public static void registerSlashCommand(){


        //--+ Interação com a API +--//
        // -> Atualizar cargos
        Main.jda.upsertCommand("atualizar-cargos", "atualiza os cargos das novels na base de dados")
                        .addOption(OptionType.STRING, "api-url", "a url da api", true)
                        .queue();
        // -> Cadastrar Cargo
        Main.jda.upsertCommand("cadastrar-cargo", "cadastra um novo cargo")
                        .addOption(OptionType.ROLE, "cargo", "o cargo que será cadastrado", true)
                        .queue();

        //--+ Comandos Entidade Avaliador +--//
        Main.jda.upsertCommand("cadastrar-avaliador", "Adiciona um avaliador na abse de dados")
                        .addOption(OptionType.USER, "usuario", "usuario que sera cadastrado")
                        .queue();

        //--+ Comandos de usuário +--//
        Main.jda.upsertCommand("ping", "Comando para testar a conexão com o bot")
                .queue();

        Main.jda.upsertCommand("ship", "ver a compatibilidade entre dois usuários")
                .addOption(OptionType.USER, "usuario1", "menciona um usuário", true)
                .addOption(OptionType.USER, "usuario2", "menciona outro usuário", true)
                .queue();

        Main.jda.upsertCommand("say", "repete a mensagem digitada")
                .addOption(OptionType.STRING, "mensagem", "mensagem a ser repetida", true)
                .queue();

        //--+ EM MANUTENÇÃO +--//
        Main.jda.upsertCommand("ask", "Tem algumas dúvida? Pergunte e eu responderei!")
                .addOption(OptionType.STRING, "prompt", "digite sua pergunta", true)
                .queue();

        //--+ Comandos de avaliação +--//
        Main.jda.upsertCommand("iniciar-avaliacao", "inicia a avaliação de um ticket de autor")
                .queue();

        Main.jda.upsertCommand("senha-avaliacao", "use o comando após ler o documento inicial e preencher o formulário")
                .addOption(OptionType.STRING, "senha", "senha contida no documento inical", true)
                .queue();

        //--+ Comandos de configuração +--//
        Main.jda.upsertCommand("selecionar-presenca", "selenciona a atividade da aplicação")
                .addOption(OptionType.STRING, "atividade", "escolha a atividade", true)
                .addOption(OptionType.STRING, "descricao", "descricao da atividade", true)
                .queue();

        //--+ Comandos de anuncio (sim, tem parâmetros pra caralho) +--//
        Main.jda.upsertCommand("anunciar-novel", "anuncia uma nova novel")
                .addOption(OptionType.ROLE, "cargo", "cargo da novel", true)
                .addOption(OptionType.STRING, "generos", "cargo da novel", true)
                .addOption(OptionType.STRING, "nacionalidade", "nacionalidade da novel", true)
                .addOption(OptionType.STRING, "sinopse", "sinopse da novel", true)
                .addOption(OptionType.USER, "autor-ou-tradutor", "autor ou tradutor da novel", true)
                .addOption(OptionType.STRING, "autor-original", "Autor original da novel", true)
                .addOption(OptionType.BOOLEAN, "everyone", "deseja marcar everyone?", true)
                .addOption(OptionType.STRING, "link", "link da novel", true)
                .addOption(OptionType.STRING, "capa", "link da capa da novel", true)
                .queue();

        //--+ EM MANUTENÇÃO +--//
        Main.jda.upsertCommand("brasileiras", "adicionar categoria")
                .addOption(OptionType.ROLE, "cargo", "cargo da novel", true)
                .addOption(OptionType.STRING, "emoji_id", "id da sigla", true)
                .queue();


        //--+ Comandos de Ticket +--//
        Main.jda.upsertCommand("criar-painel-ticket-avaliacao", "Cria um painel para abrir tickets de avaliacao")
                .addOption(OptionType.STRING, "titulo", "titulo do painel", true)
                .addOption(OptionType.STRING, "descricao", "descrição do painel", true)
                .addOption(OptionType.STRING, "botao", "texto do botão", true)
                .addOption(OptionType.STRING, "tipo", "tipo de avaliacao", true)
                .addOption(OptionType.BOOLEAN, "requisitos", "deseja pedir os requisitos de avaliacao?", true)
                .queue();
        Main.jda.upsertCommand("criar-painel-ticket-autor", "Cria um painel para abrir tickets de autor")
                .addOption(OptionType.STRING, "titulo", "titulo do painel", true)
                .addOption(OptionType.STRING, "descricao", "descrição do painel", true)
                .addOption(OptionType.STRING, "botao", "texto do botão", true)
                .addOption(OptionType.BOOLEAN, "requisitos", "deseja pedir os requisitos de autor?", true)
                .queue();

        Main.jda.upsertCommand("criar-painel-ticket-designer", "Cria um painel para abrir tickets de designer")
                .addOption(OptionType.STRING, "titulo", "titulo do painel", true)
                .addOption(OptionType.STRING, "descricao", "descrição do painel", true)
                .addOption(OptionType.STRING, "botao", "texto do botão", true)
                .queue();

        Main.jda.upsertCommand("criar-painel-ticket-tradutor", "Cria um painel para abrir tickets de tradutor")
                .addOption(OptionType.STRING, "titulo", "titulo do painel", true)
                .addOption(OptionType.STRING, "descricao", "descrição do painel", true)
                .addOption(OptionType.STRING, "botao", "texto do botão", true)
                .addOption(OptionType.BOOLEAN, "requisitos", "deseja pedir os requisitos de autor?", true)
                .queue();

        Main.jda.upsertCommand("criar-painel-ticket-avaliador", "Cria um painel para abrir tickets de avaliador")
                .addOption(OptionType.STRING, "titulo", "titulo do painel", true)
                .addOption(OptionType.STRING, "descricao", "descrição do painel", true)
                .addOption(OptionType.STRING, "botao", "texto do botão", true)
                .queue();
    }
}
