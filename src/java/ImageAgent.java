package LectureEcritureFichier;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

public class ImageAgent extends Agent {

	
	
	protected void setup()
	{
		// reception de message a partir de l agent procipale (intermisaire entre le client et moi 
		System.out.println("Bonjour , je suis "+ this.getAID().getLocalName() +"  je suis localisé dans le conteneur 3 -- ImageContainer");
		
		
	      
		
		addBehaviour(new CyclicBehaviour(){

			@Override
			public void action() {

	             ACLMessage msgFromPrincipalAgent = receive();
	                   if(msgFromPrincipalAgent != null )
	                {
	              System.out.println("reception du message"+ msgFromPrincipalAgent.getContent());
	              System.out.println("FROM :" + msgFromPrincipalAgent.getSender().getName());
	              
	             final ACLMessage repleyPrincipal = msgFromPrincipalAgent.createReply();
	             repleyPrincipal.setPerformative(ACLMessage.INFORM);
	             repleyPrincipal.setContent("La liste des requets Image  est bien recu . Merci, Mr "+ msgFromPrincipalAgent.getSender().getName() );
	             addBehaviour(new DelayBehaviour(myAgent, 6000) {
                     public void handleElapsedTimeout() {
                    	  send(repleyPrincipal);   	 
                    	 
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
