package gamedata.controller;

public enum ChooserType {

	PLAY("Play"), EDIT("Edit"), SCORES("Scores");

	private String myName;

	private ChooserType(String name) {
		this.myName = name;
	}

	public String toString() {
		return this.myName;
	}

}
