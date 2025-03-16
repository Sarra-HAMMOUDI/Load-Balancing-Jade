package LectureEcritureFichier;

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;
import jade.core.Agent;

public class VideoSupervisor extends Agent{

	// serveur superviseur 
	public void setup()
	{
		
		//recevoir les message a partir de Agent2
		
		

		System.out.println("je suis le serveru1 des video , je suis dans le conteneur realservercontaienr ");
		
		addBehaviour(new OneShotBehaviour() {
			public void action() {
             	final ACLMessage request = new ACLMessage(ACLMessage.INFORM);
	            request.addReceiver(new AID("v2agent",AID.ISLOCALNAME));
	   
	       //     for(int lo=0 ; lo< 3 ; lo++)
	         //   {   
	            	request.setContent("197.205.171.239|l|video1.mp4");
    	            send(request);
    	            
    	        	request.setContent("197.205.171.239|d|video1.mp4");
    	            send(request);

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
	              
	          
	                   final ACLMessage request = msgFromAgent2.createReply();
	                   //ACLMessage request;
	 	               request.addReceiver(new AID("v2agent",AID.ISLOCALNAME));
	                   request.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
	 	               request.setContent("La requete est bien recue est bein recue; merci ");
	 	            send(request);
	 	            System.out.print("j arrive pas a trouver le v2agent , lessentiel je suis dans le block ");
	 	            
	 	        //   msgFromAgent2 = null;
	              
	                } else
	                {
	                	block();
	                }
		
	} });
 	
		
}}
