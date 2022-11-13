# Fleetio Work Sample Assessment

## Completed
1. Querying the Fleetio API retrieves a list of vehicles to display to the user.
2. The results are shown in a paginated list. Scrolling to the bottom of the list automatically retrieves the next page of results.
3. Clicking on an item in the list shows its details.
4. The search bar at the top of the screen filters results by make.
5. Unit tests are available for the early features.
6. Instrumentation tests are available for the early features.\nEdit AppModule.kt, uncomment line 33 w/ CachedVehiclesService and comment line 34 w/ provideVehiclesService

## Would be Nice to Add
1. Loading animation while search results are loading, and displaying server error messages.
2. Up navigation
3. Instrumentation tests need to inject fakes so they don't hit the live server. Koin supports this but I didn't have enough time.
4. Filtered results are not maintained after navigating to the details screen and returning to the main results screen. The ViewModel just needs to retain the value in the Search Bar instead of the Search Bar being stateful.
5. There's no offline support. The repository always queries the server for results.
6. Display the number of results.

![Demo video](https://bitbucket.org/awood82/fleetio_assessment/src/main/demo.gif)
Or see 'demo.gif' in the project's root folder.