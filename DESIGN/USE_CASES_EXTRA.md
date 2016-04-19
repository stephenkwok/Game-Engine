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

- An Actor (A1) speeds up by a given amount when the user presses the Right Arrow 
	 - Key A1 contains a Rule linking the Trigger KeyTrigger(KeyCode.RIGHT) to the Action 
	 - Accelerate(double accelerationForce)  
	 - Accelerate accesses the PhysicsEngine via the method applyAcceleration(A1) 
	 - This method calculates a new velocity based on the given acceleration force and sets A1’s velocity to this velocity  
	 - This occurs via A1.setVeloX(newVeloX)
   
- An Actor (A1) changes its image when the User clicks the mouse 
	 - A1 contains a Rule linking the Trigger ClickTrigger to the Action ChangeImage(Image) 
	 - When this Rule is created and placed in the Actor, it receives an image that is saved within the Actor as the variable nextImage 
	 - When the User clicks the mouse, this trigger is registered and handled  
	 - It’s handled via the game’s handleTrigger(Trigger) method 
	 - When called, the Action ChangeImage uses A1’s method setMyImageView(Image) to set A1’s ImageView to equal nextImage 
	 - When the GameEngine completes its cycle and sends the Actors back to GamePlayer, they will display the Actor with the updated ImageView
	 
- An Actor (A1) moves upwards at a constant rate when it collides with a PowerUp Actor
	 - A1 contains a Rule linking the Trigger SideCollision between itself and a PowerUp actor (a1SideCollisionpowerUp) to an Action GlideUp
	 - When a collision is detected within the CollisionDetection class, it will call the method A1.performActionfor(a1SideCollisionpowerUp) 
	 - A1 will then call the perform method within the GlideUp class 
	 - This method will increment A1’s y position by a set value

- User sets Actor size within actor editing environment
	- Within the actor editing environment, in the left pane containing Actor fields, the user types in a name within the Name: textfield.
	- User clicks “Go”, and this calls Actor.setName(String enteredString)
	- The textfield value is also set to the String that the user entered, so when the user goes back to edit this Actor, the previously set values will still be shown
	
- User sets Actor size w/in level editing environment
	- Within the level editing environment, double click on the actor
	- Dialog pops up with field for height, width, and preserve ratio.
	- User enters either a height or a width + preserve ratio, or a height and width.
	- Actor’s ImageView is updated and ActorIcons reflect change.
	
- User wants to edit Actor in the Level Inspector’s Actor Tab
	- User double clicks on Actor in the Actor Tab
	- On double click calls myController.goToActorEditingEnvironment(actor).

- User adds behavior (either trigger or action) to Actor rule
	- Within the actor editing environment of the authoring environment, in the right pane, a GridPane representation of an ActorRule is shown. 
	- The user can drag any Behavior tab element from the Library and drop it onto the GridPane of the ActorRule. 
	- If the Behavior is a trigger it will be added to the Trigger container, if it is an Action it will be added to the Action container. 
	- The Behavior will expand to some sort of Node that contains settable parameters for this Behavior (e.g. dragging ChangeSize to the rule will expand to a textfield that allows the user to enter the size that the Actor will change to). 

- User adds sound to Actor rule
 	- Within the actor editing environment of the authoring environment, in the right pane, a GridPane representation of an ActorRule is shown. 
 	- The user can drag any Sound tab element from the Library and drop it onto the GridPane of the ActorRule. 
 	- The sound element will automatically be added to the Action section of the rule, and it will expand to be a ComboBox containing the currently available sound/music files, with the initial value of the ComboBox set to the sound that was dragged over. 

- User adds image to Actor rule
	- Within the actor editing environment of the authoring environment, in the right pane, a GridPane representation of an ActorRule is shown. 
	- The user can drag any Image tab element from the Library and drop it onto the GridPane of the ActorRule. 
	- The image element will automatically be added to the Action section of the rule, and it will expand to be a ComboBox containing the currently available image files, with the initial value of the ComboBox set to the sound that was dragged over.

- User clicks finish button to finish editing game in authoring environment
	- When the finish button is clicked, the gamedata Saver will be called to save the current game.
	- Also, the Controller will return the user to the game player’s splash screen by setting the Stage’s Scene to the splash Scene.

- User wants to go from authoring environment back to splash screen
	- The user will click the button with the salad icon in the toolbar. 
	- A dialog will pop up asking the user if they are sure they want to return to the splash screen without saving.
	- If the user presses yes, the Controller will return the user to the game player’s splash screen by setting the Stage’s Scene to the splash Scene.
	- If the user presses no, the user will remain in the authoring environment. 

- User resizes level size
	- User enters height or width into the TextFields in the Level AttributesTab.
	- Level’s setWidth or setHeight method will be called
	- Canvas that represents the level’s bounds resizes its display.

