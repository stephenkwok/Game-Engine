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
* Doodle Jump
	* With regards to the design of the game data aspect, our XML saver will still allow for the functionalities specific to Doodle Jump.  To address the behaviors particular to Doodle Jump (i.e. a collision with other actors doesn't mean "game over" like it does in Flappy Bird...a player landing on one of the actors actually means the opposite, that the game should continue), merely, the rules property file referred to in an actor's section in the XML file will simply be different for Doodle Jump versus Flappy Bird or Mario.  That rules property file would also catch the unique scrolling of Doodle Jump that has movement occur with "up" and "down" keys because those would be the keys corresponding to movement actions in the file (rather than "left" and "right" keys, say). The XML creator only writes in a filepath given by the authoring environment, so the avoidance of hardcoding of a file allows for an acknowledgement of behaviors specific to Doodle Jump.  The design choice to use property files to connect interactions with executionable behaviors also facilitates that flexibility because reflection can then be used to generically execute game behaviors regardless of the actual TriggerEvent/Action pair.  The unique background for Doodle Jump would be acknowledged in the establishment of a "background-image" section in each level's section created by the XML creator too.
	* Our design of the game player also facilitates Doodle Jump's unique functionalities. In particular, the infinite scrolling capability is supported by the game player's design inclusion of a game loop...
	


## Design Considerations