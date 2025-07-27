package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class WeatherResponse {

    private Current current;




@Getter
@Setter
    public class Current{

        private int temperature;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;



    }




}







