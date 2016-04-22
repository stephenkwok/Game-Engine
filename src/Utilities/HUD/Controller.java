package Utilities.HUD;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Changed!");
	}
	
	
}
