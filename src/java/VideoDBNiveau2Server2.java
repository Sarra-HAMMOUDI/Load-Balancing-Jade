package LectureEcritureFichier;

import java.util.Random;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

import Fichiers.Lecture;
import Fichiers.SpliteVersion2;
import Fichiers.DelayBehaviour;
public class VideoDBNiveau2Server2 extends Agent{

	
	/*
	SpliteVersion2 ssm= new SpliteVersion2();
	public float cpu ;
	
	public void setup()
	{
		 Lecture l =  new Lecture ("serveurBDvideoS2");
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
		 	

		 	
		 	float enG = (foo/1000);
		 	float fillRate = enG/7 ;

		 	System.out.println(foo + "MB");

		 	System.out.println( enG + "GA");
		 	
		 	System.out.println("fill rate = "+fillRate+ "MB");
		 	
		 	/// l etat de disc == s done 
		 	
		 	  Random randomno = new Random();
		 	 
		             int a = randomno.nextInt(10);
			         cpu = (float) (a * 0.1); 			    		  
			         System.out.println("l etat de cpu egal a : "+cpu);
		       
	}*/
	
	
	SpliteVersion2 ssm= new SpliteVersion2();
	public float cpu ;
    public String tailleglobal [];
	public String c ;
	
	ACLMessage msgFromAnnuaire;
    ACLMessage request;
    
	
	
	public  String [] decouperM (String d)
	{
		String[] tb = new String[1];
		tb = d.split("M");
		return tb ;
	}

	public static String [] decouper (String d)
	{
		String[] tb = new String[2];
		tb = d.split("\\.");
		return tb ;
	}
	//  <  > 
	public static String [] decoupervir (String d)
	{
		String[] tb = d.split(";");
		return tb ;
	}
	
	public static String [] decoupebar(String d)
	{
		String[] tb = d.split("\\|");
		return tb ;
	}
	

	public String [][] haveRequest(String c){
		SpliteVersion2 ssm= new SpliteVersion2();
	    String requets []   = decoupervir(c);
		String Mat [][] = new String [requets.length-1][requets.length-1];
		
		String taille[] =  new String[Mat.length];
        int tailleINT[] =  new int[taille.length];
        int foo = 0;

        
        System.out.println("requets");
    
        for (int j=0;j<requets.length-1 ; j++)
	         {
	
		        String temp[] = decoupebar(requets [j]);    
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
		 	float fillRate = enG/7 ;

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
		 final Lecture l =  new Lecture ("fichiersServers/serveurBDvideoS2");
		  c = l.Affichage();
 	   
           String Matrice[][]	=   haveRequest(l.Affichage());

		/* 
		 System.out.println(" etat disc egale a : "+  DISC(haveRequest(c)));
		 System.out.println("l etat de cpu egale a : "+ CPU());
		 */
	
		 // il faut envoyer les donée 
		 
		 
		 

			addBehaviour(new CyclicBehaviour(){

				@Override
				public void action() {

		              msgFromAnnuaire= receive();
		             
		             
		             if(msgFromAnnuaire != null )
		                {
		            
		            	 if(msgFromAnnuaire.getPerformative()== ACLMessage.CFP)
			            	{
		            	 request= msgFromAnnuaire.createReply();
   		                 request.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
   		                 request.setContent(l.Affichage()+"*"+CPU()+"*"+DISC(haveRequest(l.Affichage())));
   		                
   		                 System.out.println("le cpu "+CPU());
		                 System.out.println("le disc "+DISC(haveRequest(l.Affichage())));
		                 
		                
   		                             send(request);
		                       
   		                
   		                 
		            	    }else
		                   	{
		            		   if(msgFromAnnuaire.getPerformative()== ACLMessage.PROPOSE)
		            		     {
		            			// je lui repondre create reply
		            			// and respond him by lectur i  copy form exmpl or diposit 
		            			System.out.println(" the performative of that request  is propose ")	;            			
		            	      	}
		            		
		            		
		            		
		                 	}
                            	 
    	   
                          
		         	    }
		             else
		                { 
		                	block();
		                }
				}});
        
	}
	
	


}
		
	