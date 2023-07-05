package fr.openent.chatbot.controller;

import fr.openent.chatbot.config.ChatbotConfig;
import fr.openent.chatbot.core.constants.Field;
import fr.openent.chatbot.security.Access;
import fr.openent.chatbot.service.ServiceFactory;
import fr.wseduc.rs.ApiDoc;
import fr.wseduc.rs.Get;
import fr.wseduc.security.ActionType;
import fr.wseduc.security.SecuredAction;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import org.entcore.common.controller.ControllerHelper;
import org.entcore.common.events.EventStore;
import org.entcore.common.events.EventStoreFactory;
import org.entcore.common.http.filter.ResourceFilter;

import static fr.openent.chatbot.core.enums.Events.ACCESS;


public class ChatbotController extends ControllerHelper {

    private final ChatbotConfig chatbotConfig;
    private final EventStore eventStore;


    public ChatbotController(ServiceFactory serviceFactory) {
        this.chatbotConfig = serviceFactory.chatbotConfig();
        this.eventStore = EventStoreFactory.getFactory().getEventStore(ChatbotController.class.getSimpleName());
    }

    @Get("")
    @ApiDoc("Render view")
    @SecuredAction("chatbot.view")
    public void view(HttpServerRequest request) {
        notFound(request);
    }

    @Get("/url")
    @SecuredAction(value = "", type = ActionType.RESOURCE)
    @ResourceFilter(Access.class)
    public void getChatbotUrl(HttpServerRequest request) {
        renderJson(request, new JsonObject().put(Field.CHATBOTURL, chatbotConfig.chatbotUrl()));
        eventStore.createAndStoreEvent(ACCESS.name(), request);
    }
}
