isaac-item-tracker-server
=========================

Description
-----------

This is a server software for The Binding of Isaac: Rebirth [item trackers](https://github.com/Hyphen-ated/RebirthItemTracker). Users can register with their Twitch account and then set up their item tracker to send updates on what items they have. Then people who are restreaming them can use the server to get those updates. The restreamer can then view the items in their own local copy of the item tracker, so they don't have to capture the item tracker from someone's stream.

<br />

Requirements
------------

* A Java runtime (only been tested with 8, but will probably work on 7)
  * On Ubuntu: `apt install default-jre`
* A database (tested with PostgreSQL and SQLite3)
  * On Ubuntu: `apt install sqlite3`
* A Java compiler (only if you plan on compiling the project from scratch)
  * On Ubuntu: `apt install default-jdk maven

<br />

Building from Source
--------------------

* To remove all previous build artifacts and then build a JAR file: `mvn clean package`

<br />

Running
-------

* To run it, first register your application at: https://www.twitch.tv/settings/connections
* Set up a database with the schema in `createdb.sql`.
* Edit `options.yaml` to point to that database and to have the client ID assigned to your app by Twitch.
* Start it with: `java -jar target/trackerserver-1.0-SNAPSHOT.jar server options.yaml`
* Alternatively, you can start it from source instead: `java hyphenated.trackerserver.TrackerServerApplication server options.yaml`

<br />

Run at Startup
--------------

If you want to have it automatically run at startup, you can try using one of the provided scripts in the `startup` subdirectory.
