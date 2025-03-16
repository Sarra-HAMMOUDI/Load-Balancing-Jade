package LectureEcritureFichier;

import Fichiers.DelayBehaviour;
import Fichiers.SpliteVersion2;

import java.util.Enumeration;
import java.util.Hashtable;




import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
public class VideoAnnuaireNiveau1  extends Agent{
	 ACLMessage repleyPrincipal = null;


	// Les serveurs des video ,  leurs bade de donee la taille de chasque ficheir et l adresse ip de chaque serveur 
	
	public String [][] serveur1 = {{"video1.mp3","100"},{"video2.avi","200"}};
	public String [][] serveur2 = {{"video1.mp3","100"},{"video3.avi","500"}};
	
	public String [][] server1 ;
	public String [][] server2 ;
	public String [][] server3 ;
	  int compteur2 =0;

	  ACLMessage msgFromDBNiveau2;
      ACLMessage msgFromDBNiveau3;
      ACLMessage msgFromDBNiveau1;
    
      	
	  Enumeration  servers;
      float CPU , CPU2, CPU3;      
  	  float disc, disc2 , disc3 ; 
  	  String file ;
  	  String str;
  	  ACLMessage  requ;
  	  String typedmd ;
  	  String req ;
  	 boolean result[];
	
	// declaration de hashtable  ServersCPU pour indiquer l etat de cpu  de ch	aque serveur 
	
	public Hashtable ServersCPU = new Hashtable();
	
	// declaration de hashtable ServersDisc pour indiquer l etat de CPU de chaque serveur dans le network 
	
    public Hashtable ServersDISC = new Hashtable();
    public Hashtable ServersIP = new Hashtable();
    boolean dispose = false ;
    public String tailleglobal [];

    public String listserveur [] = new String [3];
    
    /*********************************************************************************/ 
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
    
    
    /*******************************************************************************/
    
	public static String [] decoupebar(String d)
	{
		String[] tb = d.split("\\|");
		return tb ;
	}
	public static String [] decouper (String d)
	{
		String[] tb = new String[2];
		tb = d.split("\\.");
		return tb ;
	}
	
	
	public Hashtable RemplirCPUState(String s, float cPU4)
	{	
		 ServersCPU.put(s, new Float(cPU4));
		 return ServersCPU ;	   
	}
	
	/*
	public Hashtable RemplirIPAdress()
	{
		 ServersIP.put("serveur1", new String("Serveur1"));
		 ServersIP.put("serveur2", new String("Serveur2"));
		 
		 return ServersIP ;	   
	}
	*/
		public Hashtable RemplirDISCState(String s,Float d)
	{
		ServersDISC.put(s, new Float(d));
	    return ServersDISC ;
	}
	int nombredeservuer;
	
	
	public boolean [] intialisation()
	{
		 boolean[] list =  new boolean[3];	
         for(int j=0 ; j<3; j++)
			{
       	 list[j]= false;
			}
         
         return list;
	}
	
	public boolean[] service(String file)
	{
		   
       // RemplirDISCState();
        boolean[] list= intialisation();
		
		String str ;
//		 RemplirDISCState();
		servers = ServersDISC.keys();
		
		 
		 ///////////////////***********************************///////////////////
		    while(servers.hasMoreElements())
		    { 
		       str = (String) servers.nextElement();
		       if(str.equals("server1"))
		       {
		    	   // il est claire que le nombre de serveur est connu , est les noms de serveurs est bien connu meme les noms des matrice 
		    	   // le but principale de ce travial la est de bien allouer la valeur true a la case correspondante serveur1 ** case 0 ; serveur2 case1  etc ..
		    	   for(int j=0 ; j<server1.length; j++) 					
		    	   {   
		    		   if (server1[0][j].equals(file))
 						{
		    			   list[0] = true;
		    			   listserveur [0]= "server1";// on peut les intialiser directement mais  just pour  ne pas oublier leurs but.
		    					   break;
		    			   
 						} 
 				   }
		       }
		       else
		       {
		    	   if (str.equals("server2"))
		    	   {
		    		   for(int j=0 ; j<server2.length; j++) 					
			    	   {   
			    		   if (server2[0][j].equals(file))
	 						{
			    			   list[1] = true;
			    			   listserveur [1]= "server2";
			    					   break;
	 						} 
	 				   }
			       }else
			       {
			    	   if (str.equals("server3"))
			    	   {

			    		   for(int j=0 ; j<server3.length; j++) 					
				    	   {   
				    		   if (server3[0][j].equals(file))
		 						{
				    			   list[2] = true;
				    			   listserveur [2]= "server3";
				    					   break;
		 						} 
		 				   }
			    	   }
			       }
		    	   }
		    	   
		       }
		   
       return list;
	}
// 	t[0]==   if t  0 == true , Serveur1.men hash table  
//  cette fonction me retourne l adress ip du serveur parfait 
	
