package fr.cgi.chatbot.controller;

import fr.cgi.chatbot.Chatbot;
import fr.cgi.chatbot.service.ServiceFactory;
import fr.cgi.chatbot.service.ChatbotService;
import fr.wseduc.rs.ApiDoc;
import fr.wseduc.rs.Get;
import fr.wseduc.security.ActionType;
import fr.wseduc.security.SecuredAction;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import org.entcore.common.controller.ControllerHelper;
import org.entcore.common.events.EventStore;
import org.entcore.common.events.EventStoreFactory;
import org.entcore.common.user.UserUtils;

public class ChatbotController extends ControllerHelper {

    private final EventStore eventStore;
    private final ChatbotService chatbotService;

    private final JsonObject config;

    public ChatbotController(ServiceFactory serviceFactory) {
        this.chatbotService = serviceFactory.chatbotService();
        this.eventStore = EventStoreFactory.getFactory().getEventStore(Chatbot.class.getSimpleName());
        this.config = serviceFactory.config();
    }

    @Get("")
    @ApiDoc("Render view")
    @SecuredAction("chatbot.view")
    public void view(HttpServerRequest request) {
        UserUtils.getUserInfos(eb, request, user -> {
            JsonObject context = new JsonObject().put("myId", user.getUserId());
            renderView(request, context);
        });
    }

    @Get("/config")
    @SecuredAction(value = "", type = ActionType.AUTHENTICATED)
    public void getConfig(HttpServerRequest request) {
        renderJson(request, config);
    }
}
