import {chatbot} from "./chatbot";

export const chatbotBehaviours = {
    rights: {
        workflow: {
            access: 'fr.cgi.chatbot.controller.ChatbotController|view'
        }
    },
    chatbot: chatbot
};
