Gmon-util is micro webservices module for accessing the database of gmon application used by Tanita (body Impedance) machines

Requirements:
=============

* Jdk1.7 or higher
* gradle 

How to Run:
===========
* Clone the repository and unzip to a folder gmon-util
* cd to the folder and run `gradle build`
* This creates a war file in the build directory.
* You can either run the war with embedded containers like `jetty-Runner` or deploy to a serlvet container like Tomcat

How does it Work:
=================

* Gmon-util runs a web-service end-point with the signature `/api/bodyImp/{patientId}` on a `GET` request.
* Patient data has to be entered into the GMON application with the required fields
* Perform the test.Once done the data will be save to the `firebird` database used by the gmon application
* When a `GET` request is made at `/api/bodyImp/{patientId}` the data for the given patiendId {patienId} is queried from  the firebird database directly and converted to a JSON format which will be available as a response.
* Json reponse can be used by any application which is capable of making http request to a Web-service

Alternative Approach:
=====================

The GMON.EXE can be executed with an INI file to create a patient in the database automatically. 
The options in the INI file control some functions in the GMON to print reports or return actual 
measurement values. 


You can execute the GMON with: GMon.exe INI IniFile
e.g.: c:\Program Files\MedServ\GMon\GMon.exe INI c:\htc\htc.ini
The current INI file format is:
 
 
[PATIENT] <br>
 ID=fd12345 <br> 
 VNAME=Fred <br>
 FNAME=Demoman <br>
 TITEL=Dr. <br>
 SEX=M <br>
 BIRTHDATE=19490921<br> 
 GROUP1= <br>
 GROUP2= <br>
 COMMENT= <br>
 BTYP= <br>
 [OPTIONS] <br>
 REQUEST_LAST_DATA=TABC;KUNDE.PATNR<br> 
 [REPORT_ONEXIT] <br>
 NAME=BCM Analyse <br>
 FORMAT=gif <br>
 BUTTON=Save Report to HTC<br> 
 [REQUEST_LAST_DATA] <br>
 TABC.DATETIME=20090908140500<br> 
 TABC.MODEL=SC-330 <br>
 TABC.FLAG=4 <br>
 TABC.CONDITION=0<br> 
 TABC.AGE=59.000 <br>
 TABC.BTYP=0 <br>
 TABC.HEIGHT=170.000<br> 
 TABC.WEIGHT=83.300 <br>
 TABC.BMR=7701.005 <br>
 TABC.FATP=24.500 <br>
 TABC.FATM=20.400 <br>
 TABC.FFM=62.900 <br>
 TABC.TBW=43.200 <br>
 TABC.PMM=59.800 <br>
 TABC.IMP=522.300 <br>
 TABC.BMI=26.300 <br>
 TABC.VFATL=9.000 <br>
 TABC.BONEM=3.100 <br>
 TABC.METAAGE=51.000 <br>
 KUNDE.PATNR=7 <br>


The [Patient] is automatically created or updated with the specified values. If the values change, which 
are relevant for the evaluation in the GMON, then all risk values are new calculated. The values INFO 
(KUNDE.BEMERKUNG) and BTYP (KUNDE.BTYP) are only used, when the patient is new 
created. With the BTYP you can specify the "Asian Judgement" (Bit 0) and then "Age depending BMI 
score" (not Bit 1). 


![](https://github.com/cghr/gmon-util/blob/master/images/image1.jpg)


Under [Options] / REQUEST_LAST_DATA you can specify the Database tables or 
tables.column ( ; separated list), where the last actual values should be reported in the section 
[REQUEST_LAST_DATA] when the GMON is closed. 

![](https://github.com/cghr/gmon-util/blob/master/images/image2.jpg)


Under [REPORT_ONEXIT] you can specify the report and the report format (gif, bmp, emf) 
that should be saved, when the GMON is closed. The user must press the save button. The 
caption of the save button can specify with BUTTON=XXXX. The filename is generated as 
"ID_ReportName". If the report consists of many pages, the extension "_pN" is appended. 
The default directory is the path of the INI file. 
At this point the user can also send the specified report to the printer, or save and print any 
other reports. Over the button Exit, the GMON will be closed and the process terminated. 
Now the executing master process can read the results from the INI file, collect and delete the 
reports with the current ID from the path of the INI file, or can access to the GMON database 
itself (e.g. over the KUNDE.PATNR). 

![](https://github.com/cghr/gmon-util/blob/master/images/image3.jpg)