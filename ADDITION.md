## SLogo Addition
**Amy Zhao (abz3)**

*The additional features I added were a `Wrap` command and a `Fence` command.*

### Estimation

Before looking at the old code, I think it could take up to 2 hours to implement these new features because although adding new commands is simple, our canvas behavior had been written entirely by our front-end team so I feel like it could be difficult to identify where and what changes needed to be made. Adding commands that manipulate front-end attributes can also be tricky.

I think I would have to at least update our `GUICanvas` class, add two new classes for `Wrap` and `Fence`, and also update the properties files used by our parser so that it can add these new commands into a command tree correctly.

### Review

It ended up taking only about an hour to add these new features, although I had to update more classes than I had anticipated. As far as our front-end classes go, I only had to add a few methods into the `GUICanvas` class. Obviously I also had to write the two new `Wrap` and `Fence` classes, but I found that I could extend `DisplayNode`, which is the parent class for all commands that manipulate a front-end feature and require a reference to the `GUICanvas`. When working on SLogo, our front-end had made the `GUICanvas` the single class that our `Controller` could pass to our back end to update, making it easy for me to add features that manipulate the canvas. However, I also had to change all of the other `DisplayNodes` `interpret()` methods to return an arbitrary value rather than just the value that was used to update the canvas so as to accommodate the requirement that `Wrap` should return 1 and `Fence` should return 3.

I also created an enum for `Bounds` and a properties file for bounds so that other types of bounds can easily be added in the future. This properties file is used so that the name of the enum can easily be converted into the method name for checking either the minimum bound or maximum bound, allowing for use of reflection.

On my first try, there were some adjustments that needed to be made to get the reflection to work exactly right, but once that was fixed it appeared to function as expected.

### Analysis

I think our back end design was good in that it was easy to add in a new command, although looking back on it, it was probably not a good choice to have our model (`DisplayNodes`) directly edit our view (`GUICanvas`). Our `GUICanvas` class could also have been improved, as it's not only an extremely long class, but it's also hard to consider all of its methods and functionality as front-end features even though we placed it in the view package.

We could have improved our design by making a `Canvas` in the back-end so that the `GUICanvas` could be solely responsible for rendering the `Canvas` and the turtles rather than also considering logic like bounds-checking. This way, our `DisplayNodes` could also edit the `Canvas`, which would be part of the model, rather than directly changing the view.

If I was not familiar with the code, I think it would have been difficult for me to know which properties files to update since these are not described thoroughly in our README. It also would have been difficult for me to know where the canvas bounds behavior was being prescribed if I hadn't spent a good amount of time working together and communicating with the front-end half of our group.
