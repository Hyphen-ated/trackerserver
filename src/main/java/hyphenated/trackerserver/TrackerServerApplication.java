package hyphenated.trackerserver;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TrackerServerApplication extends Application<TrackerServerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TrackerServerApplication().run(args);
    }

    @Override
    public String getName() {
        return "trackerserver";
    }

    @Override
    public void initialize(final Bootstrap<TrackerServerConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final TrackerServerConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
