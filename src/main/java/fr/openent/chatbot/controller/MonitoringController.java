package fr.openent.chatbot.controller;

import fr.openent.chatbot.service.ServiceFactory;
import fr.wseduc.rs.Get;
import fr.wseduc.security.ActionType;
import fr.wseduc.security.SecuredAction;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import org.entcore.common.controller.ControllerHelper;
import org.entcore.common.http.filter.AdminFilter;
import org.entcore.common.http.filter.ResourceFilter;

public class MonitoringController extends ControllerHelper {
    
    private final JsonObject config;

    public MonitoringController(ServiceFactory serviceFactory) {
        this.config = serviceFactory.config();
    }

    @Get("/config")
    @SecuredAction(value = "", type = ActionType.RESOURCE)
    @ResourceFilter(AdminFilter.class)
    public void getConfig(HttpServerRequest request) {
        renderJson(request, config);
    }
}
