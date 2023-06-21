interface IViewModel {
    that: any;
    $eval: any;
    init(): void;
}
declare var Webchat: any;

const vm: IViewModel = {
    that: null,
    $eval: null,
    init: () => {
        setTimeout(() => Webchat.init({
            // Mandatory
            botURL: 'https://chatbot.lyceeconnecte.fr/chatbot',
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
            this.vm = vm;
            vm.$eval = this.$eval;
            vm.init();
        }
    }
};
