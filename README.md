# ActivityLogger
Activity Logger Exercise Part 1
Your task is to create a proof of concept solution for a site where people can log their fitness activities. More precisely for walking, jogging og cycling. Many advanced systems exist on the market today, like Stava, Garmin Connect, Endomondo, Nike+, but for many people these apps are overly complicated. So we go simple.

The idea is that a user can log an exercise activity on a website and then the system will enrich the data from two external APIs. Namely with weather info from the current day and a few data about the city in which the exercise took place. See illustration below:

Activity Logger API

You will be able to build a real solution with a frontend later this semester. But for now we will focus on the backend. In this exercise, we will focus on getting the data from the APIs, storing the data in memory in DTOs. Later we might want to add the data to a database with JPA. But not today.

The data
All data will be held in an ActivityDTO. The ActivityDTO will contain / reference a WeatherInfoDTO and a CityInfoDTO. The content of WeatherInfoDTO and CityInfoDTO can be obtained from two external APIs. The domain model may look like this:

Domain model
Activity Logger domain model

External API-servers to be used in this exercise
WeatherInfo: https://vejr.eu/pages/api-documentation
CityInfo: https://dawadocs.dataforsyningen.dk/dok/stednavne
Examples on external api-calls
To get the current weather in Roskilde: https://vejr.eu/api.php?location=Roskilde&degree=C

To get info about the city of Roskilde: https://dawa.aws.dk/steder?hovedtype=Bebyggelse&undertype=by&prim%C3%A6rtnavn=Roskilde.

OBS! The DAWA API need to have http version set to 1.1 to work. This can be done by adding the following line of code to your HttpClient (.version(HttpClient.Version.HTTP_1_1)):

HttpRequest request = HttpRequest
.newBuilder()
.version(HttpClient.Version.HTTP_1_1)
.uri(new URI(encodedURL))
.GET()
.build();

The exercise
Take a close look at the domain model and the external APIs. What data do you need to get from the APIs? What data do you need to store in the ActivityDTO?

Create a WeatherInfoDTO and a CityInfoDTO that can hold the data from the external APIs.

Create an ActivityDTO that can hold the data from the external APIs + extra data that you might want to add.

Create a WeatherService and a CityService that can fetch data from the external APIs and return the data in the form of a WeatherInfoDTO and a CityInfoDTO.

Create a ActivityService that can create an ActivityDTO and enrich it with data from the WeatherService and the CityService.

Create an integration test that can test the ActivityService and the WeatherService and the CityService.

Activity Logger Exercise Part 2
This is the Friday exercise for Java Deep Dive II. The exercise is a continuation of the exercise from Tuesday. Your task is to persist the data from the external APIs in a database.

The first part of the exercise can be found here
In case you haven’t completed the first part, you can find a solution here that you can use as a starting point.
Just to recap. The json from the WeatherInfo API looks like this:

{
"LocationName": "Roskilde",
"CurrentData": {
"temperature": 13.4,
"skyText": "Hovedsageligt klar",
"humidity": "",
"windText": "13.7 m/s"
}
}

The json from the CityInfo API looks like this (we have removed a lot of attributes to make things simpler):

[
{
"id": "12337669-dbab-6b98-e053-d480220a5a3f",
"primærtnavn": "Roskilde",
"href": "https://api.dataforsyningen.dk/steder/12337669-dbab-6b98-e053-d480220a5a3f",
"visueltcenter": [
12.08713962,
55.63659446
]
}
]

Also, the ActivityDTO, that includes all data should look something like this:

public class ActivityDTO {
private LocalDate exerciseDate;
private Activity exerciseType;
private LocalTime timeOfDay;
private double duration;  // In hours, for example
private double distance;  // In kilometers or miles
private String comment;
private CityInfoDTO cityInfo;
private WeatherInfoDTO weatherInfo;
}

The exercise
Change your code to use threads and executor service to get the city and weather data into your DTOs
Create a database with JPA and persist the data from the external APIs in the database. You should probably create the entities for Activity, CityInfo and WeatherInfo. Remember to add the Entities to the HibernateConfig file. Think about the relationships between Activity, CityInfo, and WeatherInfo when designing your entities. Consider using one-to-many or one-to-one relationships as needed, and ensure you annotate them properly with JPA annotations like @OneToMany, @ManyToOne, etc.
You should create a ActivityDAO class that can persist the data in the database. Send in the DTO’s and let the DAO class persist the data. You might want to return DTO’s with the ID’s from the database when creating new entries.
Create an ActivityService that can call the DAO class and persist the data in the database.
Perform number of requests to the APIs and persist the data in the database. You can use the ActivityService to do this.
Add a getAll method to the DAO class that can return all activities from the database. Use the Jackson ObjectMapper to convert your DTOs to JSON. For example, you can use objectMapper.writeValueAsString(dto) to convert an object into a JSON string.
Extra
Create integrations tests for the DAO class as needed.