package fr.cgi.chatbot.core.enums;

import fr.cgi.chatbot.core.constants.WorkflowRight;

public enum WorkflowActions {
    ACCESS(WorkflowRight.VIEW);

    private final String actionName;

    WorkflowActions(String actionName) {
        this.actionName = actionName;
    }

    public String getAction() {
        return this.actionName;
    }
}
