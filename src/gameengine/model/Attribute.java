package gameengine.model;

import gameengine.model.Actions.Action;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public abstract class Attribute {

    private String myName;
    private IntegerProperty myInitialValue;
    private IntegerProperty myTriggerValue;
    private Action myAction;
    private BooleanBinding isActionTriggered;

    public Attribute(String name, int initialValue, int triggerValue, Action action) {
        myName = name;
        myInitialValue = new SimpleIntegerProperty(initialValue);
        myTriggerValue = new SimpleIntegerProperty(triggerValue);
        myAction = action;
        isActionTriggered = myInitialValue.isEqualTo(myTriggerValue);

        initAttribute();
    }

    public void changeAttribute(int change) {
        myInitialValue.setValue(myInitialValue.getValue() + change);
    }

    private void initAttribute() {
        isActionTriggered.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal,
                                Object newVal) {
                myAction.perform();
            }
        });
    }

    public String getName() {
        return myName;
    }

}
