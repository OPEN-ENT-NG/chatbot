package fr.cgi.chatbot;

import fr.cgi.chatbot.controller.ChatbotController;
import fr.cgi.chatbot.service.ServiceFactory;
import org.entcore.common.http.BaseServer;
import org.entcore.common.storage.Storage;
import org.entcore.common.storage.StorageFactory;

public class Chatbot extends BaseServer {

	@Override
	public void start() throws Exception {
		super.start();

		Storage storage = new StorageFactory(vertx, config).getStorage();

		ServiceFactory serviceFactory = new ServiceFactory(vertx, storage);

		addController(new ChatbotController(serviceFactory));
	}

}
