package LectureEcritureFichier;

import java.util.Random;


import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import Fichiers.Ecriture;
import Fichiers.Lecture;
import Fichiers.SpliteVersion2;



public class VideoDBNiveau2Server3 extends Agent{

	
	SpliteVersion2 ssm= new SpliteVersion2();
	public float cpu ;
    public String tailleglobal [];
	public String c ;
	 ACLMessage msgFromAnnuaire; 
	public ACLMessage request;
	
	
	Ecriture e = new Ecriture();
	Lecture l =  new Lecture ("fichiersServers/serveurBDvideoS3");
	/////////////
	
	public static String [] decoupebar(String d)
	{
		String[] tb = d.split("\\|");
		return tb ;
	}
//////////////////

	public String [][] haveRequest(String c){
		SpliteVersion2 ssm= new SpliteVersion2();
	    String requets []   = ssm.decoupervir(c);
		String Mat [][] = new String [requets.length-1][requets.length-1];
		
		String taille[] =  new String[Mat.length];
        int tailleINT[] =  new int[taille.length];
        int foo = 0;

        
        System.out.println("requets");
    
        for (int j=0;j<requets.length-1 ; j++)
	         {
	
		        String temp[] = ssm.decoupebar(requets [j]);    
		        String m [] = ssm.decouperM(temp[0]);
		        taille[j]=m[0];
    			Mat[1][j] = temp[0];                
                Mat[0][j]= temp[1];     
                 
               }
        
        tailleglobal= taille ; 
		return Mat;
	}
	
	
	
	
	// tableau de la taille DISC 
	

	public double DISC (String m[][])
	{
		int  tailleINT[] = new int [m.length];
	    String taille []= new String [m.length];

	    // faut remplir tout d abord le tableau taille 
	    
	    
	    taille = tailleglobal;
	    
	    
	    int foo =0 ;
		
		 for (int j=0;j<m.length-1; j++)
	       {
	     	 tailleINT[j] = Integer.parseInt(taille[j]);	
	       }	
		
	   	 for (int j=0;j<taille.length; j++)
	       {
			foo  =tailleINT[j] +foo; 
	       }	
		
		 	// on suppose que ce serveur est saturé .ce serveur a 4 G
		 	
		 	float enG = (foo/1000); 
		 	float fillRate = enG/5 ;

return fillRate;
	
	}
	

	public double CPU ()
	{
	 	  Random randomno = new Random();
		 	 
          int a = randomno.nextInt(10);
	      cpu = (float) (a * 0.1); 			    		  

	         
	         return cpu;
	}
	
	


	
	public void setup()
	{
		 final Lecture l =  new Lecture ("fichiersServers/serveurBDvideoS3");
		  c = l.Affichage();
 	   
           String Matrice[][]	=   haveRequest(l.Affichage());

           
           
           
			addBehaviour(new CyclicBehaviour(){

				@Override
				public void action() {

		              msgFromAnnuaire= receive();
		             
		             if(msgFromAnnuaire != null )
		                {
		            	 if(msgFromAnnuaire.getPerformative()== ACLMessage.CFP)
			            	{
		            	 
		 	                         request = msgFromAnnuaire.createReply();
		 			                 request.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
		 			                 String s = l.Affichage()+"*"+CPU ()+"*"+DISC(haveRequest(l.Affichage()));
					                 request.setContent(s);
					                 System.out.println(s);
					 	             send(request);
					 	             
			            	}else
			            	{
			            		if(msgFromAnnuaire.getPerformative()== ACLMessage.REQUEST)
			            		{
			            			   String complexReq  = msgFromAnnuaire.getContent();
			            			   // decouper ; extraire l element 1 ; si d alors  le contenu va etre  jouter dans le fichier 
			            			   
			            			  String tab [] =  decoupebar(complexReq);
			            			  System.out.println("tab[1] :"+ tab[1]);
			            			  System.out.println("tab[2]"+tab[2]);
			            			  
			            			  String type = tab[1];
			            			  if(type.equals("d"))
			            			  {
			            				  
			            				  Random randomno = new Random();
			     					 	 
			        			          int a = randomno.nextInt(50);
			        				       			    		
			        					 String Content = a+"M|"+tab[2]+";"; 

			        					 System.out.println(" le contenu egal a : "+Content );

			        						String c = l.Affichage();
			        						c = c+Content;
			        						System.out.println("la nouvelle valeur du fichier egal a :"+ c);
			        						
			        						e.write("fichiersServers/serveurBDvideoS3", c);
 
			            				  
			            			  }
			            			   
			            			   
			            			   
			            			   
			            		}
			            	}
                           
		 	             
		 	        
		                }
		             else
		                { 
		                	block();
		                }
				}});
        
	}
		/*
	SpliteVersion2 ssm= new SpliteVersion2();
	public float cpu ;
	
	public void setup()
	{
		 Lecture l =  new Lecture ("fichiersServers/serveurBDvideoS3");
		 String c = l.Affichage();
    
		 String requets [] = ssm.decoupervir(c);
		 String Mat[][]= new String [requets.length-1][requets.length-1];
         String taille[] =  new String[Mat.length];
         int tailleINT[] =  new int[taille.length];
         int foo = 0;
         
		 System.out.println(requets.length);
		
		 for (int j=0;j<requets.length-1 ; j++)
	       {
                 
			      String temp[] = ssm.decoupebar(requets [j]);
                  
			              String m [] = ssm.decouperM(temp[0]);
			              taille[j]=m[0];
         			      Mat[1][j] = temp[0];                
                          Mat[0][j]= temp[1];                   
           } 	
		 	
		 for (int j=0;j<taille.length-1; j++)
	       {
	     	 tailleINT[j] = Integer.parseInt(taille[j]);
			
	       }	
		
		 for (int j=0;j<taille.length-1; j++)
	       {
			foo  =tailleINT[j] +foo; 
	       }	
		 	System.out.println(foo + "MB");
		 	

		 	// on suppose que ce serveur est saturé .ce serveur a 4 G
		 	
		 	float enG = (foo/1000);
		 	float fillRate = enG/7 ;

		 	System.out.println(foo + "MB");

		 	System.out.println( enG + "GA");
		 	
		 	System.out.println("fill rate = "+fillRate+ "MB");
		 	
		 	/// l etat de disc == s done 
		 	
		 	  Random randomno = new Random();
		 	 
		             int a = randomno.nextInt(10);
			         cpu = (float) (a * 0.1); 			    		  
	   		          
		       
	}*/
	
	

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
		
	