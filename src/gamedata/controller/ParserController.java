package gamedata.controller;

import java.io.File;

import gamedata.XMLParser;
import gameengine.controller.Game;

public class ParserController implements IParserController {

	private XMLParser myXMLParser;

	public ParserController() {
		this.myXMLParser = new XMLParser();

	}

	@Override
	public Game loadForEditing(File file) {
		Game playingGame = loadforPlaying(file);
		if (playingGame == null) {
			return null;
		}
		else {
			File editingFile = new File(playingGame.getInitialGameFile());
			return loadforPlaying(editingFile);
		}
	}

	@Override
	public Game loadforPlaying(File file) {
		Game XMLgame = (Game) this.myXMLParser.load(file);
		if (XMLgame == null) {
			return null;
		}
		else {
			return new Game(XMLgame.getInitialGameFile(), 
					XMLgame.getLevels(), 
					XMLgame.getInfo(),
					XMLgame.getMyPhysicsEngine(),
					XMLgame.getMyCollisonDetector(),
					XMLgame.getActiveTriggers(),
					XMLgame.getAnimation(),
					XMLgame.getCurrentActors(),
					XMLgame.getDeadActors(),
					XMLgame.getCount());
		}
	}

}
