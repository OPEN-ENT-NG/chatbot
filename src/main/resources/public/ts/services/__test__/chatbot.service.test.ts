import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import {chatbotService} from "../chatbot.service";

describe('ChatbotService', () => {

    it('test fetching chatbot config url via axios', done => {
        const mock = new MockAdapter(axios);
        let spy = jest.spyOn(axios, "get");

        const data = {chatbotUrl: "/chatbot/url/ok"};

        mock.onGet(`/chatbot/url`).reply(200, data);

        chatbotService.getChatbotUrl().then((e) => {
            expect(spy).toHaveBeenCalledWith(`/chatbot/url`);
            expect(data.chatbotUrl).toEqual(e);
            done();
        });
    });

});
