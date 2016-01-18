package hyphenated.trackerserver.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;

@JsonSnakeCase
public class UpdateReport {
    @JsonProperty
    private String name;
    @JsonProperty
    private int seconds;

    public UpdateReport(String name, int seconds) {
        this.name = name;
        this.seconds = seconds;
    }

    public String getName() {
        return name;
    }

    public int getSeconds() {
        return seconds;
    }
}
