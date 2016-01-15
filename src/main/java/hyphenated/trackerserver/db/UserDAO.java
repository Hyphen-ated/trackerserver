package hyphenated.trackerserver.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface UserDAO {

    @SqlUpdate("delete from users where name = :name")
    void deleteUser(@Bind("name") String name);

    @SqlUpdate("insert into users (name, token, trackerstate, stateversion) values (:name, :token, '', 0)")
    void insertNewUser(@Bind("name") String name, @Bind("token") String token);

    @SqlUpdate("update users set trackerstate = :trackerstate, stateversion = stateversion + 1 where token = :token")
    void updateTrackerState(@Bind("token") String token, @Bind("trackerstate") String trackerState);

    @SqlQuery("select stateversion from users where name = :name")
    String getStateVersionByName(@Bind("name") String name);

    @SqlQuery("select trackerstate from users where name = :name")
    String getTrackerStateByName(@Bind("name") String name);

    @SqlQuery("select name from users where token = :token")
    String getNameByToken(@Bind("token") String token);

}