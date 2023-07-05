package fr.openent.chatbot.config;

import fr.openent.chatbot.core.constants.Field;
import io.vertx.core.json.JsonObject;

public class ChatbotConfig {
    private final String chatbotUrl;
    public ChatbotConfig(JsonObject config) {
        this.chatbotUrl = config.getString(Field.CHATBOT_URL, "");
    }
    public String chatbotUrl() {
        return this.chatbotUrl;
    }

}
