package fr.openent.chatbot;

import fr.openent.chatbot.config.ChatbotConfig;
import fr.openent.chatbot.controller.ChatbotController;
import fr.openent.chatbot.controller.MonitoringController;
import fr.openent.chatbot.service.ServiceFactory;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import org.entcore.common.http.BaseServer;
import org.entcore.common.storage.Storage;
import org.entcore.common.storage.StorageFactory;

public class Chatbot extends BaseServer {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		Promise<Void> promise = Promise.promise();
		super.start(promise);
		promise.future()
				.compose(init -> StorageFactory.build(vertx, config))
				.compose(storageFactory -> initChatbot(storageFactory))
				.onComplete(startPromise);
	}

	private Future<Void> initChatbot(StorageFactory storageFactory) {
		ChatbotConfig chatbotConfig = new ChatbotConfig(config);

		Storage storage = storageFactory.getStorage();

		ServiceFactory serviceFactory = new ServiceFactory(vertx, storage, config, chatbotConfig);

		addController(new ChatbotController(serviceFactory));
		addController(new MonitoringController(serviceFactory));
		return Future.succeededFuture();
	}

}
