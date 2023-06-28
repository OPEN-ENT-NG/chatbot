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

        var script = document.createElement('script');
        script.src = `${chatbotUrl}/backoffice/assets/scripts/embbed-chatbot.min.js`;

        script.onload = function() {
            Webchat.init({
                // Mandatory
                botURL: `${chatbotUrl}/chatbot`,
                // Optional
                chatWidth: '300px',
                chatHeight: '500px',
                buttonSize: '60px',
                buttonColor: '#e20037',
                iconSize: '30px',
                iconColor: '#ffffff'
            });
        };

        document.querySelector('.chatbot-container').appendChild(script);

    }
}
