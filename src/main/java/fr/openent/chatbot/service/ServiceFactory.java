package fr.openent.chatbot.service;

import fr.openent.chatbot.config.ChatbotConfig;
import fr.openent.chatbot.service.impl.DefaultChatbotService;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import org.entcore.common.storage.Storage;

public class ServiceFactory {
    private final Vertx vertx;
    private final Storage storage;

    private final JsonObject config;
    private final ChatbotConfig chatbotConfig;

    public ServiceFactory(Vertx vertx, Storage storage, JsonObject config, ChatbotConfig chatbotConfig) {
        this.vertx = vertx;
        this.storage = storage;
        this.config = config;
        this.chatbotConfig = chatbotConfig;
    }

    public ChatbotService chatbotService() {
        return new DefaultChatbotService();
    }
    public EventBus eventBus() {
        return this.vertx.eventBus();
    }

    public Vertx vertx() {
        return this.vertx;
    }

    public JsonObject config() {
        return this.config;
    }
    public ChatbotConfig chatbotConfig() {

        return this.chatbotConfig;
    }
}
