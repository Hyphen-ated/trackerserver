package hyphenated.trackerserver.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface UserDAO {
    @SqlUpdate("create table users (name text primary key, token text unique, trackerState text )")
    void createUsersTable();

    @SqlUpdate("delete from users where name = :name")
    void deleteUser(@Bind("name") String name);

    @SqlUpdate("insert into users (name, token, trackerState) values (:name, :token, '')")
    void insertNewUser(@Bind("name") String name, @Bind("token") String token);

    @SqlUpdate("update users set trackerState = :trackerState where token = :token")
    void updateTrackerState(@Bind("token") String token, @Bind("trackerState") String trackerState);

    @SqlQuery("select trackerState from users where name = :name")
    String getTrackerStateByName(@Bind("name") String name);

    @SqlQuery("select name from users where token = :token")
    String getNameByToken(@Bind("token") String token);

}