- User changes background image of level
	- User clicks the change background image button.
	- Filechooser pops up to choose an image file.
	- Level’s setBackground() method is called to update its background actor.
	- Pane that represents the level updates its background actor’s imageview.

- User drags actor outside of level (into other elements of the GUI)
	- User drags actor outside of canvas boundaries.
	- Checks bounds and prevents it from being dragged any further

- User wants to re-order levels
	- User clicks Reorder Level button
	- Pop-Up is generated listing all the created levels, accompanied by a text field
	- User enters a number into each text field indicating when that level should be played (so if it should be played first, the user should enter 1 into the text field next to the first level)

- User changes game preview icon
	- User clicks "Change Game Preview Image" button
	- FileChooser is generated allowing user to upload new image
	- Method is called to set the Game’s preview image and display that updated image in the Game Editing Environment


- User clicks to replay the level
	 - The GameScreenController will observe the Replay button
	 - When this button is clicked, the controller notifies the Game
	 - The Game calls the initialize() method to set the game’s level to its current level and then begin()

- User clicks to clear high scores
	 - SplashScreenController will observe the HighScore button
	 - When this button is clicked, the controller instantiates a FileScreenController
	 - This new controller calls clearFile() in XMLEditor

- Character moves right, camera moves with it
	 - The GameController contains a camera that is set to the GameScreen (which is a subscene of BaseScreen)
	 - Camera observes the main character’s x position
	 - When the main character’s x position changes, camera’s x position will change

- Character moves at the end of level, camera stops
	 - When camera’s position changes, a base check is called that tests if camera’s x position + camera’s width surpasses a the subscene’s width - camera’s width, then camera’s position becomes final for this level

- Mute/unmute sound effects:
	 - GameController observes the sound effects button. In case the sound effects button is clicked, the GameController is notified. 
	 - The boolean (sfxOn) is flipped. 
	 - The GameController then gets the List<Actor> currentActors through the Game. Each Actor has a JukeBox instance, and if soundOn is true for that JukeBox instance, then the MediaPlayer inside that JukeBox will play.  
	 - soundOn is set to the value of sfxOn for each Actor in currentActors.

- Mute/unmute music:
	 - GameController observes the music button. In case the music button is triggered, the GameController is notified. 
	 - The boolean (musicOn) is flipped.  
	 - The GameController then gets the List<Level> levels through the Game. 
	 - Each Level has a JukeBox instance, and if soundOn is true for that JukeBox instance, then the MediaPlayer inside that JukeBox will play.  

- Show data from multiple main characters in HUD:
	 - Instead of a single Actor mainCharacter, the HUD will have a List<Actor> mainCharacters that will all be observed and update the values of HUDData if anything changes.

- User toggles timeline pause/play: 
	 - GameController observes the play/pause button. 
	 - When the button is clicked, the GameController is notified and flips the value of its isPlaying field.  
	 - Depending on that value, it either calls the togglePause() or toggleUnPause() methods.

- Infinite scrolling background: 
	 - Use the JavaFX Animation feature TranslateTransition that spans a certain duration that controls how fast the background will scroll.  
	 - Over that duration, the image will translate across an X range determined by setFromX(-backgroundImageViewWidth) and setToX(stageWidth).   
	 - Because this TranslateTransition will run over an indefinite Timeline cycle count, the image will continue to wrap around back to its start from position and translate across indefinitely. 

- Vertical scrolling: 
	 - From GameScreen on handleScreenEvent(e), handleKeyPress(e) will determine that the ITrigger is a KeyTrigger and evaluate(actor) based on the key code to trigger a CameraUp Action.  
	 - The CameraUp Action’s perform() notifies the GameController to set off the GameScreen’s changeCamera(0, DEFAULT_Y_MOVEMENT)

- In Doodle Jump, actor falling doesn’t move camera: 
	 - From GameScreen, on handleScreenEvent(e), handleKeyPress(e) will determine that the ITrigger is a KeyTrigger and evaluate(actor) based on the key code to trigger a MoveDown Action but not a CameraDown Action. 

- Making sure main character doesn’t go off screen: 
	 - For any Move___  Action of the main actor, ensure there is a simultaneous Camera___  Action.  
	 - If Camera has settled all the way at the “end” of the GameScreen’s subscene and no longer move, then notify GameController to invoke endSceneReached().   
	 - In endSceneReached(), GameController will set checkBounds = true.   
	 - In the step() called by the Game’s timeline, if checkBounds == true, will check that main actor’s getX() % Controller’s GameScreen’s getScene().getWidth() < getScene().getWidth().  
	 - If so, will not let main actor’s x increase.
