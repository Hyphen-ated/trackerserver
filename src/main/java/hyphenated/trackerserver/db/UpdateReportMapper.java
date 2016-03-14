package hyphenated.trackerserver.db;


import hyphenated.trackerserver.api.UpdateReport;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateReportMapper implements ResultSetMapper<UpdateReport>
{
    public UpdateReport map(int index, ResultSet r, StatementContext ctx) throws SQLException
    {
        // this is a username followed by how many seconds since they uploaded new info
        return new UpdateReport(r.getString(1), r.getInt(2));
    }
}