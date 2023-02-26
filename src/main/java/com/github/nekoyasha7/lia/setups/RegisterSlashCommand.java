//<<< Package >>>//
package com.github.nekoyasha7.lia.setups;
//<<< End Package >>>//

//<<< Imports >>>//
import com.github.nekoyasha7.lia.main.Main;
import net.dv8tion.jda.api.interactions.commands.OptionType;
//<<< End Imports >>>//
/*
/*@author Nekoyasha
 */
//<<< Class >>>//
public class RegisterSlashCommand {

    //--+Registra os Slash Commands e seus parâmetros obrigatórios e opcionais--+//
    public static void registerSlashCommand(){
        //Public commands
        Main.jda.upsertCommand("ping", "Comando para testar a conexão com o bot")
                .queue();

        Main.jda.upsertCommand("ship", "ver a compatibilidade entre dois usuários")
                .addOption(OptionType.USER, "usuario1", "menciona um usuário", true)
                .addOption(OptionType.USER, "usuario2", "menciona outro usuário", true)
                .queue();

        Main.jda.upsertCommand("say", "repete a mensagem digitada")
                .addOption(OptionType.STRING, "mensagem", "mensagem a ser repetida", true)
                .queue();

        Main.jda.upsertCommand("ask", "Tem algumas dúvida? Pergunte e eu responderei!")
                .addOption(OptionType.STRING, "prompt", "digite sua pergunta", true)
                .queue();

        //'Avaliação Autor' commands
        Main.jda.upsertCommand("iniciar-avaliacao", "inicia a avaliação de um ticket de autor")
                .queue();

        Main.jda.upsertCommand("senha-avaliacao", "use o comando após ler o documento inicial e preencher o formulário")
                .addOption(OptionType.STRING, "senha", "senha contida no documento inical", true)
                .queue();

        //'Setup' commands
        Main.jda.upsertCommand("selecionar-presenca", "selenciona a atividade do bot")
                .addOption(OptionType.STRING, "atividade", "escolha a atividade", true)
                .addOption(OptionType.STRING, "descricao", "descricao da atividade", true)
                .queue();

        //'Anuncios' commands
        Main.jda.upsertCommand("anunciar-novel", "anuncia uma nova novel")
                .addOption(OptionType.ROLE, "cargo", "cargo da novel", true)
                .addOption(OptionType.STRING, "generos", "cargo da novel", true)
                .addOption(OptionType.STRING, "nacionalidade", "nacionalidade da novel", true)
                .addOption(OptionType.STRING, "sinopse", "sinopse da novel", true)
                .addOption(OptionType.USER, "autor", "autor da novel", true)
                .addOption(OptionType.BOOLEAN, "everyone", "deseja marcar everyone?", true)
                .addOption(OptionType.STRING, "link", "link da novel", true)
                .addOption(OptionType.STRING, "capa", "link da capa da novel", true)
                .queue();

        Main.jda.upsertCommand("brasileiras", "adicionar categoria")
                .addOption(OptionType.ROLE, "cargo", "cargo da novel", true)
                .addOption(OptionType.STRING, "emoji_id", "id da sigla", true)
                .queue();


        //'Tickets' commands
        Main.jda.upsertCommand("criar-painel-ticket-autor", "Cria um painel para abrir tickets de autor")
                .addOption(OptionType.STRING, "titulo", "titulo do painel", true)
                .addOption(OptionType.STRING, "descricao", "descrição do painel", true)
                .addOption(OptionType.STRING, "botao", "texto do botão", true)
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
                .queue();

        Main.jda.upsertCommand("criar-painel-ticket-avaliador", "Cria um painel para abrir tickets de avaliador")
                .addOption(OptionType.STRING, "titulo", "titulo do painel", true)
                .addOption(OptionType.STRING, "descricao", "descrição do painel", true)
                .addOption(OptionType.STRING, "botao", "texto do botão", true)
                .queue();
    }
}
