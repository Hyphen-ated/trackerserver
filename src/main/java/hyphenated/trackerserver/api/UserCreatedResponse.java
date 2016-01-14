package hyphenated.trackerserver.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;

@JsonSnakeCase
public class UserCreatedResponse {
    @JsonProperty
    private String name;
    @JsonProperty
    private String token;

    public UserCreatedResponse(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

}
