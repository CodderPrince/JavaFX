# Weather-forecast
A simple weather forecast application created using JavaFX.

## Description
* This application provides the **current weather forecast** and a **5-day weather forecast** for a user's current location.
* The application fetches live weather forecast data using the **free** version of the https://openweathermap.org/api API.
* The application also stores a local copy of the most recent forecast requested by a user.
* The application provides a detailed visual representation of the 5-day weather forecast in the form of a **line chart**.
* The visual representation contains weather parameters of a specific day on a **3-hour interval** throughout the day.

## Screenshots
![Main window](/src/Resources/Images/main.png)

![Detailed Day Forecast](/src/Resources/Images/detailed_forecast.png)

![Detailed Day Forecast Tooltip](/src/Resources/Images/detailed_forecast_tooltip.png)

## Requirements
* jdk-13.0.2
* openjfx-11.0.2
* jackson-core-2.11.3
* jackson-annotations-2.11.3
* jackson-databind-2.11.3

## How to use?
1. Clone the project.
   > git clone  https://github.com/TheRevenant04/Weather-forecast.git

1. Obtain a free API key from https://ipinfo.io

1. Obtain a free API key from https://openweathermap.org

1. Setup a javaFX project with the project requirements and source files cloned from the repo according to your IDE.

1. In the **APIKeys.json** file located in the **Resources** folder, make the following changes.
   1. Replace your API key obtained from https://ipinfo.io in the line shown below.
      > "IpInfo_API_Key" : "Your API key",
      
   1. Replace your API key obtained from https://openweathermap.org.
      > "owm_API_Key" : "Your API key"
      
1. Run **Driver.java** to run the application. 
