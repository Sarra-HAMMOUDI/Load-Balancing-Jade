package LectureEcritureFichier;

import java.util.Enumeration;
import java.util.Hashtable;

import Fichiers.DelayBehaviour;
import Fichiers.SpliteVersion2;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class VideoAnnuaireNiveau1Version3 extends Agent {

	
	float CPU , CPU2, CPU3;      
	float disc, disc2 , disc3 ; 
	ACLMessage requ;
	String file ,  typedmd ,req ,str;
	 boolean result[];
	 String [] requets;
     int cases ;
     MessageTemplate mt;

    public Hashtable ServersCPU = new Hashtable();
    public Hashtable ServersDISC = new Hashtable();
    public Hashtable ServersIP = new Hashtable();
    
    public String tailleglobal []; 	
	Enumeration  servers;
    
	
	boolean Access [] = new boolean [3]; 
	
	 String [] typedmd2 = new String [2];
     String [] file2 = new String [2];
	 public String listserveur [] = new String [3];
	    public String [][] server1 ;
		public String [][] server2 ;
		public String [][] server3 ;
		SpliteVersion2 ssm= new SpliteVersion2();
		
    /****************************************************************************************/
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
	/**************************************************************************************************/
    
    public static String [] decoupebar(String d)
	{
		String[] tb = d.split("\\|");
		return tb ;
	}
    
    
    public static String [] decoupers (String d)
    {
    	String[] tb = d.split("\\*");
		return tb ;
    }
    /*************************************************************************************************/
   
    public Hashtable RemplirCPUState(String s, float CPU)
	{	
		 ServersCPU.put(s, new Float(CPU));
		 return ServersCPU ;	   
	}
	
	/***************************************************************************************************/
		public Hashtable RemplirDISCState(String s,Float d)
	{
		ServersDISC.put(s, new Float(d));
	    return ServersDISC ;
	}
		
	/**************************************************************************************************/
		public void callingForRequest ()
		{
			// recupertion du fichier
					addBehaviour(new OneShotBehaviour() {
						public void action() {

							
							
							
							
							
							final ACLMessage request1 = new ACLMessage(ACLMessage.CFP);
							
	                        	 request1.addReceiver(new AID("server2",AID.ISLOCALNAME));					                     

	                        	 request1.addReceiver(new AID("server1",AID.ISLOCALNAME));	

									request1.addReceiver(new AID("server3",AID.ISLOCALNAME));
							
						/*	
							request1.setConversationId("Database-request");
							request1.setReplyWith("request1"+System.currentTimeMillis());
						    mt = MessageTemplate.and(MessageTemplate.MatchConversationId("Database-request"),
							MessageTemplate.MatchInReplyTo(request1.getReplyWith()));*/
							send(request1);
				            
				           
				           /*
				            // pour le serveur 2
				            final ACLMessage request2 = new ACLMessage(ACLMessage.CFP);
				            request2.addReceiver(new AID("server2",AID.ISLOCALNAME));
				            send(request2);
				            
				            final ACLMessage request3 = new ACLMessage(ACLMessage.CFP);
				            request3.addReceiver(new AID("server3",AID.ISLOCALNAME));
				            send(request3);
				            
				            System.out.println("j ai envoyé les messages c bon ");
				            */
						}});
		}
	/**************************************************************************************************/
		public boolean [] intialisation()
		{
			 boolean[] list =  new boolean[3];	
	         for(int j=0 ; j<3; j++)
				{
	       	 list[j]= false;
				}
	         
	         return list;
		}

  /******************************************************************************************************/
		public boolean[] service(String file)
		{
			   
	       // RemplirDISCState();
	        boolean[] list= intialisation();
			
			String str ;
//			 RemplirDISCState();
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
					    					   
			 						} 
			 				   }
				    	   }
				       }
			    	   }
			    	   
			       }
			   
	       return list;
		}
		/******************************************************************************************************************/
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
		/*********************************************************************************************/
		
		/*************************************************************************/
		/*************************************************************************/
		 int number =0 ;
		  int number2 = 0; 
		
		public void setup()
		{  
			
			addBehaviour(new CyclicBehaviour(){
				
				@Override
				public void action() {
				
					 
				 requ = receive(); 	
				       
	     if(  requ != null )	              
		     {
		            	 
                if( requ.getPerformative()== ACLMessage.ACCEPT_PROPOSAL)
		            {
		                	       
                    	switch (cases) {
                		case 0:
                		{
                			if (requ.getSender().getLocalName().equals("server1"))
	            	            {   
	            		         number ++ ;
                				   Access [0]= true;
            	        	      String server1content [] = decoupers(requ.getContent());  
            	        	 

            	        	 String content = server1content[0];
            	        	 CPU =    Float.parseFloat(server1content[1]);
            	             disc = Float.parseFloat(server1content[2]);
            	             
            	             
            	                System.out.println(content);
                	        	System.out.println(CPU);
                	        	System.out.println(disc);
            	                String matrice1 [][] = haveRequest(content);
            	                System.out.println(matrice1.length);
            	                System.out.println("*******************La matrice1******************************************");
            	                System.out.println("number = "+ number);
            	                
            	                for (int j=0;j< matrice1.length-1 ; j++)
            	     	         {
            	                	System.out.println(matrice1 [0][j]);
            	                	System.out.println(matrice1 [1][j]);
            	                 }
            	                
            	                server1 = matrice1 ;
            	               
	            		        /* filling the hashtable  */
	            		      
	 	              	          RemplirCPUState("server1",CPU);
	 	            	          RemplirDISCState("server1",disc);
                         
	            	            }
	            	 
	            	          if (requ.getSender().getLocalName().equals("server2"))
	            	            {	
	            		          number ++ ;
	            	        	  Access [1]= true;
	            	        	String server2content [] = decoupers(requ.getContent());  
            	        	 
		                	   //  msgFromDBNiveau1 = server1content[0]   ;
		
		                	        	 String content2 = server2content[0];
		                	        	 System.out.println(content2);
		                	        	 CPU2 =    Float.parseFloat(server2content[1]);
		                	             disc2 = Float.parseFloat(server2content[2]);
		                	       
		                	            System.out.println(content2);
	                	             	System.out.println(CPU2);
	                	        	    System.out.println(disc2);
		                	              
		                	            String matrice2 [][] = haveRequest(content2);

		                	             System.out.println("******************************La matrice 2 ******************************************");
		                	             System.out.println("number = "+ number);
		                	             for (int j=0;j< matrice2.length-1 ; j++)
                	     	            {
                	                	 System.out.println(matrice2 [0][j]);
                	                	 System.out.println(matrice2 [1][j]);
                	                    }
		                	        	 
		                	             server2 = matrice2 ; 
		  	            		        /* filling the hashtable  */
		  	            		     
		  	 	              	          RemplirCPUState("server2",CPU2);
		  	 	            	          RemplirDISCState("server2",disc2);
		  	 	            	          

		            	
	            	            }
	            	           if (requ.getSender().getLocalName().equals("server3"))
	            	            {
	            	        	number ++;
	            	        	   Access [1]= true;
	            	        	String server3content [] = decoupers(requ.getContent());
	            	        	String content3 = server3content[0];
                	            CPU3=    Float.parseFloat(server3content[1]);
                	            disc3 = Float.parseFloat(server3content[2]);
                	        
                	           String matrice3 [][] = haveRequest(content3);
                	           System.out.println("*********************Matrice 3 ******************************");
                	            System.out.println("number = "+ number);
                	           System.out.println(content3);
                	        	System.out.println(CPU3);
                	        	System.out.println(disc3);
                	        	
                	        	  for (int j=0;j< matrice3.length-1 ; j++)
                	     	       {
                	                	System.out.println(matrice3 [0][j]);
                	                	System.out.println(matrice3 [1][j]);
                	               }
                	        	 
                	        	  server3 = matrice3;
  	            		        /* filling the hashtable  */
  	 	              	          RemplirCPUState("server3",CPU3);
  	 	            	          RemplirDISCState("server3",disc3);  
	            	            }    	          
  	 	            	        if ( number < 3 )
  	 	            	       //if(Access [0] == false || Access [1]== false || Access [2]== false)   
	            	           {
  	 	            	        	  block();

  	 	            	        }
  	 	            	       //if (Access [0] == true && Access [1]== true && Access [2]== true)  
  	 	            	       
  	 	            	      	if(number == 3)
  	 	            	        {
  	 	            	        		cases = 1;
  	 	            	        		System.out.println(" cases est devenu 1 ");
  	 	            	        }
  	 	            	           
  	 	            	        
  	 	            	        break; 
  	 	            	        
	            	            } 
  	 	            	          case 1:
  	 	            	        	  
  	 	            	          {    
  	 	            	        	  
  	 	            	        	System.out.println(" here ");
  	 	            	        	int i ;
  	 	            	        	number = 0;
  	 	            	        	
  	 	            	        	i= number2;
  	 	            	        	result =  service(file2[i]);
  	 	                              for(int j=0 ; j<result.length; j++) 
  	 	    		                         {
  	 	                                     System.out.println("result = "+result[j] );
  	 	    		                         }
  	 	       
  	 	                               String bestdistination =  BestServer (typedmd2[i] , result);
  	 	     
  	 	                              System.out.println("Bonjour, "+ file2[i] + " merci MR : " + bestdistination); 
  	 	                           number2  ++; 
	  	 	               	   	System.out.println("**********calling for request 111 **************************************************************");
 	
	  	 	               	      callingForRequest();
	  	 	               	      cases = 0;
	  	 	               	      /*
	  	 	               	      if(number2 == requets.length)
	  	 	               	      {
	  	 	               	    	  cases = 100;
	  	 	               	    	  System.out.println("OK that is all , al requests are done");
	  	 	               	    	  break;
	  	 	               	      }*/
	  	 	               	      break;
	  	 	               	      
	  	 	               	       
  	 	            	          }
	            	            }         
            
		            }  //req
		                                   	
                 
                    if( requ.getPerformative()== ACLMessage.INFORM)
            		{  
            		req = requ.getContent();
            	    
            		System.out.println("****************************calling for request 111 **************************************************************");
            		
            		
            		
        	   
                    System.out.println("reception du message"+ requ.getContent());
                    System.out.println("FROM :" + requ.getSender().getLocalName());
                    requets = ssm.decoupervir(req);
                    
                       
                    for(int j=0 ; j<requets.length; j++) 
                       {
                    	  System.out.println(requets[j]);
                         System.out.println(requets.length);
                         
                    	 String [] treq = decoupebar(requets[j]);   
                    	  System.out.println(treq[1]);
                    	 /*
                    	  typedmd2 =  ;
                    	  file2=   requets;
                    	  */
                    	  typedmd2[j]  = treq[1];
                    	  file2[j] = treq[2];
                    	 
                    	 System.out.println(" typedmd = "  + typedmd2[j] );
                         System.out.println(" file  = "+ file2[j] );
                       }
                    
                    for(int j=0 ; j<requets.length; j++) 
                    { System.out.println(requets[j]);System.out.println(requets.length);
                    }
                    callingForRequest();
		               }
		                  
	        /****************************************************************************************/    	 
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
		      /***************************************************************************************/            
		                            
		                  }
		             
		                            else
		  	            	          {
		  	            	        	 block(); 
		  	            	          }
		                       }

				
				});
				}
}
