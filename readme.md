This is a server for The Binding Of Isaac item trackers. Users can register with their twitch account and then set
up their item tracker to send updates on what items they have. Then people who are restreaming them can use the server
to get those updates. The restreamer can then view the items in their own local copy of the item tracker, so they don't
have to capture the item tracker from someone's stream.

It requires a java runtime. I only tested with 8, but it probably works on 7.

To run it, first register your application at https://www.twitch.tv/settings/connections .
You'll need to enter a redirect URI which is going to point to this service, and it should look something like http://kanye.space:8000/tracker/setup

Set up a database with the schema in createdb.sql (only tested on postgres so far)

Then edit options.yaml to point to that database, and to have the client ID assigned to your app by twitch.

If you haven't downloaded a release jar for this project, you can make one with: mvn clean package

Then do java -jar magazineservice.jar server options.yaml

If you want to run it from source instead, then it's: java hyphenated.trackerserver.TrackerServerApplication server options.yaml

A sysv-style init script is provided if you want to use it.


