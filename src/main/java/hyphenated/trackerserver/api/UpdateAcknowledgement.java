package hyphenated.trackerserver.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;

@JsonSnakeCase
public class UpdateAcknowledgement {
    private String updatedUser;

    public UpdateAcknowledgement(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    @JsonProperty
    public String getUpdatedUser() {
        return updatedUser;
    }

    @JsonProperty
    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }
}
