# Advanced Android Practice App
# KeepCoding Startup Engineering Master V 

## Project name: MADridLife

<br>

## Synopsis
Create a mobile application to display information of shops and activities in Madrid, even when the user has no Internet connection.
Shops and activities are shown in a map.

<br>

## Technology
- Android Studio 3.0.1
- Kotlin
- SQLite
- GoogleMaps Api

<br>

## External libraries

### Picasso
- Library used for downloading and caching images.  

- Repo: <http://square.github.io/picasso/>

<br>
### AVLoadingIndicatorView
- Library used in HomeActivity.  
It provides an activity indicator, which shows while data is being downloaded from the net.  
Once the download is completed, it hides, showing two buttons, one for each section.

- Repo: <https://github.com/81813780/AVLoadingIndicatorView>

<br>

## Implementation

### Orientation
- App has custom layouts for portrait and landscape orientation, no matter the size of the screen.

### Localization
- App has been localized.  
If the device's language is Spanish ("es"), texts will be displayed in this language.  
Otherwise, texts will always be displayed in English.

### Internet Connection
- When launching, app checks if the device is connected to the net.  
- If there is net conection, it will download the data and continue with it's normal execution.
- If there is NO net connection, it will display a SnackBar, wich will inform the user about it and provide two buttons:
	- **Ok**, this button will close the app so the user can fix the issue.
	- **Use cached data**, this button will continue the apps execution using the cached data.

### Cache - DDBB
- When the app executes, it caches all the data in a SQLite Data Base.  
- Images and logos are not cached, but their source URL.  
- Images and logos themselves are cached by Picasso library previously described.

### Generic
- App has been coded as generic as possible.
- DDBB low-level entity model is generic for Shop and Activity, as well the high-level entity model.
- All data is store in one single table.
-  Entitys are differenced using a custom property (type) wich is added to it when it is being store in the DDBB.
- This property is either "shop" or "activity" according to the URL from where the data is downloaded.
- ListAndMapActivity and DetailActivity are also generic and can display information no matter the content's type/section.

<br>

## Activities
### HomeActivity
- An external library has been used to display a loding view in order to give user feedback while data is being downloaded from the net. Once the data is ready, the loading view dissappears. 
- This activity shows two buttons, Shops and Activities.  
Each one will take the user to the selected section of the app.

<br>
### MapAndListActivity
- Contains two parts:

#### Map
- Provided by GoogleMaps API.
- The map shows markers for each Shop/Activity in the list.
- Markers icons are customized and are different for Shop and Activity.
- Each marker has a custom InfoWindowAdapter, so it can display Shop/Activity's: logo, name and opening hours.
- Marker's InfoWindows are touchable, so when the user touchs it, app will navigate to Shop/Activity's DetailView.
- When this navigation is done, marker's InfoWindow will hide.

#### List
- Shown using a RecyclerView, in order the reuse cells when scroll is done.
- RecyclerView's cells are customized and display Shop/Activity's: logo, name and opening hours.
- Cells are touchable; when the user touchs one, app will navigate to Shop/Activity's DetailView.

<br>
### DetailActivity
- Shows a Shop/Activity's DetailView.
- This activity contains Shop/Activity's: image, description, opening hours, address and a map static image.
- Map's static image shows a marker with Shop/Activity's exact location.  
- Map's static image is also touchable, so when the user touchs it, GoogleMaps app will launch and provide user turn-by-turn navigation to the Shop/Activity's location by walking.



<br>




