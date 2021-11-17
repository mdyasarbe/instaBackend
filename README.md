# instaBackend

Simple Instagram Like Social media Backend using Spring Boot, JPA , Mysql

– Users can register the system.
– Users can log in and log out of the system.
– Users can share (upload) pictures when they register and log into the system.
– Users can view (download) pictures when other users allow their pictures can be viewed publicly.
– Users can follow other users.
– Users can view the hottest pictures in their timeline.
– Users can search for pictures based on titles.

# REST API DESIGN :
Have to design the REST API for all the endpoints of the system.
Reference : For Uploading a Picture one can go by UploadPicture (api_dev_key,
picture, title, picture_description, tags[], picture_details)
# DB DESIGN :
Have to design a DB schema for holding all the data needed for the System.
Reference :For Holding the User Data, one can go by having a Separate User
Relation with needed attributes in it
# CACHE MODEL :
Better to hold the cache layer for faster access of the frequent records to get rid
of latency
# JAVA OBJECT MODEL :
Have to design the core object model of all the components of the system.The
design should be able to cover the inter-communication of the different objects.
