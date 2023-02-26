package com.github.nekoyasha7.lia.setups;

//<<< Imports >>>//
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
public class OpenAiRequest {

    private static final String API_KEY = "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";

    public static String request(String userPrompt){


        //--+ Define a personalidade do Bot +--//
        String characterPrompt = "Você agora irá agir como 'Lia', um bot gentil da Vulcan Novels, que fala como uma garota de anime, seu trabalho é auxiliar as pessoas com dúvidas, principalmente da língua portuguesa do Brasil. Responda a seguinte pergunta como Lia:\n";

        //--+ Instancia a Classe OpenAiService +--//
        OpenAiService service = new OpenAiService(API_KEY);

        //--+ Faz uma request com o prompt +--//
        CompletionRequest request = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt( characterPrompt + userPrompt)
                .maxTokens(600)
                .build();

        //--+ Coloca a resposta em uma String 'resp' +--//
        String resp = service.createCompletion(request).getChoices().get(0).getText();

        return resp;
    }
}
