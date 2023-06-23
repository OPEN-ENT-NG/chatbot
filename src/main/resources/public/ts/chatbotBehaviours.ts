import {Chatbot} from "./chatbot";

export const chatbotBehaviours = {
    rights: {
        workflow: {
            access: 'fr.cgi.chatbot.controller.ChatbotController|view'
        }
    },
    chatbot: new Chatbot()
};
