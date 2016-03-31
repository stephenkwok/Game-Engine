# VOOGASalad Use Cases - The Loop's Goat Cheese Salad

*Justin Bergkamp, Michelle Chen, Michael Figueiras, Blake Kaplan, Stephen Kwok, Annie Tang, Carine Torres, Colette Torres, Bobby Wang, Amy Zhao*

##### 1. User adds a new actor

  User clicks a ‘New Actor’ button in the MainScreen → onAction it will create a new CreatedActor and add it to the list of actors in the MainScreen, then setActor(newActor) for the Actor Editor, then transition to the ActorEditor

##### 2. User drags actor to change position

In the LevelEditor, the user can drag the actor’s image → onMouseDrag will move the actor’s position in response.

##### 3. User adds a new rule to an actor so that W moves the actor up

In the ActorEditor, the user can click a ‘New Rule’ button → onAction will create a Popup where the user can choose ‘Press key’ for the Trigger, type in ‘W’ for the key, and select ‘Move up’ for the action, which will correspond to ‘PressKey’ in the triggers.properties file, and ‘Up’ in the actions.properties file. → This Rule will be added to the CreatedActor’s RuleMap with ‘PressKey,W’ as the key and ‘Up’ as the value.

##### 4. User deletes a rule

In the ActorEditor, the user clicks ‘Remove Rule’ → a ChoiceDialog pops up displaying all existing rules, and the selected one is removed from the map.

##### 5. User edits actor name

In the Actor editing environment, under “Attributes” (which will be represented using a TableView), the user can click on the Name cell and type in a String→ on enter,  will call updateNode() on the TableView object and update the TableView table variables.

##### 6. User saves state of design

From the toolbar in the user interface, at any point the user can press the “Save” button, and this will call a Parser/Saver from backend (game data section) to extract information from existing Levels and Actors using getLevelInfo() and getActorInfo(). The information will be saved in a predefined XML format.

##### 7. User returns to main screen

The user will click the home button on the toolbar → onAction will call MainScreen.show(), and the main screen will be viewable.

##### 8. User tries to load .jpg file to sound library

In either the scene editing environment or the actor editing environment, the user will click the “+” button to load a sound file into the library → onAction will open up a prompt to enter the filename including extension → on enter, there will be error checking to verify that the file extension is valid, i.e. is an mp3 → since the file extension is not valid, there will be an Alert dialog that pops up to inform the user that they have entered an invalid file format.

##### 9. User selects already created level to edit
Main Screen displays all created levels as a series of clickable labels in a ScrollPane. User then clicks on label corresponding to level to edit → level to edit is passed to LevelEditingEnvironment via the LevelEditingEnvironment setLevel(ILevel level) method
Game Authoring Environment switches scene from MainScreen to LevelEditingEnvironment

##### 10. User adds a new level
User clicks on “Create New Level” button on MainScreen → new instance of Level instantiated and passed to LevelEditingEnvironment through setLevel() method

##### 11. User adds an actor to a level

Created actors are displayed in LevelEditingEnviroment
User drags and drops actors (gesture source)  into the scene (gesture target)

##### 12. User removes actor from level
User drags actor to be removed into “Trash” area
Trash area detects actor in its space and makes call to removeActor(IActor actor) to remove the actor from the Group

##### 13. User loads a background image for the level.

 User then clicks on “Load Background Image” button in LevelEditingEnvironment → FileChooser enables user  to select image to upload. The selected image is added to Group to be displayed

##### 14. display dynamically updated game status information

In the HUDScreen, the update() method will change the x position of the icon’s location to reflect the change in player’s location. This will be accomplished through an Observer/Observable relationship between the two that will not involve the GameLoop.

##### 15. User clicks to replay their current level

User clicks the “circular arrow icon” to restart the level -> onAction invokes game.playAt(currentLevel) -> GameLoop is reinitialized along with level actors

##### 16. User clicks to pause the game

User clicks the “||” button on the game screen -> the GameLoop stop() method is invoked which halts the animation timeline.

##### 17. User switches game without quitting

User selects an item from “New Game” combobox, calling the switchGame() method -> onAction will endGame() through the GameLoop and create a new Game based on their choice, then initialize(0) on what they selected.

##### 18. User saves high score after the game ends

When endGame() is called, GameScreen will then saveHighScore() which creates and prompts an alert to save the current score only if the player has reached a high score.  If the alert is prompted, the user will click ‘OK’ —> onAction will overwrites information in HighScores.xml only if the player has reached a high score.

##### 19. Starting the game from main menu screen

On an implemented ISplashScreen, the user will hover over ‘Play’ —> onAction play() is called which instantiates a Menu of options of available games for playing. The games will be represented by an image preview of the game.  When one of the game options is clicked —> onAction a new instance of GameXMLParser is instantiated to read through the XML file and that information gets passed to instantiate a new IGame from the Game Engine.  That IGame will be instantiated with a list of levels taken from IGameParser’s getLevels() and then the current level to be played is set with setLevel(myLevel).

##### 20. Pressing restart mid-game to reset game to level 0 (the start)

On the GameScreen implementing IButtons, user clicks on ‘Restart’ —> onAction it will pauseGame(), and start the game loop over by calling the game’s implemented GameLoop function initialize(0).  initialize(0) clears the scene on the stage and uses the GameScreen’s addActor(actor) to add every visualization of an IActor passed from getActors() on the game’s level 0 (an instance of ILevel).

