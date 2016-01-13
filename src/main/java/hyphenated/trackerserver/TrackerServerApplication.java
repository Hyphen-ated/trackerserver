package hyphenated.trackerserver;

import hyphenated.trackerserver.db.UserDAO;
import hyphenated.trackerserver.resources.MainResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

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
    public void run(final TrackerServerConfiguration config,
                    final Environment environment) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "postgresql");
        final UserDAO dao = jdbi.onDemand(UserDAO.class);
        environment.jersey().register(new MainResource(dao));
    }

}
