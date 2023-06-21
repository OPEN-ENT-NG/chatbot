package fr.cgi.chatbot.service;

import fr.cgi.chatbot.service.impl.DefaultChatbotService;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.*;
import org.entcore.common.storage.Storage;

public class ServiceFactory {
    private final Vertx vertx;
    private final Storage storage;

    private final JsonObject config;

    public ServiceFactory(Vertx vertx, Storage storage, JsonObject config) {
        this.vertx = vertx;
        this.storage = storage;
        this.config = config;
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
}
