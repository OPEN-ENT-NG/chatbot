package fr.cgi.chatbot.controller;

import fr.cgi.chatbot.Chatbot;
import fr.cgi.chatbot.security.Access;
import fr.cgi.chatbot.service.ServiceFactory;
import fr.cgi.chatbot.service.ChatbotService;
import fr.wseduc.rs.ApiDoc;
import fr.wseduc.rs.Get;
import fr.wseduc.rs.Post;
import fr.wseduc.security.ActionType;
import fr.wseduc.security.SecuredAction;
import fr.wseduc.webutils.request.RequestUtils;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import org.entcore.common.controller.ControllerHelper;
import org.entcore.common.events.EventStore;
import org.entcore.common.events.EventStoreFactory;
import org.entcore.common.http.filter.ResourceFilter;
import org.entcore.common.user.UserUtils;

public class ChatbotController extends ControllerHelper {

    private final EventStore eventStore;
    private final ChatbotService chatbotService;

    public ChatbotController(ServiceFactory serviceFactory) {
        this.chatbotService = serviceFactory.chatbotService();
        this.eventStore = EventStoreFactory.getFactory().getEventStore(Chatbot.class.getSimpleName());
    }

    @Get("")
    @ApiDoc("Render view")
    @SecuredAction("view")
    public void view(HttpServerRequest request) {
        UserUtils.getUserInfos(eb, request, user -> {
            JsonObject context = new JsonObject().put("myId", user.getUserId());
            renderView(request, context);
        });
        eventStore.createAndStoreEvent("ACCESS", request);
    }

    @Post("/test")
    @ApiDoc("Simulation post data")
    @SecuredAction(value = "", type = ActionType.RESOURCE)
    @ResourceFilter(Access.class)
    public void simulationPostData(HttpServerRequest request) {
        RequestUtils.bodyToJson(request, bodyJson ->
                UserUtils.getUserInfos(eb, request, user -> {
                    JsonObject context = new JsonObject()
                            .put("myId", user.getUserId())
                            .put("content", bodyJson);
                    renderJson(request, context);
                }));
    }

    @Get("/test")
    public void getDataTest(HttpServerRequest request) {
        renderJson(request, new JsonObject().put("result", new JsonObject()));
    }

    @Post("/test/data")
    public void postDataTest(HttpServerRequest request) {
        RequestUtils.bodyToJson(request, bodyJson -> {
            // data manipulation
            JsonObject result = new JsonObject().put("data", bodyJson);
            renderJson(request, new JsonObject().put("result", result));
        });
    }
}
