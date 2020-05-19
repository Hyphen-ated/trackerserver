package hyphenated.trackerserver.resources;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import hyphenated.trackerserver.TrackerServerConfiguration;
import hyphenated.trackerserver.api.UpdateReport;
import hyphenated.trackerserver.api.UpdateResponse;
import hyphenated.trackerserver.api.UserCreatedResponse;
import hyphenated.trackerserver.db.UserDAO;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
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
        String twitchAuthUrl = "https://api.twitch.tv/helix/users/";
        
        String name;
        try {
            HttpGet req = new HttpGet(twitchAuthUrl);
            
            //see https://dev.twitch.tv/docs/authentication#sending-user-access-and-app-access-tokens
            req.setHeader(new BasicHeader("Authorization", "Bearer " + twitchToken));
            //see https://discuss.dev.twitch.tv/t/requiring-oauth-for-helix-twitch-api-endpoints/23916
            req.setHeader(new BasicHeader( "Client-ID", this.twitchClientID));
            HttpClient client = new DefaultHttpClient();

            HttpResponse resp = client.execute(req);
            InputStream content = resp.getEntity().getContent();
            JsonNode rootNode = mapper.readTree(content);
            
            //see https://dev.twitch.tv/docs/api/reference#get-users
            name = rootNode.get("data").get(0).get("display_name").asText().toLowerCase(Locale.US);
        } catch (IOException | NullPointerException e) {
            throw new BadRequestException("token not recognized by twitch (or twitch api is down)");
        }

        String token = UUID.randomUUID().toString();
        dao.deleteUser(name);
        dao.insertNewUser(name, token);
        return new UserCreatedResponse(name, token);
    }
}
