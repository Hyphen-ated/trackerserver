package hyphenated.trackerserver.resources;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import hyphenated.trackerserver.TrackerServerConfiguration;
import hyphenated.trackerserver.api.UpdateReport;
import hyphenated.trackerserver.api.UpdateResponse;
import hyphenated.trackerserver.api.UserCreatedResponse;
import hyphenated.trackerserver.db.UserDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Path("/tracker/api")
@Produces(MediaType.APPLICATION_JSON)
public class MainResource {
    private UserDAO dao;
    private String twitchClientID;
    ObjectMapper mapper = new ObjectMapper();
    public MainResource(TrackerServerConfiguration config, UserDAO dao) {
        this.dao = dao;
        this.twitchClientID = config.getTwitchClientId();
    }

    @GET
    @Path("/user/{name}/version")
    public Optional<String> getUpdateTime(@PathParam("name") String name) {
        String val = dao.getupdatetimeByName(name.toLowerCase(Locale.US));
        return Optional.fromNullable(val);
    }

    @GET
    @Path("/user/{name}/")
    public Optional<String> getTrackerState(@PathParam("name") String name) {
        String val = dao.getTrackerStateByName(name.toLowerCase(Locale.US));
        return Optional.fromNullable(val);
    }

    @GET
    @Path("/userlist/")
    public List<UpdateReport> getUserList() {
        return dao.getLatestUpdates();
    }

    @GET
    @Path("/servertime/")
    public long getTime() {
        //integer division, throw away remainder (we dont care about millis)
        return System.currentTimeMillis() / 1000;
    }

    @GET
    @Path("/twitchclientid/")
    public String getTwitchClientId() {
        return twitchClientID;
    }

    @PUT
    @Path("/update/{token}")
    public UpdateResponse updateWithToken(@PathParam("token") String token, String trackerState) {
        dao.updateTrackerState(token, trackerState);
        String name = dao.getNameByToken(token);
        return new UpdateResponse(name);
    }

    @PUT
    @Path("/createuser/{twitchToken}")
    public UserCreatedResponse createUser(@PathParam("twitchToken") String twitchToken) {
        URL twitchAuthUrl;
        try {
            twitchAuthUrl = new URL("https://api.twitch.tv/kraken?oauth_token=" + twitchToken);
        } catch (MalformedURLException e) {
            throw new BadRequestException("invalid token format");
        }
        String name;
        try {
            JsonNode rootNode = mapper.readTree(twitchAuthUrl.openStream());
            name = rootNode.get("token").get("user_name").asText().toLowerCase(Locale.US);
        } catch (IOException | NullPointerException e) {
            throw new BadRequestException("token not recognized by twitch (or twitch api is down)");
        }

        String token = UUID.randomUUID().toString();
        dao.deleteUser(name);
        dao.insertNewUser(name, token);
        return new UserCreatedResponse(name, token);
    }
}
