package main;

// Jade Imports
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class DF_Agent extends Agent {
    protected void setup() {
        // Creare descriere agent DF
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());

        // Adăugare serviciu pentru chat
        ServiceDescription sd = new ServiceDescription();
        sd.setType("chat-service");
        sd.setName("chat-agent");
        dfd.addServices(sd);

        // Înregistrare serviciu în DF
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    protected void takeDown() {
        // Dezregistrare la terminarea agentului DF
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }
}