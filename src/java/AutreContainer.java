package LectureEcritureFichier;
import jade.core.Runtime;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class  AutreContainer {

	public static void main(String[] args) {
		try {
		Runtime rt = Runtime.instance();
        ProfileImpl pc = new ProfileImpl(false);
        pc.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer ac = rt.createAgentContainer(pc);
        
		AgentController AutreAgent= ac.createNewAgent("AutreAgent","LectureEcritureFichier.AutreAgent", new Object[]{});
		AutreAgent.start();		
							
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
