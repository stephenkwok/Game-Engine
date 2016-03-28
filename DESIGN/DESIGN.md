# VOOGASalad Design - The Loop's Goat Cheese Salad

Justin Bergkamp, Michelle Chen, Michael Figueiras, Blake Kaplan, Stephen Kwok, Annie Tang, Carine Torres, Colette Torres, Bobby Wang, Amy Zhao

## Introduction

**Problem:** 

**Primary Design Goals:** 

**Closed:** 

**Open:** 

**High Level Description:** 

## Design Overview


## User Interface


## Design Details

## Example Games

**Flappy Bird**

In the game Flappy Bird, the user attempts to dodge obstacles by moving an actor up and down as the screen scrolls horizontally until a collision occurs between the actor and an obstacle. The game authoring environment allows for this type of game by giving the author the option to generate an infinitely-scrolling scene within the level editing environment. The author then has the option of dragging and dropping actors created in the actor editing environment into that scene or level. The author may also decide how frequently each actor appears on that level (as it would be impossible for the author to place obstacles indefinitely). Each of the actors will come with a set of defined rules for responding to given triggering events (this will be implemented as a Rules class with a TriggerEvent that results in the invoking of an Action). In the case of Flappy Bird, the actors to be created will include the user-controlled player and obstacles such as pipes. Each obstacle will likely have a single Rule, stating that whenever the TriggerEvent Collision occurs between the user and the obstacle, the GameEnd Action is invoked. For the user-controlled player, a Rule specific to that actor may be that whenever the trigger event KeyPressUp occurs, the MoveUp Action is invoked. Any other responses to events such as mouse clicks, key presses, or collisions, can be added to an Actor’s list of rules in this way. Then, when the game runs, each game loop will involve iterating through each actor’s list of Rules to check for TriggerEvents and making any responses happen as needed. 

**Super Mario**

In the game Super Mario, the user traverses a predefined scene until he or she reaches some destination that enables him or her to advance to the next level. Along the way, the user must break down obstacles, defeat enemies, and watch out for traps. Super Mario differs from Flappy Bird in that the scene does not scroll indefinitely. Rather, there is a set ending point and every obstacle on a given level must be added manually by the author. The author will be given the option to build whole levels in the level editing environment. From there, creation of the scene is exactly the same as it is for Flappy Bird. The level editing environment will display actors the author has already created, and those actors simply need to be dragged and dropped into the desired location on the scene. One difference between Super Mario and Flappy Bird is that Super Mario consists of multiple levels instead of just one. To accommodate this, the Game Authoring Environment will have a Main Screen that allows the author to see all currently created levels and actors. By clicking on any level, the author will then be sent to the level editing environment to edit the level he or she clicked on. The Main Screen will also have buttons allowing the author to create new levels or actors. Having multiple levels also means the Game Engine needs some way of knowing when to switch scenes. To account for this, the author can define some actor that serves as the target destination which has the Rule that when the user and target collide, the NextLevel Action is invoked. 

**Doodle Jump**

Doodle Jump is similar to Flappy Bird in that both require infinite scrolling. However, Doodle Jump scrolls vertically instead of horizontally, which the Game Authoring Environment will enable. Another difference is that projectiles are present in Doodle Jump. To account for this, the actor editing environment will allow authors to create the projectile with attributes and Rules as with any other actor. However, the actor will not be dragged and dropped into a scene in the level editing environment. Rather, the initial position of the projectile will be determined by the Game Engine while the game is running.




## Design Considerations
