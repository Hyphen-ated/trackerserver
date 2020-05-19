isaac-item-tracker-server
=========================

<br />

Description
-----------

This is the server software for The Binding of Isaac: Rebirth [item trackers](https://github.com/Hyphen-ated/RebirthItemTracker). Using a Twitch account, users can broadcast their items to the server. This allows restreamers to view the player's items in their own local copy of the item tracker so they don't have to capture the item tracker from the player's stream.

<br />

Requirements
------------

* A Java runtime (only been tested with 8, but will probably work on 7 or later)
  * On Ubuntu: `apt install default-jre`
* A Java compiler (only if you plan on compiling the project from scratch)
  * On Ubuntu: `apt install default-jdk maven`

<br />

Building from Source
--------------------

* To remove all previous build artifacts and then build a JAR file: `mvn clean package`

<br />

Running
-------

* To run it, first register your application on Twitch ( https://www.twitch.tv/settings/connections )
* Set up a database with the schema in `createdb.sql`.
  * For SQLite3: `sqlite3 database.sqlite < createdb.sql`
* Edit `options.yaml`:
  * Ensure that the sqlite database file it's trying to point to is in a writeable location.
  * Ensure that it has your client ID from Twitch.
  * Ensure that it has the right path for the log files.
* Start it with: `java -jar target/trackerserver-1.0-SNAPSHOT.jar server options.yaml`
* Alternatively, you can start it from source instead: `java hyphenated.trackerserver.TrackerServerApplication server options.yaml`

<br />

Run at Startup
--------------

If you want to have it automatically run at startup, you can try using one of the provided scripts in the `startup` subdirectory.
