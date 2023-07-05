import {chatbot} from "./chatbot";

export const chatbotBehaviours = {
    rights: {
        workflow: {
            access: 'fr.openent.chatbot.controller.ChatbotController|view'
        }
    },
    chatbot: chatbot
};
