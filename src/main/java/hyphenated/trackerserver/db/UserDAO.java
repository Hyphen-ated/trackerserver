package hyphenated.trackerserver.db;

import hyphenated.trackerserver.api.UpdateReport;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface UserDAO {

    @SqlUpdate("delete from users where name = :name")
    void deleteUser(@Bind("name") String name);

    @SqlUpdate("insert into users (name, token, trackerstate, updatetime) values (:name, :token, '', 0)")
    void insertNewUser(@Bind("name") String name, @Bind("token") String token);

    @SqlUpdate("update users set trackerstate = :trackerstate, updatetime = cast(extract(epoch from current_timestamp) as integer) where token = :token")
    void updateTrackerState(@Bind("token") String token, @Bind("trackerstate") String trackerState);

    //this is retrieving how many seconds since they updated
    @SqlQuery("select name, cast(extract(epoch from current_timestamp) as integer) - updatetime from users order by updatetime desc limit 10")
    @Mapper(UpdateReportMapper.class)
    List<UpdateReport> getLatestUpdates();

    @SqlQuery("select updatetime from users where name = :name")
    String getupdatetimeByName(@Bind("name") String name);

    @SqlQuery("select trackerstate from users where name = :name")
    String getTrackerStateByName(@Bind("name") String name);

    @SqlQuery("select name from users where token = :token")
    String getNameByToken(@Bind("token") String token);

}