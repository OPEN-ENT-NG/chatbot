import {chatbotService} from "./services";

interface IViewModel {
    chatbotUrl: string;
}

declare var Webchat: any;

export class Chatbot implements IViewModel {
    chatbotUrl: string;

    constructor() {
    }

    init = async () => {
        this.chatbotUrl = await chatbotService.getChatbotUrl();

        $("<div>")
            .addClass("chatbot-container")
            .appendTo("body");

        $("<div>")
            .attr({id: "webchat", style: "z-index: 999999;"})
            .appendTo(".chatbot-container");

        $("<script>")
            .attr({src: `${this.chatbotUrl}/backoffice/assets/scripts/embbed-chatbot.min.js`})
            .appendTo(".chatbot-container");

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
