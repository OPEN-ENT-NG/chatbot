import {chatbotService} from "./services";

declare var Webchat: any;

export const chatbot = {

    init: async function () {
        let chatbotUrl = await chatbotService.getChatbotUrl();

        $("<div>")
            .addClass("chatbot-container")
            .appendTo("body");

        $("<div>")
            .attr({id: "webchat", style: "z-index: 999999;"})
            .appendTo(".chatbot-container");

        $("<script>")
            .attr({src: `${chatbotUrl}/backoffice/assets/scripts/embbed-chatbot.min.js`})
            .appendTo(".chatbot-container");

        setTimeout(() => Webchat.init({
            // Mandatory
            botURL: `${chatbotUrl}/chatbot`,
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
