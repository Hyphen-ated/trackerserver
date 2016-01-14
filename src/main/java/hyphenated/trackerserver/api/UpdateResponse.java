package hyphenated.trackerserver.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;

@JsonSnakeCase
public class UpdateResponse {
    @JsonProperty
    private String updatedUser;

    public UpdateResponse(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }
}
