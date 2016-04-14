 - Main character jumps in the air when space bar is clicked
	 - GameScreen detects a key press, creates a KeyPress trigger instatiated with KeyCode.SPACE and notifies GameController, passing it the KeyTrigger trigger
	 - GameController receives a notification to handleTrigger(KeyTrigger(SPACE)), so it tells Game to handleTrigger(KeyPress(SPACE))
	 - Game tells the current level to handleTrigger() on a KeyTrigger of type SPACE
	 - Level retrieves the list of actors with a SpaceKeyTrigger trigger from its myTriggerMap and for each of them, performsActionsFor() the space key trigger

 - Main character walks into block and bounces off of it Main character
	 - actor must contain a Rule mapping a SideCollision (instatiated with mainCharacter and block) trigger to a HorizontalBounceCollision action In Game class, collisionDetector detects collision between main character actor and block actor  
	 - In CollisionDetection class, side collision is detected and signals main character actor’s performActionsFor() method for a side collision between the maincharacter actor and the block actor 
	 - The main character performs the HorizontalBounceCollision action to bounce backwards off the block
	  

 - Main character walks on floor and doesn’t fall through it 
	 - Main character actor must contain a Rule mapping a BottomCollision (instantiated with mainCharacter and floor) to a VerticalStaticCollision action
	 - In Game class, collisionDetector detects collision between main character actor and floor actor 
	 - In CollisionDetection class, a bottom collision is detected and signals the main character’s performActionsFor() method for a bottom collision between the main character actor and the floor actor
	 - The main character performs the VerticalStaticCollision so that actor stops on top of the floor and neither crashes through it nor bounces off of it

 - Main character jumps onto an enemy and destroys it 
	 - Enemy actor must contain a Rule mapping a TopCollision (instantiated with enemy and mainCharacter) trigger to a Destroy action Main character lands on top of enemy from a jump, and in the Game class, collisionDetector detects collision between the enemy actor and main character actor 
	 - In CollisionDetection class, a top collision is detected and signals the enemy’s performActionsFor() method for a top collision between enemy and mainCharacter The enemy performs the Destroy action so that its isDead field is set to true

 - Actor is removed from the game  
	 - Destroy action must have set an actor’s isDead field to true 
	 -  In the Game class, updateActors() iterates through the game’s current actors and finds that the actor’s isDead() method returns true, so adds it to the list deadActors 
	 - removeDeadActors() is called, notifying GameController to updateActors 
		 - In GameController, updateActors() is invoked, so it tells the view to remove everything contained in the Game class’ deadActors list by removing their ImageView’s from the scene’s root 
		 - Back in Game, dead actors are then removed from game’s current level’s list of actors, then deadActors is cleared because they have been removed

 - New Attribute Made
	 - Attribute creation signature is `new Attribute(AttributeType type, int initialValue, int TriggerValue, Action action)`
	 - `Actor.addAttribute(Attribute newAttribute)` adds the new Attribute to the Actor
	 - Within the Actor, `getAttributeMap().put(newAttribute.getMyType(), new Attribute)` puts the new Attribute object into its proper place for easy reference

 - Actor1 Side Collision with actor2 causes actor1’s points Attribute to increase by 10
	 - Assume actor1 has an attribute of type AttributeType.POINTS
	 - The Level receives a SideCollision Trigger
	 - `Trigger.getMyKey()` returns “actor1SideCollisionactor2”
	 - `Level.handleTrigger(ITrigger myTrigger)` retrieves the list of Actors that respond to this trigger, which, in this case, include actor1
	 - `actor1.performActionsFor(myTrigger.getMyKey)` gets the action response for “actor1SideCollisionactor2”
	 - Within actor1 there is a rule mapping “actor1SideCollisionactor2” to ChangeAttribute(actor1, AttributeType.POINTS, 10)
	 - `ChangeAttribute.perform()` makes the change to the Attribute

 - Points Attribute in actor1 currentValue equals target value causing WinGame Action 
	 - Assume actor1 has an Attiribute of type AttributeType.POINTS that has a current value of 90, a target value of 10, and an Action of WinGame 
	 - Assume a trigger has just caused a `changeAttribute(actor1, AttributeType.POINTS, 10)` call
	 - Within`Attribute.changeAttribute(int change)` the initialValue equals the target value 
	 - The Attribute calls `myAction.perform()`, which calls
   the `WinGame.perform()`
	 - The WinGame Action executes

 - A projectile actor is launched from Actor when the User presses the SpaceBar
  
	 - Main character contains a Rule mapping KeyTrigger(KeyCode.Space) to the Action called SpawnActor
	 - This action creates a new Actor via the Actor constructor 
	 - This action will also add a Rule to the Actor that links the TickTrigger to the Glide(Right/Left) Action. It does this via the addRule method 
	 - This actor will also have a rule linking the SideCollision Trigger to the Destroy Action 
	 - The action will also need to add the created Actor to the List of Actors maintained in Level 
	 - Every time the step in animation timeline is called, this projectile will move horizontally at a set velocity

 - An Enemy Actor dies when it collides with a ProjectileActor 
	 - This Enemy Actor contains a Rule linking a SideCollision between Projectile and Enemy to ChangeAttribute.  
	 - ChangeAttribute will update the Enemy Actor’s attribute AttributeType.HEALTH 
	 - This attribute is linked, via an Observer/Observable framework, to the Action destroy
	 - If the projectile decrements the Enemy Actor’s health by enough to cause it to reach 0, the Attribute will notify the Destroy action 
	 - The Destroy Action will cause the Enemy Actor to be removed from the List of Actors being maintained in the Level class.

 - Main character does not fall off the screen 
	 - Every cycle through the update method in the physics engine, the method bound() called in physics engine so that if the new character position is less than 0 its position is reset to 0
