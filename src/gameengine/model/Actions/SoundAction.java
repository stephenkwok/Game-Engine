package gameengine.model.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import gameengine.model.Actor;
import gameengine.model.IGameElement;

public class SoundAction extends Action{
	
	private String soundFile;
	
	public SoundAction(IGameElement assignedActor, String soundFile) {
		super(assignedActor);
		this.soundFile = soundFile;
	}

	@Override
	public void perform() {
		getGameElement().changed();
		List<String> send = new ArrayList<>();
		send.add("playSound");
		send.add(soundFile);
		((Observable) getGameElement()).notifyObservers(send);
	}

	@Override
	public Object[] getParameters(){
    	return new Object[]{getGameElement(), soundFile};
    }
	
	public String getSoundFile(){
		return this.soundFile;
	}

}
