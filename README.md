<div align="center">
    <h1>News App</h1>
</div>

## Overview

- **Language and Asynchronous Programming**: Built entirely in [Kotlin](https://kotlinlang.org/) with [Coroutines](https://developer.android.com/kotlin/coroutines) for efficient asynchronous programming.
  
- **Dependency Injection**: Utilizes [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection, simplifying management of dependencies throughout the app.
  
- **Networking**: Integrates [Retrofit2](https://square.github.io/retrofit/) for robust network requests handling, ensuring seamless communication with APIs.

- **Local Database**: Implements [Room](https://developer.android.com/jetpack/androidx/releases/room) for efficient local data storage, offering a reliable and structured way to manage app data.

- **Navigation**: Uses [Navigation Components](https://developer.android.com/guide/navigation) to implement navigation within the app, providing a consistent and predictable user experience.

- **Image Loading**: Leverages [Glide](https://bumptech.github.io/glide/) for efficient image loading and caching, enhancing the app's performance when displaying images.

- **View Interaction**: Implements [View Binding](https://developer.android.com/topic/libraries/view-binding) to simplify interaction between views and activities/fragments, reducing boilerplate code and improving maintainability.

- **Pagination**: Implements pagination to efficiently load and display large datasets from the News API, enhancing user experience with seamless scrolling and data loading.
  
## Screenshots

<p float="left">
  <img src="https://github.com/user-attachments/assets/40126277-120f-463a-b4cf-2a215f4509a2" width=150" />
  <img src="https://github.com/user-attachments/assets/c8af72d2-fc68-475b-b1fb-d949b119e47c" width="150" />
  <img src="https://github.com/user-attachments/assets/fd5e6a84-195a-4a70-881f-9519144928a4" width=150" />
  <img src="https://github.com/user-attachments/assets/1a31de14-7197-4ad5-afe0-d333b41b92fc" width="150" />
  <img src="https://github.com/user-attachments/assets/1734c7d7-5d30-49f0-88ea-c46b19e9d0b2" width="150" />
</p>

## Architecture

The News App follows the MVVM architecture pattern along with the Repository pattern, adhering to [Google's recommended architecture](https://developer.android.com/topic/architecture).

<div align="center">
    <img src="https://github.com/bedirhanssaglam/weather-app/assets/105479937/e6ffd008-ea7d-4379-a787-e4e434707e83" alt="Architecture Diagram">
</div>

## API Integration

The app integrates with the [News API](https://newsapi.org/) to fetch and display news content.

