package LectureEcritureFichier;

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;
import jade.core.Agent;

public class VideoSupervisorNiveau1 extends Agent{

	 
	public void setup()
	{
	
		addBehaviour(new OneShotBehaviour() {
			public void action() {
  //           	final ACLMessage request = new ACLMessage(ACLMessage.INFORM);
	//            request.addReceiver(new AID("VideoAnnuaireNiveau1",AID.ISLOCALNAME));
	   
	      
//	            	request.setContent("197.205.171.239|l|ipcop.mp4;197.205.171.250|l|Resistance.mp4;197.205.171.250|l|dora.mp4");
//    	            send(request);
    	       
    	        	/*
    	            request.setContent("197.205.171.250|l|Resistance.mp4");
    	            send(request);
    	        	*/
	           // }    		
			}});


		
		addBehaviour(new CyclicBehaviour(){

			@Override
			public void action() {

	             ACLMessage msgFromAgent2 = receive();

	             
	             if(msgFromAgent2 != null )
	                {
	            	 
	   	               System.out.println("reception du message"+ msgFromAgent2.getContent());
	                   System.out.println("FROM :" + msgFromAgent2.getSender().getName());
	                   final ACLMessage request2 = msgFromAgent2.createReply();
	                   //ACLMessage request;
	 	               //request.addReceiver(new AID("v2agent",AID.ISLOCALNAME));
	                   request2.setPerformative(ACLMessage.INFORM);
	 	               request2.setContent("La requete est bien recue est bein recue; merci ");
	 	               send(request2);
	 	               
	 	                final ACLMessage request = new ACLMessage(ACLMessage.INFORM);
	 		            request.addReceiver(new AID("VideoAnnuaireNiveau1",AID.ISLOCALNAME));
	 		
	 		            request.setContent(msgFromAgent2.getContent());
	 	    	        send(request);

	 	               
	 	               

	 	                
	 	                
	 	                
	                } else
	                {
	                	block();
	                }
		
	} });
 	
		
}}
