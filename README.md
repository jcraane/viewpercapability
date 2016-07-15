# viewpercapability
Demonstrates the View Per Capability pattern for Android applications

## Introduction

The goal of the view-per-capability pattern is to avoid any kind of isTablet() or isPhone() related actions by putting the device 
specific behavior in separate objects. This increases readability and maintainability since any single class
only deals with the device-specific characteristics.

This pattern does not make any assumptions of how particular views are populated. In this example
the model objects are passed to the views directly after which the view updates the corresponding widgets.
You could also use data-binding, a seperate view model or any other mechanism that suits the application at hand.

This application does use the EventBus but it could just as easily use regular listener interfaces.

A dependency injection framework is not used in this example but could be easily added as well.

## Implementation

The top-level activity, in this case OverviewActivity loads a single view, the MovieMediator. The MovieMediator 
is an interface with two implementations at the moment: MoviePhoneMediator and MovieTabletMediator.

The MoviePhoneMediator defines the phone specific layout and behavior and the MovieTabletMediator defines the tablet specific layout and behavior.
 
The mediator acts as a mediator between the various components that make up the view, it takes over 
the responsibility from the activity. The purpose of the activity is to load the mediator view
and forward any life cycle methods it may need.

The idea is to use a different composition of views for the phone and tablet layouts. Therefor it is important
to structure the views in such a way so that it allows for easy composition. In the example application
the views MovieList and MovieDetails views are defined. MovieList just displays a list of movies and MovieDetails
displays the details of a single movie.

### MoviePhoneMediator

The MoviePhoneMediator inflates the layout/mediator_overview defines only the MovieList view which is displayed as a single view
with a list of movies on a phone. When the user taps on a movie, the MovieSelectedEvent is dispatched which
is handled by the MoviePhoneMediator. The MoviePhoneMediator starts the DetailsActivity and passes the
selected movie using an intent. The DetailsActivity then displays the details of the selected movie.

The DetailsActivity defines the MovieDetails view.

### MovieTabletMediator

The MovieTabletMediator inflates the layout-w670dp/mediator_overview defines both the MovieList and MovieDetails view with the 
MovieList on the left and the MovieDetails next to it. When a user taps on a movie, the MovieSelectedEvent
is handled by the MovieTableMediator which updates the MovieDetails view.

When the tablet view starts for the first time, by default shows the details of the first movie in the list.

## Application state

Which component is responsbile for managing application state? This can be done in a variaty of ways. The pattern I prefer at the moment is to let top-level components manage state and pass this through to their child components. ReactJs also uses this principle. This has a couple of advantages:

- It is clear where in the application state is managed. Only top-level components.
- Child components are more re-usable because they do not have to concern about handling state.

### How do other components react to changes in child/parent components?

Obviously this can be done in a variety of different ways: TODO

## What about fragments?

The view-per-capability pattern does not make use of fragments. Fragments, like activities, are life-cycle
components. The mediator is called by the containing activity for any life-cycle events the mediator
may be iterested in. For example, the onResume of the activity to register an EventBus. By following this
pattern, the need for fragments is gone which reduces complexity.

## Code style

Code style is matter of preference. What I do find helpful is the following rule:

Make sure all classes/components/views in your entire applicatie do not EXCEED 200 lines of code.

This rule is created over time by writing al lot of code and finally settling on something
simple that works. This way you have to thing about decomposing your application in re-usable
components.