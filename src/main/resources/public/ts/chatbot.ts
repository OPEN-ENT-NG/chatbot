import {chatbotService} from "./services";

declare var Webchat: any;

export const chatbot = {

    init: async function () {
        try {
            let chatbotUrl: string = await chatbotService.getChatbotUrl();

            $("<div>")
                .addClass("chatbot-container")
                .appendTo("body");

            $("<div>")
                .attr({id: "webchat", style: "z-index: 999999;"})
                .appendTo(".chatbot-container");

            let script: HTMLScriptElement = document.createElement('script');
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
                $("#iframe-bot").css("background", "#fff");
            };

            document.querySelector('.chatbot-container').appendChild(script);
        } catch (e) {
            console.error("Error has occured during attempting to fetch chatbot url ", e);
            throw e;
        }
    }
}
