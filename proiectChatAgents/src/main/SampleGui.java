package main;



import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.*;

import jade.core.*;
import jade.gui.*;
import jade.lang.acl.*;


class SampleGui extends JFrame implements ActionListener {
// --------------------------------------------------------------------------


   private JLabel lb1 = new JLabel( "Enter an integer #1: " );
   private JLabel lb2 = new JLabel( "Enter an integer #2: " );
   private JLabel lb3 = new JLabel( "Enter an integer #3: " );
   private JTextField tf1, tf2, tf3;
   private JTextArea outputArea;
   private GUIAgent myAgent;
   private JPanel panel;
   private JButton ok;


   public SampleGui(GUIAgent a) {
// ----------------------------------------  Constructor

      myAgent = a;

      setTitle("Sample GUI Project");

      panel = new JPanel();


      tf1 = new JTextField(20);
      tf2 = new JTextField(20);
      tf3 = new JTextField(20);

	  panel.add(lb1);
      panel.add(tf1);
      panel.add(lb2);
      panel.add(tf2);
      panel.add(lb3);
      panel.add(tf3);

      outputArea = new JTextArea(10,25);
      outputArea.setEditable(false);

      panel.add(outputArea);

      ok = new JButton("OK");
      panel.add(ok);
      ok.setToolTipText("Submit operation");
      ok.addActionListener(this);

	  getContentPane().add(panel);

	  setSize(400, 300);
	  setResizable(false);
	  Rectangle r = getGraphicsConfiguration().getBounds();
	  setLocation(r.x + (r.width - getWidth())/2,
                  r.y + (r.height - getHeight())/2);

   }

   public void actionPerformed(ActionEvent ae) {
// ---------------------------------------------
		if (ae.getSource() == ok) {
		  if (tf1.getText().equals(""))
		  {
			//Performs no action
		  }
		  else
		  {
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setContent( tf1.getText() );
     		msg.addReceiver( new AID( "c1" , AID.ISLOCALNAME) );
			myAgent.send(msg);
		  }

		  if (tf2.getText().equals(""))
		  {
		  	//Performs no action
		  }
		  else
		  {
		  	ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		  	msg.setContent( tf2.getText() );
		    msg.addReceiver( new AID( "c2" , AID.ISLOCALNAME) );
		  	myAgent.send(msg);
		  }

		  if (tf3.getText().equals(""))
		  {
		  	//Performs no action
		  }
		  else
		  {
		  	ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		  	msg.setContent( tf3.getText() );
		  	msg.addReceiver( new AID( "c3" , AID.ISLOCALNAME) );
		  	myAgent.send(msg);
		  }
      }

   }

   public void alertResponse(String s) {
   // -------------------------------------

         outputArea.append(s);
   }




}
