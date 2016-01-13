package hyphenated.trackerserver.resources;


import com.google.common.base.Optional;
import hyphenated.trackerserver.api.UpdateAcknowledgement;
import hyphenated.trackerserver.db.UserDAO;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/trackerapi/")
public class MainResource {
    private UserDAO dao;
    public MainResource(UserDAO dao) {
        this.dao = dao;
    }

    @GET
    @Path("/user/{name}/")
    public Optional<String> getTrackerState(@PathParam("name") String name) {
        String val = dao.getTrackerStateByName(name);
        return Optional.fromNullable(val);
    }

    @PUT
    @Path("/update/{token}")
    public UpdateAcknowledgement updateWithToken(@PathParam("token") String token, String trackerState) {
        dao.updateTrackerState(token, trackerState);
        String name = dao.getNameByToken(token);
        return new UpdateAcknowledgement(name);
    }

    @PUT
    @Path("/createuser/{twitchToken}")
    public void createUser(@PathParam("twitchToken") String twitchToken) {
        //TODO: go to twitch, pass the token, get their name
        String name = "";
        //generate a uuid
        String token = "";
        dao.insertNewUser(name, token);
    }
}
