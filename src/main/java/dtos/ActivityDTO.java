package dtos;

import enums.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@ToString
@AllArgsConstructor
@Builder
public class ActivityDTO {
    private LocalDate exerciseDate;
    public ActivityType exerciseType;
    private LocalTime timeOfDay;
    private double duration;  // In hours, for example
    private double distance;  // In kilometers or miles
    private String comment;
    private CityInfoDTO cityInfo;
    private WeatherInfoDTO weatherInfo;
}