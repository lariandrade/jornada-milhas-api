package com.challenge.jornadamilhasapi.services;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    public String gerarTexto(String nomeDestino){

        OpenAiService service = new OpenAiService(openaiApiKey);

        CompletionRequest requisicao = CompletionRequest.builder()
                .prompt("Faça um resumo sobre " + nomeDestino + " enfatizando o porque este lugar é incrível. " +
                        "Utilize uma linguagem informal e até 100 caracteres no máximo em cada parágrafo. " +
                        "Crie 2 parágrafos neste resumo.")
                .model("text-davinci-003")
                .maxTokens(500)
                .build();

        String resposta = service.createCompletion(requisicao).getChoices().get(0).getText();

        return resposta.replace("\n", "");

    }
}
