package LectureEcritureFichier;
import jade.core.Runtime; 
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class  VideoContainerNiveau1 {

	public static void main(String[] args) {
		try {
		Runtime rt = Runtime.instance();
        ProfileImpl pc = new ProfileImpl(false);
        pc.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer ac = rt.createAgentContainer(pc);
        
		AgentController VideoAnnuaire= ac.createNewAgent("VideoAnnuaireNiveau1","LectureEcritureFichier.VideoAnnuaireNiveau1Version5", new Object[]{});
	   AgentController VideoSupervisor= ac.createNewAgent("VideoSupervisorNiveau1","LectureEcritureFichier.VideoSupervisorNiveau1", new Object[]{});

		
    	
		VideoAnnuaire.start();
	   VideoSupervisor.start();
		
							
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
