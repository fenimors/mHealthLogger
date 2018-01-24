# Project Idea

## Mental Health tracker app

* android probably targeting 7.0 and up
* link in with dropbox/onedrive/googledrive
* lets user log mental health states, medication tracker, *maybe idk, add in reminders for appts/appt notes*, probably have mhealth goals to attain option
* log will have different options for mental state and a area for free notes
* stored in json format, backup/sync with cloud storage providers
* possible mongo db backend for storage, keeping everything json, **nope use the built in sqllite database probably still use json though as that is fitting for this application**
* some kind of plain text data export option for human readable files
* possible android wear component
* unit testing with good coverage using junit? maybe pytest?, or some standard android dev unit testing framework
* some amount of functional tests
* probably should add it so it requires fingerprint or pin to open app
  * should encrypt the data
* probably will let user choose the different mental health state sliders/(prebuilt buttons or whatever) will be included
* nicely formatted timeline and goal tracking display quantified self like status giving averages for different sliders over the course of weeks/months/years
* **Smaller stretch goal**
  * alexa skill for voice based adding and querying of the app
  * probably would have to add some kind of aws connecting in app that could be pushed the info from skill through aws lambda function
* **Giant stretch goal**
  * add in a built in cloud sync system that allows viewing online
  * cross device sync
  * full aws backend

### First Steps

* getting android dev environment setup
  * android studio
  * find a good unit testing framework. Junit? **looks like junit is the standard thing for android studio**
  * json based android data storage medium, mongodb? maybe just parse json myself? 
    * **looks like I should check out [couch db](http://couchdb.apache.org/) possibly**
    * **android standard is sqllite might end up using that though not ideal**
    * **looks like probably will end up storing the json in the built in local sqllite db**
  * make a small sketch/plan for the ui, each of the screens
    * settings?
    * calender or timeline display?
    * log screen sliders/buttons and things what would be given as given options and what would just be put in the daily text log
    * will follow googles material design standards
  * look into cloud storage api's for onedrive/googledrive/dropbox on android maybe just use one to start with
  * look at android app locking encryption stuff, see if any built in tools/frameworks already present to use
  * figure out how each data block will be formatted what will be store in json for each day what keys will be there for each json block? data, time, built in status's storage (so like 1-10 slider for separate feels), text field key
    * Each day would be an entry into mongo db but maybe should not utalize mongo? look at alternatives or not using a db