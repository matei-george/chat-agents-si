package main;


import java.io.*;
import java.util.Date;

import jade.core.*;
import jade.core.behaviours.*;
import jade.domain.*;
import jade.domain.FIPAAgentManagement.*;
import jade.lang.acl.*;
import jade.content.*;
import jade.content.lang.*;
import jade.content.lang.sl.*;
import jade.content.onto.*;
import jade.content.onto.basic.*;
import jade.util.leap.*;
import jade.gui.*;

import javax.swing.*;



public class GUIAgent extends Agent{
// ----------------------------------------------------------------------

  // static final int WAIT = -1;
  // static final int QUIT = 0;
  // private int command = WAIT;
  // private int cnt = 0;


   transient protected SampleGui myGui;  // The gui

   protected void setup() {

      // Set up the gui
      myGui = new SampleGui(this);
      myGui.setVisible(true);

      addBehaviour(new ReceiveResponse(this));
   }

   protected void takeDown() {
// ---------------------------  Terminate the program properly


    }


   class ReceiveResponse extends CyclicBehaviour {
// -----------------------------------------------  // Receive and handle server responses


      ReceiveResponse(Agent a) {
         super(a);
      }

      public void action() {

         ACLMessage msg = receive();

         if (msg == null)
         {
			 block(); return;
         }

         if (msg.getPerformative() == ACLMessage.INFORM){
            myGui.alertResponse("Calculation Result from agent " + msg.getSender().getLocalName() + ": " + msg.getContent());
         }

      }

   }


}
