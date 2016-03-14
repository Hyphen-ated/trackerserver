package hyphenated.trackerserver;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class TrackerServerConfiguration extends Configuration {
    private DataSourceFactory database = new DataSourceFactory();

    private String twitchClientId;

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("twitchClientId")
    public String getTwitchClientId() { return twitchClientId;}
}
