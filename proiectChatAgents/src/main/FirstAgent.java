package main;

// Jade imports
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class FirstAgent extends Agent {
	@Override
	protected void setup() {
		addBehaviour(new OneShotBehaviour() {
			@Override
			public void action() {
//				Trimite mesajul catre cel de-al doilea agent
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.setContent("Send");
				msg.addReceiver(new AID("second",AID.ISLOCALNAME));
				send(msg);
			}
		});
		super.setup();
	}
}
