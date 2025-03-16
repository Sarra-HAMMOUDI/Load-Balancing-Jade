package LectureEcritureFichier;


import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import Fichiers.Ecriture;
import Fichiers.Lecture;
import Fichiers.SpliteVersion2;


public class PrincipalAgentNiveau0 extends Agent  {
	// la declaration des variable 
	public int compteur =0 ;
	int sa=0;
	public String []m ;
	public int resu = 2900;
	public int tt=0;
	public AID [] ServeursFrontal = {new AID("VideoSupervisorNiveau1", AID.ISLOCALNAME), new AID("ImageAgent", AID.ISLOCALNAME),new AID("AutreAgent", AID.ISLOCALNAME)};
	
	// methode pour l affichage du pourcentage 
	
	public void pourcentage ()
	{
		addBehaviour( 
                new DelayBehaviour( this, 900)
                { 
                   public void handleElapsedTimeout() {
                	   tt= 0;
                	   while (tt< 20){
          	        	 tt = tt + 10;
          	        	 System.out.println(tt +"%");
          	    	       }
                	   
                	   addBehaviour(new DelayBehaviour(myAgent, 900) {
                           public void handleElapsedTimeout() {
                        	   while (tt< 40){
                    	        	 tt = tt + 15;
                    	        	 System.out.println(tt +"%");
                    	    	       }
 
                        	   addBehaviour(new DelayBehaviour(myAgent, 900) {
                                   public void handleElapsedTimeout() {
                                	   while (tt< 60){
                            	        	 tt = tt + 19;
                            	        	 System.out.println(tt +"%");
                            	    	       }

                        	   addBehaviour(new DelayBehaviour(myAgent, 900) {
                                   public void handleElapsedTimeout() {
                                	   while (tt< 80){
                            	        	 tt = tt + 17;
                            	        	 System.out.println(tt +"%");
                            	    	       }
                                	   addBehaviour(new DelayBehaviour(myAgent, 900) {
                                           public void handleElapsedTimeout() {
                                        	   while (tt< 100){
                                    	        	 tt = tt + 10;
                                    	        	 
                                    	        	 if(tt == 106){tt = tt -6 ; }
                                    	    	       }System.out.println(tt +"%");
                	                  	   
                                           }}) ; }}) ;  }}) ;  }}) ; }});   }
	
	
	public void setup()
	{
		
        System.out.println("----------- Container numero 1 ----------");
		System.out.println("je suis l'agent pricipale  "+ this.getAID().getLocalName() );
		System.out.println("je suis entrain d'attendre les requets arrivées des clients ");
		System.out.println("en attente .....");
		
	/**/	addBehaviour(new DelayBehaviour( this, 5000) {
            public void handleElapsedTimeout() { 
         	   System.out.println("Je viens de recevoir la liste des requets des clients ");
					System.out.println("en cours de la consultation  .....");
        
	       /**/       	addBehaviour(new DelayBehaviour(myAgent, 5000) {
		                public void handleElapsedTimeout() { 
		                	 System.out.println("Consultation terminé  .");
		    		         System.out.println("--------Chargement du Resultat --------");
		    		         System.out.println("");
		    		         pourcentage (); 
		    		         
		    		 /**/        addBehaviour(new DelayBehaviour(myAgent, 5000) {
		    		             public void handleElapsedTimeout() { 
		    		             	 SpliteVersion2 s = new SpliteVersion2 ();
		    		                  s.action();
		    		                  m= s.sss;
		    		                  
		    		                	  System.out.println("la valeur du tableau recuperer ");
		    		                	  System.out.println(m[0]);
		    		                	  System.out.println(m[1]);
		    		                	  System.out.println(m[2]);
		    		           /**/       addBehaviour(new DelayBehaviour(myAgent, 5000) {
		    		                      public void handleElapsedTimeout() { 
		    		                       System.out.println("je suis entrain de me preparer pour envoyer la liste des requets video, Images et autre   aux agents ; VideoAgent" +
		    		                       		" ImageAgent and autreAgent");
		    		                      
		    		                       		
		    		                       		System.out.println("envoi en cours");
		    		           		        
		    		           		       
		    		           		       /**/ 
		                                   addBehaviour(new DelayBehaviour(myAgent, 5000) {
		                                       public void handleElapsedTimeout() {
		                                     	  /* **************************************************** */
		                                     	  // l envoi de mesage 
				                                     	   addBehaviour(new OneShotBehaviour() {
				                                   			public void action() {
		
		                                     		final ACLMessage msgToVideoAgent = new ACLMessage(ACLMessage.INFORM);
		                                     		final ACLMessage msgToAutreAgent = new ACLMessage(ACLMessage.INFORM);
		                                     		final ACLMessage msgToImageAgent = new ACLMessage(ACLMessage.INFORM);
		                     			        	 
		                     			        	 msgToVideoAgent.addReceiver(new AID(ServeursFrontal[0].getLocalName(),AID.ISLOCALNAME));
		                     			        	 msgToVideoAgent.setContent(m[0]);
		                     			        	 String m0 = m[0];
		                     			        	// System.out.println("m o " + m[0]);
		                     			        	msgToImageAgent.addReceiver(new AID(ServeursFrontal[1].getLocalName(),AID.ISLOCALNAME));
		                     			        	msgToImageAgent.setContent(m[1]);
		                     			            //System.out.println("m1" + m[1]);
		                     			        	msgToAutreAgent.addReceiver(new AID(ServeursFrontal[2].getLocalName(),AID.ISLOCALNAME));
		                     			        	msgToAutreAgent.setContent(m[2]);
		                     			        	//System.out.println("m 2 " + m[2]);
		                     			        	 
		                     			        	 pourcentage ();  // atention il est trop critique 
		                     			        	 addBehaviour(new DelayBehaviour(myAgent, 4900) {
		 		                                       public void handleElapsedTimeout() {
		 		                                    	  System.out.println("Envoi Terminé");
		                     			        	    // hena normalmnt nebeda nahesseb le temps 
		 		                                    	 msgToVideoAgent.setPerformative(ACLMessage.REQUEST);
		 		                                    	 msgToAutreAgent.setPerformative(ACLMessage.REQUEST);
		 		                                    	 msgToImageAgent.setPerformative(ACLMessage.REQUEST);
		 		                                    	 
		 		                                    	  send(msgToVideoAgent);
		                     			        	      send(msgToAutreAgent);
		                     			        	      send(msgToImageAgent);
		                     			        	        	 
		                     			            } } );	  } } );
		                                     	   
		                                     	   // la reception de message 
		                                     	   addBehaviour(new CyclicBehaviour(){

		                                				@Override
		                                				public void action() {

		                                		             ACLMessage msgFromVideoAgent = receive();
		                                		                   if(msgFromVideoAgent != null )
		                                		                {
		                                		                  System.out.println(msgFromVideoAgent.getSender().getLocalName() +"  m'a envoyé  --- »  "+ msgFromVideoAgent.getContent());
		                                		                        
		                                		                 }else 
		                                		                   {  
		                                		                     block();
		                                		                    }
		                                		                            }
		                                		    	  
		                                		                          });
		                                		/* **************************************************** */

		                                     	                      addBehaviour(new DelayBehaviour(myAgent, 7000) {
				                                                              public void handleElapsedTimeout() {
				                                                            	  
				                                                            	  System.out.println("Greate ");
				                                    
            
            }}); }}); }});}});  }});  }});
	

	
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
