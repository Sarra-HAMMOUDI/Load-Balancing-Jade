package LectureEcritureFichier;

import jade.core.Runtime;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class  VideoContainerNiveau2Server3{

	public static void main(String[] args) {
		try {
		Runtime rt = Runtime.instance();
        ProfileImpl pc = new ProfileImpl(false);
        pc.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer ac = rt.createAgentContainer(pc);
		AgentController serveur3 = ac.createNewAgent("server3","LectureEcritureFichier.VideoDBNiveau2Server3", new Object[]{});
		serveur3.start();	
	
							
    	} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
