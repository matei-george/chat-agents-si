package main;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class ChatApp {

	public static void main(String[] args) {
		try {
			// Start the Jade platform
			jade.core.Runtime rt = jade.core.Runtime.instance();
			Profile profile = new ProfileImpl(null, 12345, null);
			AgentContainer container = rt.createMainContainer(profile);

			// Create and start the GUIAgent
			AgentController guiAgent = container.createNewAgent("GUIAgent", "main.GUIAgent", new Object[0]);
			guiAgent.start();

			// Create and start ChatAgent instances
			AgentController chatAgent1 = container.createNewAgent("ChatAgent1", "main.ChatAgent", new Object[0]);
			chatAgent1.start();

			AgentController chatAgent2 = container.createNewAgent("ChatAgent2", "main.ChatAgent", new Object[0]);
			chatAgent2.start();

		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
	}
}
