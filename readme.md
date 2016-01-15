This is a server for The Binding Of Isaac item trackers. Users can register with their twitch account and then set
up their item tracker to send updates on what items they have. Then people who are restreaming them can use the server
to get those updates. The restreamer can then view the items in their own local copy of the item tracker, so they don't
have to capture the item tracker from someone's stream.

To run it, set up a database with the schema in createdb.sql (only tested on postgres so far)

Then edit options.yaml to point to that database

Then do java -jar <release jar> server options.yaml

If you want to run it from source instead, then it's: java hyphenated.trackerserver.TrackerServerApplication server options.yaml

To make a release jar do: mvn clean package


