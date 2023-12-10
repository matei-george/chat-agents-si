package main;

import java.awt.BorderLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import jade.core.AID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class GUIAgent extends jade.core.Agent {

	private static final long serialVersionUID = 3007353778696543294L;
	private JFrame frame;
	private JComboBox<String> recipientComboBox;
	private JTextArea messageTextArea;
	private List<String> messageHistory;

	public static void main(String[] args) {
		try {
			// Pornirea platformei Jade
			jade.core.Runtime rt = jade.core.Runtime.instance();
			Profile profile = new ProfileImpl(null, 12345, null);

			// Obține containerul principal
			AgentContainer container = rt.createMainContainer(profile);

			if (container != null) {
				System.out.println("Container created successfully.");

				// Adăugarea agentului GUI
				AgentController agentController = container.createNewAgent("GUIAgent", "main.GUIAgent", new Object[0]);
				agentController.start();
				try {
					AgentController chatAgent1 = container.createNewAgent("ChatAgent1", "main.ChatAgent",
							new Object[0]);
					chatAgent1.start();
				} catch (StaleProxyException e) {
					e.printStackTrace();
				}

				// Crează și lansează al doilea agent de chat
				try {
					AgentController chatAgent2 = container.createNewAgent("ChatAgent2", "main.ChatAgent",
							new Object[0]);
					chatAgent2.start();
				} catch (StaleProxyException e) {
					e.printStackTrace();
				}
				System.out.println("GUIAgent created and started successfully.");
			} else {
				System.err.println("Error: Container is null.");
			}
		} catch (StaleProxyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void setup() {
		messageHistory = new ArrayList<>();
		initGUI();
		registerDFService(); // Adăugat apelul pentru metoda de înregistrare a serviciului
	}

	private void initGUI() {
		SwingUtilities.invokeLater(() -> {
			frame = new JFrame("Chat Application");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(400, 300);

			JPanel panel = new JPanel(new BorderLayout());

			recipientComboBox = new JComboBox<>();
			messageTextArea = new JTextArea();
			JButton sendButton = new JButton("Send");
			JButton saveHistoryButton = new JButton("Save History");

			panel.add(recipientComboBox, BorderLayout.NORTH);
			panel.add(new JScrollPane(messageTextArea), BorderLayout.CENTER);
			panel.add(sendButton, BorderLayout.SOUTH);

			JPanel buttonPanel = new JPanel();
			buttonPanel.add(saveHistoryButton);

			frame.getContentPane().add(panel, BorderLayout.CENTER);
			frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

			sendButton.addActionListener(e -> sendMessage());

			saveHistoryButton.addActionListener(e -> saveHistory());

			frame.setVisible(true);
			updateRecipientList();
		});
	}

	private void sendMessage() {
		String recipient = (String) recipientComboBox.getSelectedItem();
		String message = messageTextArea.getText();

		if (recipient != null && !recipient.isEmpty() && message != null && !message.isEmpty()) {
			AID receiverAID = new AID(recipient, AID.ISLOCALNAME);

			ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
			aclMessage.addReceiver(receiverAID);
			aclMessage.setContent(message);

			send(aclMessage);

			displayMessage("Me", message);

			messageHistory.add("Me: " + message);
			messageTextArea.setText("");
		}
	}

	public void displayMessage(String sender, String message) {
		String currentText = messageTextArea.getText();
		messageTextArea.setText(currentText + "\n" + sender + ": " + message);
	}

	private void saveHistory() {
		try (FileWriter writer = new FileWriter("message_history.txt")) {
			for (String message : messageHistory) {
				writer.write(message + "\n");
			}
			writer.flush();
			JOptionPane.showMessageDialog(frame, "Message history saved successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void registerDFService() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());

		ServiceDescription sd = new ServiceDescription();
		sd.setType("gui");
		sd.setName("GUIAgentService");
		dfd.addServices(sd);

		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}

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

			recipientComboBox.setModel(new DefaultComboBoxModel<>(currentRecipients));
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
	}

	public void updateHistoryList(String messageEntry) {
		messageHistory.add(messageEntry);
	}

	public void updateRecipientList(String[] recipients) {
		recipientComboBox.setModel(new DefaultComboBoxModel<>(recipients));
	}
}
