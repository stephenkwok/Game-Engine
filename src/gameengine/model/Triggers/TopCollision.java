package gameengine.model.Triggers;

import gameengine.model.Actor;
import gameengine.model.IActor;
import gameengine.model.IPlayActor;

public class TopCollision extends CollisionTrigger{

    private static final String COLLISION_TYPE = "TopTrigger";

    public TopCollision(IPlayActor actor1, IPlayActor actor2) {
        super(actor1, actor2);
    }

    @Override
    public boolean evaluate(ITrigger otherTrigger) {
        TopCollision otherCollision = (TopCollision) otherTrigger;
        return this.equals(otherCollision);
    }

    @Override
    public String getMyKey() {
        return COLLISION_TYPE;
    }

}