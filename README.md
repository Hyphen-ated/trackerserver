isaac-item-tracker-server
=========================

Description
-----------

This is a server software for The Binding of Isaac: Rebirth [item trackers](https://github.com/Hyphen-ated/RebirthItemTracker). Users can register with their Twitch account and then set up their item tracker to send updates on what items they have. Then people who are restreaming them can use the server to get those updates. The restreamer can then view the items in their own local copy of the item tracker, so they don't have to capture the item tracker from someone's stream.



Requirements
------------

* A Java runtime. I only tested with 8, but it probably works on 7.
* A database. It has been tested with PostgreSQL and SQLite3.



Building
--------

* `mvn clean package`



Running
-------

* To run it, first register your application at: https://www.twitch.tv/settings/connections
* Set up a database with the schema in `createdb.sql`.
* Edit `options.yaml` to point to that database and to have the client ID assigned to your app by Twitch.
* Start it with: `java -jar magazineservice.jar server options.yaml`
* Or, start it from source instead: `java hyphenated.trackerserver.TrackerServerApplication server options.yaml`



Startup
-------

If you want to have it automatically run at startup, a sysv-style init script is provided if you want to use it.
