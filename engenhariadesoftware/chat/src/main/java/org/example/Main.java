package org.example;

import io.github.ollama4j.Ollama;
import io.github.ollama4j.exceptions.OllamaException;
import io.github.ollama4j.models.chat.OllamaChatMessageRole;
import io.github.ollama4j.models.chat.OllamaChatRequest;
import io.github.ollama4j.models.chat.OllamaChatResult;

public class Main {
    public static void main(String[] args) throws OllamaException {
        Ollama ollama = new Ollama("http://localhost:11434/");
        String model = "qwen2.5-coder:7b";

        ollama.setRequestTimeoutSeconds(100);
        OllamaChatRequest builder = OllamaChatRequest.builder().withModel(model);

        //create first user question
        OllamaChatRequest requestModel = builder.withMessage(OllamaChatMessageRole.USER, "What is the capital of France?").build();

        //start conversation with model
        OllamaChatResult chatResult = ollama.chat(requestModel, null);

        System.out.println("First answer: " + chatResult.getResponseModel().getMessage().getResponse());
    }
}
