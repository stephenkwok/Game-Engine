package gameengine.model.Triggers;

import gameengine.model.IPlayActor;
import gameengine.model.ITrigger;
import javafx.scene.input.KeyCode;

public class KeyTrigger extends ITrigger {

    private String myKey;

    public KeyTrigger(KeyCode key) {
        setMyKey(key.getName());
    }

    public String getMyKey() {
        return myKey;
    }

    public void setMyKey(String myKey) {
        this.myKey = myKey;
    }

    /**
     * Checks a boolean condition against the state of an actor
     *
     * @param otherTrigger A trigger to check information against
     * @return A boolean that says if the condition is true or false
     */
    @Override
    public boolean evaluate(ITrigger otherTrigger) {
        return true;
    }
}
