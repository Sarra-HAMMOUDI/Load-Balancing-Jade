package LectureEcritureFichier;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class VideoContainerNiveau2Server2 {


		public static void main(String[] args) {
			try {
			Runtime rt = Runtime.instance();
	        ProfileImpl pc = new ProfileImpl(false);
	        pc.setParameter(ProfileImpl.MAIN_HOST, "localhost");
	        AgentContainer ac = rt.createAgentContainer(pc);
			AgentController serveur2 = ac.createNewAgent("server2","LectureEcritureFichier.VideoDBNiveau2Server2", new Object[]{});
			serveur2.start();	
		
								
	    	} catch (ControllerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

