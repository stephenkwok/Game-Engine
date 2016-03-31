package gameplayer.game;

public interface GameLoop {
	
	public void initialize (int level);
	
	public void begin ();
	
	public void update ();
	
	public void checkInteractions ();
	
	public void cleanUp ();
	
	public void stop ();

}