	public String BestServer (String typedmd , boolean [] list)
	{  Float minDISC  =  (float) 1.09;
	  	Float minCPU  =  (float) 1.09;
	
	String bonendroi = null; 
		if (typedmd.equals("d"))
		{
			
			 for(int j=0 ; j<list.length; j++) 
			 {
				 if(list[j])
				 {
					    //RemplirDISCState();
					     
					    Float valDISC =  ((Float)ServersDISC.get(listserveur[j])).floatValue();		
					 
					    if (valDISC < minDISC)
					    {
					    	minDISC  = valDISC;
					    	bonendroi  	= listserveur[j];
					    	
					    }
					   
					    
				 }
			 }
			
		}else  // if la requet typedmd = lecture
		{
			 for(int j=0 ; j<list.length; j++) 
			 {
				 if(list[j])
				 {
					// RemplirCPUState();
					     
					    Float valCPU =  ((Float)ServersDISC.get(listserveur[j])).floatValue();
					    if (valCPU< minCPU)
					    {
					    	minDISC  = valCPU;
					    	bonendroi  	= listserveur[j];
					    	
					    }
					   
					   	   
				 }
			 }
		}
		
		return bonendroi;
	}
	
	
	
	public void callingForRequest ()
	{
		// recupertion du fichier
				addBehaviour(new OneShotBehaviour() {
					public void action() {
		             	// pour le serveur 1 
						final ACLMessage request1 = new ACLMessage(ACLMessage.CFP);
			            request1.addReceiver(new AID("server1",AID.ISLOCALNAME));
			            send(request1);
			            
			            
			            // pour le serveur 2
			            final ACLMessage request2 = new ACLMessage(ACLMessage.CFP);
			            request2.addReceiver(new AID("server2",AID.ISLOCALNAME));
			            send(request2);

			            
			            final ACLMessage request3 = new ACLMessage(ACLMessage.CFP);
			            request3.addReceiver(new AID("server3",AID.ISLOCALNAME));
			            send(request3);
			            
			            System.out.println("j ai envoyé les messages c bon ");
			            
					}});
	}