##### 21. Saving a player’s progress
On the GameScreen implementing IButtons, user clicks on ‘Save Progress’ —> onAction it will pauseGame(), create an instance of GameXMLCreator then writeLevelInfo(level) for the current level, writeActorInfo(actor) for all actors in that current level, and writeInitialFile() for the game.  

##### 22. Enter level editing mode from main splash screen

On an implemented ISplashScreen, the user will hover over ‘High Scores’ -> onAction openEditor() is called which instantiates and switches the scene to the game authoring environment.

##### 23. View high scores

On an implemented ISplashScreen, the user will hover over ‘High Scores’. OnHover, openHighScores() is called, opening a menu of options of games which have saved high scores. Each game will show its highest score next to it. Each game is its own button -> onHover openIndividualHighScore() is called, which opens up an entire list of high scores.

##### 24. Clear high scores

Similarly to the view high scores use case, each game will show its highest score next to it, and onHover for each game, openIndividualHighScore() is called, opening up the entire list of high scores. The last entry of the list of high scores is a clickable button. OnClick of this button, clearHighScore() is called, which clears the high score list of that particular game.

##### 25. Muting/enabling music/sfx

On the main gameplay screen, there will be two buttons in the top right corner - one which looks like a loudspeaker (for sfx) and one that looks like a music quarter note (for music). OnClick of the loudspeaker, toggleSFX() is called, which tells the game engine to mute/unmute sound effects. OnClick of the music note, toggleMusic() is called, which tells the game engine to mute/unmute music.
Return to main menu
On the main gameplay screen, the leftmost button in the top of the screen will be labeled “menu.” OnClick of this button, pauseGame() and returnToMain() will be called. The second function sets the scene back to the main implemented ISplashScreen.

##### 26. Game character passes through pipe in Flappy Bird game

The game engine’s update() method checks for collisions and detects collision between character actor and invisible point actor. A collision trigger signals changePoints() action. The character’s points are then incremented

##### 27. Game character collides with pipe in Flappy Bird game

The game engine’s update() method checks for collisions and detects collision between character actor and pipe actor. A collision trigger signals changeHealth() action and the character’s health is decremented

##### 28. Game character reaches destination end of level in Super Mario Bros.

The character collides with invisible destination object and a collision trigger signals a LevelChange action to be performed.

##### 29. User loads new level

In the Game class, the program calls the setLevel() method. This obtains a list of strings from the Map <Level, List<Strings>> in the Game class. For each string, a list of actors is obtained via the Map<String, List<Actor>>. A Level object is created with this list of Actors.

##### 30. Restart game

The Game player creates a new IGame object from data file

##### 31. Game character collides with obstacle

The game engine’s update() method checks for collisions and detects collision between character actor and enemy actor. A collision trigger pulls actor strength and evaluates stronger actor. Based on result, one type of collision (eg BenignCollision) passed to actor’s action and executed.

##### 32. Game character weapon collides with enemy actor

Weapon creation trigger signals CreateWeapon() action to make weapon actor for character. Game engine’s update() method checks for collisions and detects collision between weapon actor and enemy actor. Collision trigger pulls actor strength and evaluates stronger actor. Based on result, one type of collision (eg KillCollision) passed to actor’s action and executed.

##### 33. Game enemies simultaneously move
UI event interpreted from game player to levels trigger to AAA map. The Action is executed for all affected actors through usage of AAA map.

##### 34. Game player dies

The game engine’s update() method checks for collisions and detects collision between character actor and enemy actor. A collision trigger pulls actor strength and evaluates stronger actor. Based on result, one type of collision (eg KillCollision) passed to actor’s action and executed. The Player’s health is then decremented.

##### 35. The Game character moves forward
The game engine receives UI event from game player (eg right arrow key pressed). Utilizing the trigger to AAA map, the appropriate evaluate methods are called on the corresponding actors (in this case the character)--
eg: character.performActionFor(forwardTrigger)

##### 36. Game character moves backwards

Game engine receives UI event from game player (eg right arrow key pressed). Utilizing the trigger to AAA map, the appropriate evaluate methods are called on the corresponding actors (in this case the character)--
eg: character.performActionFor(backwardTrigger)

##### 37. New Level is loaded with same Main Character Health

Once the player has reached the winning condition, in the Game class the Actor map List<String, Actor> is updated to reflect the current attributes of Actors that are common between multiple levels.
Then a new Level object is created with the appropriate (and updated) list of Actors via the Map<Level, List<String>> and List<String, List<Actor>>

##### 38. Restart Level

Game engine sends user back to start of level, resetting all actor positioning by calling .resetPosition() on each actor but keeping number of lives the same

##### 39. Game character shrinks
Game engine receives UI event from game player (eg down arrow key pressed)
Utilizing trigger to AAA map, the appropriate evaluate methods are called on the corresponding actors (in this case the character)--
eg: character.performActionFor(shrinkTrigger)
Character ImageView updated with shorter width

##### 40. User tries to rename actor in authoring environment

The user will click the 'Rename' button and a pop-up will prompt the user to enter the name, which will update the myName field in the actor.
