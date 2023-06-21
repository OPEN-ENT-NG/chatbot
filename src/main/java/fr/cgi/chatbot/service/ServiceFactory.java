package fr.cgi.chatbot.service;

import fr.cgi.chatbot.service.impl.DefaultChatbotService;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import org.entcore.common.storage.Storage;

public class ServiceFactory {
    private final Vertx vertx;
    private final Storage storage;

    public ServiceFactory(Vertx vertx, Storage storage) {
        this.vertx = vertx;
        this.storage = storage;
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
}
