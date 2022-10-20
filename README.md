# News Finder
Assignment

AhmeDroid


The project is built using Android Studio Chipmunk 2021.2.1
Using Android Gradle Plugin 7.1.3
Using Gradle 7.3.3
Using Android Build Tools 30.0.3
Using JDK 11

Note: I did not take the atomic-commit approach with this project although I would've preferred it.

Approach in this project:

MVVM

Clean Code Rules applied

Clean Architecture implemented in a light way - Android friendly way

DI - using Hilt

Android Architecture Components

Navigation component

ViewBinding

Nothing fancy, just the necessaries :D

New Line at each file's end to make it easier in the future to implement gradle-based tasks


Notes:
Minimal comments, as per Clean Code book written by uncle bob

Core module Acts as a glue between different modules

I didn't use usecases and made the viewmodels communicate directly with the repos for ease of use.

