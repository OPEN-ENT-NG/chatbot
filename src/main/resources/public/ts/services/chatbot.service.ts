import {ng} from 'entcore'
import http, {AxiosResponse} from 'axios';

export interface IChatbotService {
    getChatbotUrl(): Promise<string>;
}

export const chatbotService: IChatbotService = {
    getChatbotUrl: async (): Promise<string> => {
        return http.get(`/chatbot/config`)
            .then((res: AxiosResponse) => res.data["chatbot-url"]);
    }
}
