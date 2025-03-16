package LectureEcritureFichier;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.leap.*;
import jade.wrapper.AgentContainer;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.util.*;

public class Maincontainer {

	public static void main(String[] args) {

		try {
			Runtime rt =  Runtime.instance();
					
					
     		Properties p = new ExtendedProperties();
			p.setProperty("gui","true");
			ProfileImpl pc= new ProfileImpl(p);
			
			
			AgentContainer container =rt.createMainContainer(pc);
			container.start();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