	public void setup()
	{
		
		callingForRequest();
		addBehaviour(new CyclicBehaviour(){
			
			@Override
			public void action() {
			
				 
				 	
			 requ =receive();
			       
	             if(  requ != null )
	              
	                  {
	            	 
//	                   if( requ.getPerformative()== ACLMessage.ACCEPT_PROPOSAL)
	//                   {
	                	         if (requ.getSender().getLocalName().equals("server1"))
	  	            	            {   
	  	            		         
	                	        	 String server1content [] = decoupebar(requ.getContent());  
	                	        	 
	                	   //     	 msgFromDBNiveau1 = server1content[0]   ;
	
	                	        	 String content = server1content[0];
	                	        	 CPU =    Float.parseFloat(server1content[1]);
	                	             disc = Float.parseFloat(server1content[2]);
	                	             String matrice1 [][] = haveRequest(content);
	                	        	 
	  	            		        /* filling the hashtable  */
	  	            		      
	  	 	              	          RemplirCPUState("server1",CPU);
	  	 	            	          RemplirDISCState("server1",disc);

	  	            	             }
	  	            	 
	  	            	          if (requ.getSender().getLocalName().equals("server2"))
	  	            	            {	
	  	            		 
	  	            	        	String server2content [] = decoupebar(requ.getContent());  
	                	        	 
	  		                	   //     	 msgFromDBNiveau1 = server1content[0]   ;
	  		
	  		                	        	 String content2 = server2content[0];
	  		                	        	 System.out.println(content2);
	  		                	        	 CPU2 =    Float.parseFloat(server2content[1]);
	  		                	             disc2 = Float.parseFloat(server2content[2]);
	  		                	          //   String matrice2 [][] = haveRequest(content2);
	  		                	        	 
	  		  	            		        /* filling the hashtable  */
	  		  	            		      
	  		  	 	              	          RemplirCPUState("server2",CPU2);
	  		  	 	            	          RemplirDISCState("server2",disc2);

	  		            	
	  	            	            }
	  	            	          if (requ.getSender().getLocalName().equals("server3"))
	  	            	            {	
	  	            	        	String server3content [] = decoupebar(requ.getContent());
	  	            	        	String content3 = server3content[0];
		                	        CPU3=    Float.parseFloat(server3content[1]);
		                	        disc3 = Float.parseFloat(server3content[2]);
		                	        String matrice3 [][] = haveRequest(content3);
		                	        	 
		  	            		        /* filling the hashtable  */
		  	            		      
		  	 	              	          RemplirCPUState("server3",CPU3);
		  	 	            	          RemplirDISCState("server3",disc3);
	  	            	             }
	  	            	 
	  	            	            
	  	            	               //  addBehaviour(new DelayBehaviour(myAgent, 1) {
	                                     //   public void handleElapsedTimeout() {
	                          	 
	                          	  /*
	          	       			             System.out.print(" ******************************** Les donées du  serveur 2 ************************* ");
	          		            	
	          		                         System.out.println(" le CPU egale a : "+ CPU2);
	          		                         System.out.println(" le disc egale a : "+ disc2); 
	          		                
	          		             
	          		                           for(int k=0 ; k< server2.length-1 ; k++) 
	          	                                  {
	          	                                  System.out.println(" ok  "+server2[0][k] );
	          	                                  System.out.println(" ok  "+server2[1][k] );
	          	                                  }            	
	          	     
	          		            
	          		               
	          	       			    
	          	       			             System.out.print(" ******************************** Les donées du  serveur 3 ************************* ");
	          		            	
	          		                         System.out.println(" le CPU egale a : "+ CPU3);
	          		                         System.out.println(" le disc egale a : "+ disc3);
	          		                
	          		             
	          		                           for(int j=0 ; j<server3.length-1; j++) 
	          	                                 {
	          	                                 System.out.println(" ok  "+server3[0][j] );
	          	                                 System.out.println(" ok  "+server3[1][j] );
	          	                                 }
	          		               
	          		                

	          		            	          System.out.print(" ******************************** Les donées du  serveur 1 ************************* ");
	          		            	
	          		                              System.out.println(" le CPU egale a : "+ CPU);
	          		                              System.out.println(" le disc egale a : "+ disc);
	          		              
	          		                
	          		                           for(int j=0 ; j<server1.length-1 ; j++) 
	          	                                   {
	          	                                   System.out.println(" ok  "+server1[0][j] );
	          	                                   System.out.println(" ok  "+server1[1][j] );
	          	                                    }
	          		               	 
	 
	          		                           /* ********************************************************************************/
	          		                           
	          		                           
	          		                           
	          		                     /*      
	          		                         String [] t1 = decoupebar(req);      
	 			                            
	 			                            // final String[] t2 = decouper (t1[2]);
	 			                             typedmd = t1[1];  
	 			                             file = t1[2];
	 			                          
	 			                             
	 			                             // le but c recuperer le nom du serveur ideal 
	 			                           // addBehaviour(new DelayBehaviour(myAgent, 1) {
	                                     //  public void handleElapsedTimeout() {
	 			                                 System.out.println(" typedmd = "  +typedmd );
	 			                                 System.out.println(" file  = "+ file );
	 			              
	 			                           
	 			                 
	 			                               result =  service(file);
	 			                                 for(int j=0 ; j<result.length; j++) 
	 						                         {
	 			                                     System.out.println("result = "+result[j] );
	 						                         }
	 			            
	 			                            String bestdistination =  BestServer (typedmd , result);
	 			          
	 			                            System.out.println("Bonjour, tu c que c toi le mieulleur parmis tous les serveurs  ;), merci MR : " + bestdistination);  
	 			                             // reply lel agent1
	 			                 
	 		                               
	 		                               // envoi d un message au seveur1
	 		                                
	 		                                 final ACLMessage request = new ACLMessage(ACLMessage.CFP);
	 		  	                             request.addReceiver(new AID(bestdistination,AID.ISLOCALNAME));
	 		  	                             request.setContent(req);
	 		  	                             send(request);
	          		                           
	          		                           
	          		                           
	          		                           */
	          		                           
	          		                           
	          		                           
	          		                           
	          		                           
//	                                        }});
	//  	          	             compteur2 ++ ;
	  	          	              
	  	            	       
	             
	  /*	            	             
	                   }
	  	            	 else
	  	            	 {
	  	         
	  		                    	if( requ.getPerformative()== ACLMessage.INFORM)
	  		  	            		{  
	  		                    		req = requ.getContent();
	  		                    	//	callingForRequest();
	  		                    		
	  		                	   
	  		                            System.out.println("reception du message"+ requ.getContent());
	  		                            System.out.println("FROM :" + requ.getSender().getLocalName());
	  		                            
	  		                                /*********************************************/ 

 			                            /*
 			                            ACLMessage repleyPrincipal = requ.createReply();
 			                           repleyPrincipal.setPerformative(ACLMessage.INFORM);
 			                           repleyPrincipal.setContent("La liste des requets VIDEO  est bien recu . Merci, Mr "+ requ.getSender().getName());
 		                               send(repleyPrincipal);  */
 		                               
	  		                                
	  			                            		                                      
		                                        
		                                       //}});
	  	            			
	  	            			
	  	            		// }
	  	           		 
	  	            	 //} 
	                	   
    	            	 
	            	 
	            		            	 
	        	 servers = ServersCPU.keys();
	       		 
	       		 System.out.println("ServersCPU information ");
	       		    while(servers.hasMoreElements())
	       		    { 
	       		  
	       		       str = (String) servers.nextElement();
	       	           System.out.println(str + ": " + ServersCPU.get(str));
	       		           
	       		    }
	       		
	       		 System.out.println("ServersDISC information ");
	       			 servers = ServersDISC.keys();
	       			    while(servers.hasMoreElements())
	       			    { 
	       			  
	       			       str = (String) servers.nextElement();
	       		           System.out.println(str + ": " + ServersDISC.get(str));
	       			           
	       			    }
	       			    
	       				                
		                
		                
		                
	            	 
	            	 /*
	            	 final ACLMessage request = msgFromDBNiveau2.createReply();
	                 request.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
	 	             request.setContent();
	 	             send(request);*/
	       			    
	             } 
	             else
	                {
	                	block();
	                }
			
	             }});
		
		
		
	
		
		/*
		System.out.println("je suis ddans la meme plateforme avec l agent video ");
		
		addBehaviour(new CyclicBehaviour(){

			@Override
			public void action() {

	             ACLMessage msgfromsupervisor = receive();
	                   if(msgfromsupervisor != null) 
	                		   //&&   msgFromAgent1.getSender().getName().equals("v1agent"))
	                {
	                	   
	                	   
	                            System.out.println("reception du message"+ msgFromAgent1.getContent());
	                            System.out.println("FROM :" + msgFromAgent1.getSender().getLocalName());
	                            
	                            /*********************************************/ 
	            /*        if(msgfromsupervisor.getPerformative() == ACLMessage.INFORM){
	                            	
	                            	String req = msgfromsupervisor.getContent();
		                            String [] t1 = decoupebar(req);       
		                            // final String[] t2 = decouper (t1[2]);
		                            final String typedmd = t1[1];  
		                            String file = t1[2];
		                             // le but c recuperer le nom du serveur ideal 
		                            System.out.println(" typedmd = "  +typedmd );
		                            System.out.println(" file  = "+ file );
		              
		              
		                            boolean result[] =  service(file);
		                            for(int j=0 ; j<result.length; j++) 
					                     {
		                                 System.out.println("result = "+result[j] );
					                     }
		            
		                            String bestdistination =  BestServer (typedmd , result);
		          
		                            System.out.println("Bonjour, tu c que c toi le mieulleur parmis tous les serveurs  ;), merci MR : " + bestdistination);  
		             // reply lel agent1
		                           ACLMessage repleyPrincipal = msgfromsupervisor.createReply();
		                           repleyPrincipal.setPerformative(ACLMessage.INFORM);
		                           repleyPrincipal.setContent("La liste des requets VIDEO  est bien recu . Merci, Mr "+ msgfromsupervisor.getSender().getName());
	                               send(repleyPrincipal);  
	                               
	                               
	                               // envoi d un message au seveur1
	                                
	                                 final ACLMessage request = new ACLMessage(ACLMessage.CFP);
	  	          ========== here bestdestination                   request.addReceiver(new AID("serveur1",AID.ISLOCALNAME));
	  	                             request.setContent(msgFromAgent1.getContent());
	  	                             send(request);
	                  
	  	                             	  	                             
	  	                             
	  	                             
	  	                             repleyPrincipal = msgFromAgent1.createReply();           
	  	                           if (repleyPrincipal.getPerformative() == ACLMessage.ACCEPT_PROPOSAL)
	                            	 {
	                            	   
	      	          	            //  repleyPrincipal.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
	      	          	               repleyPrincipal.setContent("L aquittement est bien reçu  "+ msgFromAgent1.getSender().getName());
	      	                           send(repleyPrincipal);  
	      	                           //repleyPrincipal = null;
	      	                           
	                            	 }

	                  }            	                           
	                            
	                           

	               }
	                      else 
	                   {   
	                	   block();
	                   }
                     }});	
		

	
	*/
	
	

	

	
	}
}
