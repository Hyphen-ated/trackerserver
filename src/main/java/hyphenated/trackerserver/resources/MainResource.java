package hyphenated.trackerserver.resources;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import hyphenated.trackerserver.api.UpdateResponse;
import hyphenated.trackerserver.api.UserCreatedResponse;
import hyphenated.trackerserver.db.UserDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

@Path("/tracker/api")
@Produces(MediaType.APPLICATION_JSON)
public class MainResource {
    private UserDAO dao;
    ObjectMapper mapper = new ObjectMapper();
    public MainResource(UserDAO dao) {
        this.dao = dao;
    }

    @GET
    @Path("/user/{name}/version")
    public Optional<String> getStateVersion(@PathParam("name") String name) {
        String val = dao.getStateVersionByName(name);
        return Optional.fromNullable(val);
    }

    @GET
    @Path("/user/{name}/")
    public Optional<String> getTrackerState(@PathParam("name") String name) {
        String val = dao.getTrackerStateByName(name);
        return Optional.fromNullable(val);
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
            name = rootNode.get("token").get("user_name").asText();
        } catch (IOException | NullPointerException e) {
            throw new BadRequestException("token not recognized by twitch (or twitch api is down)");
        }

        String token = UUID.randomUUID().toString();
        dao.deleteUser(name);
        dao.insertNewUser(name, token);
        return new UserCreatedResponse(name, token);
    }
}
