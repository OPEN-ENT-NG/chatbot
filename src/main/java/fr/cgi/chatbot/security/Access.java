package fr.cgi.chatbot.security;


import fr.cgi.chatbot.core.enums.WorkflowActions;
import fr.cgi.chatbot.helper.WorkflowHelper;
import fr.wseduc.webutils.http.Binding;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import org.entcore.common.http.filter.ResourcesProvider;
import org.entcore.common.user.UserInfos;

public class Access implements ResourcesProvider {
    @Override
    public void authorize(HttpServerRequest httpServerRequest, Binding binding, UserInfos userInfos, Handler<Boolean> handler) {
        handler.handle(WorkflowHelper.hasRight(userInfos, WorkflowActions.ACCESS.getAction()));
    }
}
