import http, {AxiosResponse} from 'axios';
import {ChatbotUrlResponse} from "../models/chatbot.model";

export interface IChatbotService {
    getChatbotUrl(): Promise<string>;
}

export const chatbotService: IChatbotService = {
    getChatbotUrl: async (): Promise<string> => {
        return http.get(`/chatbot/url`)
            .then((res: AxiosResponse) => (res.data as ChatbotUrlResponse).chatbotUrl);
    }
}