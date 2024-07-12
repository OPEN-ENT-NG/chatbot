package fr.openent.chatbot;

import fr.openent.chatbot.config.ChatbotConfig;
import fr.openent.chatbot.controller.ChatbotController;
import fr.openent.chatbot.controller.MonitoringController;
import fr.openent.chatbot.service.ServiceFactory;
import io.vertx.core.Promise;
import org.entcore.common.http.BaseServer;
import org.entcore.common.storage.Storage;
import org.entcore.common.storage.StorageFactory;

public class Chatbot extends BaseServer {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		super.start(startPromise);
		ChatbotConfig chatbotConfig = new ChatbotConfig(config);


		Storage storage = new StorageFactory(vertx, config).getStorage();

		ServiceFactory serviceFactory = new ServiceFactory(vertx, storage, config, chatbotConfig);

		addController(new ChatbotController(serviceFactory));
		addController(new MonitoringController(serviceFactory));
		startPromise.tryComplete();
	}

}
