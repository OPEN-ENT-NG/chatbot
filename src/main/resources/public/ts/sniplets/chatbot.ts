import {chatbotService} from "../services";

interface IViewModel {
    chatbotUrl: string;
}

declare var Webchat: any;

class ViewModel implements IViewModel {
    chatbotUrl: string;

    constructor() {
        this.initChatbot();
    }

    initChatbot = async () => {
        this.chatbotUrl = await chatbotService.getChatbotUrl();

        $("<script>")
            .attr({src: `${this.chatbotUrl}/backoffice/assets/scripts/embbed-chatbot.min.js`})
            .appendTo(".sniplet-chatbot");

        setTimeout(() => Webchat.init({
            // Mandatory
            botURL: `${this.chatbotUrl}/chatbot`,
            // Optional
            chatWidth: '300px',
            chatHeight: '500px',
            buttonSize: '60px',
            buttonColor: '#6e91f0',
            iconSize: '30px',
            iconColor: '#ffffff'
        }), 1000);
    }
}

export const chatbotSniplet = {
    title: 'chatbot.title',
    public: false,
    controller: {
        init: async function (): Promise<void> {
            this.vm = new ViewModel();
        }
    }
};
