package main;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class ChatAgent2 extends jade.core.Agent {

	private static final long serialVersionUID = 8392585388451169413L;
	private List<String> recipients;

	@Override
	protected void setup() {
		recipients = new ArrayList<>();
		registerService();
		addBehaviour(new ReceiveMessagesBehaviour());
	}

	private void registerService() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());

		ServiceDescription sd = new ServiceDescription();
		sd.setType("chat");
		sd.setName("ChatService");
		dfd.addServices(sd);

		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}

	private class ReceiveMessagesBehaviour extends CyclicBehaviour {
		private static final long serialVersionUID = 6954637758712505417L;

		@Override
		public void action() {
			ACLMessage msg = receive();

			if (msg != null) {
				String sender = msg.getSender().getLocalName();
				String content = msg.getContent();

				// Send the message to all other ChatAgents
				for (String recipient : recipients) {
					if (!recipient.equals(sender)) {
						ACLMessage peerMessage = new ACLMessage(ACLMessage.INFORM);
						peerMessage.addReceiver(new AID(recipient, AID.ISLOCALNAME));
						peerMessage.setContent(content);
						send(peerMessage);
					}
				}
			} else {
				block();
			}
		}
	}

	@SuppressWarnings("unused")
	private void updateRecipientList() {
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("chat");
		template.addServices(sd);

		try {
			DFAgentDescription[] result = DFService.search(this, template);
			String[] currentRecipients = new String[result.length];
			for (int i = 0; i < result.length; ++i) {
				currentRecipients[i] = result[i].getName().getLocalName();
			}

			Object myAgent = null;
			// Verifică dacă myAgent este null înainte de a apela metoda
			if (myAgent != null) {
				((ChatAgent2) myAgent).updateRecipientList();
			}

		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
	}
}
