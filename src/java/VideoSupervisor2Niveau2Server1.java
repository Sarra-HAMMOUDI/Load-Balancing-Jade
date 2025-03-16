package LectureEcritureFichier;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

public class VideoSupervisor2Niveau2Server1 extends Agent {

	
	  ACLMessage msgFromPrincipalAgent;
	  String requests[];

		public static String [] decoupevir(String d)
		{
			String[] tb = d.split(";");
			return tb ;
		}
	protected void setup()
	{
		// reception de message a partir de l agent procipale (intermisaire entre le client et moi 
		System.out.println("Bonjour , je suis "+ this.getAID().getLocalName() +"  je suis localisé dans le conteneur 2");
		
		
		
		addBehaviour(new CyclicBehaviour(){

			@Override
			public void action() {

	              msgFromPrincipalAgent = receive();
	                   if(msgFromPrincipalAgent != null )
	                {
	              System.out.println("reception du message"+ msgFromPrincipalAgent.getContent());
	              System.out.println("FROM :" + msgFromPrincipalAgent.getSender().getName());
	            
	              
	              //pour lui dire que le message est bien reçu   
	             final ACLMessage repleyPrincipal = msgFromPrincipalAgent.createReply();
	             repleyPrincipal.setPerformative(ACLMessage.INFORM);
	             repleyPrincipal.setContent("La liste des requets Video  est bien recu . Merci, Mr "+ msgFromPrincipalAgent.getSender().getName() );
	             //  juste pour voir le contenu
	             addBehaviour(new DelayBehaviour(myAgent, 5000) {
                     public void handleElapsedTimeout() {
                    	  send(repleyPrincipal);   	 
                       	 // il envoie la  requete l'une a la suite de l'autre pour faire la diriger vers le bon serveur 
                    	  
                     
                    	  addBehaviour(new OneShotBehaviour() {
                  			public void action() {
              	             	final ACLMessage request = new ACLMessage(ACLMessage.INFORM);
                  	            request.addReceiver(new AID("v2agent",AID.ISLOCALNAME));
                  	            // fragmentation des requets 
                  	            
                  	            
                  	            //  ki nekeryi la base de donnée ta3 serveru , w nchouf kifeh n enregistriha bah netesti 
                  	          requests =    decoupevir(msgFromPrincipalAgent.getContent());
       
                  	             for(int lo=0 ; lo< requests.length; lo++)
                 	            {                   	          
                  	            request.setContent(requests[lo]);
                  	            send(request);
                 	            }
                  			}});
             
                     }});
	             
	           
	                      
	                        
	                        
	                        }else 
	                               {  
	                             block();
	                               }
	                            }
	    	  
	                          });

		
	 	
	              	}
		
	

	// la class DELAYbEHAVIOUR Pour  faire le blocage
		public class DelayBehaviour extends SimpleBehaviour 
		{
		   private long    timeout, 
		                   wakeupTime;
		   private boolean finished = false;
		   
		   public DelayBehaviour(Agent a, long timeout) {
		      super(a);
		      this.timeout = timeout;
		   }
		   
		   public void onStart() {
		      wakeupTime = System.currentTimeMillis() + timeout;
		 //     System.out.println("wakeupTime igale a : "+ wakeupTime);
		   }
		      
		   public void action() 
		   {
		      long dt = wakeupTime - System.currentTimeMillis();
		      if (dt <= 0) {
		         finished = true;
		         handleElapsedTimeout();
		      } else 
		         block(dt);
		         
		   } //end of action
		   
		   protected void handleElapsedTimeout() // by default do nothing !
		      { } 
		            
		   public boolean done() { return finished; }
		}



	
	
	
	
	
	
	
	
	
	
		
		
		}
