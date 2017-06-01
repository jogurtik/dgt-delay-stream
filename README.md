<h1>DGT Delay stream</h1>

Delay an DGT LiveChess stream from digital chess boards with this app

<h2>How this java application is working</h2>
<p>
There are two possibilities how to use this java application.<BR>
First one:
<ul>
<li>DGT Livechess will run on local computer</li>
<li>Livechess will copy with FTP uploader files to webserver to temporary directory</li>
<li>This script will be running on webserver
<li>Publish directory will be web visible directory
</ul>

Second one:
<ul>
<li>DGT Livechess will run on local computer</li>
<li>You will have installed FTP server on local (or another one) computer where this script will be running</li>
<li>Livechess will copy with FTP uploader files to local FTP server</li>
<li>This script will run on local computer and publish files to web FTP server</li>
</ul>
</p>


<h2>Requirements:</h2>
<ol>
<li>DGT Livechess</li>
<li>FTP connection settings to webserver</li>
<li>FTP Server on localhost (Second scenario)</li>
</ol>

<h2>Install steps</h2>
<ol>
<li>Unzip this file on webserver or on a local drive</li>
<li>Configure properly properties file</li> 
<li>Run run_dgt_delay_stream.bat on windows (or samelike on linux)</li>
</ol>

<h2>Properties file - dgt-delay-stream.properties</h2>
<ul>
<li>directory.livechess - path to directory where livechess files will be uploaded</li>
<li>directory.backup - working directory. Livechess files will be saved here</li>
<li>directory.publish - publish directory. This directory will contain files for publishing on webserver. This directory can be direct on webserver as public directory</li>
<li>delay.games - delay time of game stream in seconds. To delay game for 15 minutes, you need to set it as 15*60 = 900 (put value 900 there)</li>
<li>app.refresh - it's application refresh interval. Livechess has this value default set to 10 seconds.This value means "start repeat all checking after 10 seconds after finishing previous one"</li>
<li>boards.number - by backup directory creating app is checking if pgn contains all expected games and already last game is ending with valid signs (e.g. with asterix or with results). If pgn is not valid, app will destroy this backup and will try to create it again immediately (e.g. ignoring app.refresh value)</li>
<li>ftp.server - ftp server address</li>
<li>ftp.login - ftp server login name</li>
<li>ftp.password - ftp server password</li>
<li>ftp.active - if value is true, then ftp active mode will be set. Else pasive mode will be used</li>
<li>ftp.onlypgn - if value is true, only games.pgn file will be checked and uploaded to ftp server. This can be usefull by streams which are using only pgn file for streaming</li>  
<li>ftp.directory - ftp directory can be set to any subdirectory on ftp server</li>
</ul>


<h2>Some hints how it's working</h2>
<ul>
<li>If you let ftp.server empty in property file, Files will not be uploaded to ftp server. They will be only copied to publish directory. This is fine when this script will be running on webserver.</li>
<li>After application start every file's first check will be allways published. After this first check every next time the file will be published only when file was changed. For file change app is detecting datetimeModify property of file and Md5 checksum.</li>
<li>FTP connection to server is opening only when at least 1 file will be copied or publish directory contains at least one directory</li>
<li>Application is copying all files from backup/publish directories. It doesn't check if they are from DGT Livechess or not</li>
<li>If you want to publish something directly to webserver, create directory with name "1" in backup directory and give files there</li> 
<li>If you want to publish players to webservice and don't want to wait for delay.games time you can do it this way:
<ul>
<li>Create players in livechess app and publish them to ftp</li>
<li>Wait till application will create a new directory in backup directory</li> 
<li>Rename last created directory in backup directory to directory with name "1" and remove all other directories</li>
</ul>
</li>
</ul>

<h2>How to install ftp server on windows (e.g. localy)</h2>
<ul>
<li>Check this manual how to do it: <a href="https://www.howtogeek.com/140352/how-to-host-an-ftp-server-on-windows-with-filezilla/" target="new">https://www.howtogeek.com/140352/how-to-host-an-ftp-server-on-windows-with-filezilla/</a></li>
</ul>

<h2>Usefull applications</h2>
<ul>
<li>DGT Delay stream log analyser - <a href="https://github.com/jogurtik/dgt-delay-stream-log-analyser">https://github.com/jogurtik/dgt-delay-stream-log-analyser</a></li>
</ul>


<h2>This application is provided by slovakian online chess team</h2>

<h3>Contacts regarding making an online chess stream:</h3> 
Bury Rastislav (bury.rastislav@gmail.com), Dobrotka Martin, Pekar Karol, Palecek Peter, Diviak Rastislav

<h3>Our history of making live chess streams:</h3>
<ul>
<li>2017, BLR, Minsk, <b>European Individual Chess Championship 2017</b>, 100 boards</li>       
<li>2017, BLR, Minsk, <b>1st World Cadet Rapid Chess Championship 2017</b>, 100 boards</li>
<li>2017, BLR, Minsk, <b>1st World Cadet Blitz Chess Championship 2017</b>, 100 boards</li>
<li>2017, SVK, Ruzomberok, <b>The 17th IPCA World Individual Chess Championship</b>, 12 boards</li>
<li>2017, CZE, Kouty nad Desnou, Czech youth chess championship Kouty 2017, 50 boards</li>
<li>2011-2017, SVK, Various cities, Extraliga - Slovak team championship, 48 boards</li>
<ul>

2016, CZE, Marienbad, <b>26th World Senior Chess Championship 2016</b>, 100 boards
2016, CZE, Prague, <b>European Youth Chess Championship Prague 2016</b>, 100 boards
2016, SVK, Poprad, <b>FIDE World Youth U16 Chess Olympiad SLOVAKIA 2016</b>, 100 boards
2016, CZE, Kouty nad Desnou, <b>European Union Youth Championship</b>, 50 boards
2016, CZE, Kouty nad Desnou, Czech republic youth chess championship Kouty 2016, 50 boards
2016, CZE, Olomouc, Olomouc Chess Summer 2016, 20 boards
2016, CZE, Prague, Summer Prague 2016, 12 boards

2015, SVK, Bratislava, <b>IPCA World Individual Chess Championship 2015</b>, 60 boards
2015, CZE, Kouty nad Desnou, <b>European Union Youth Championship</b>, 50 boards
2015, CZE, Prague, Summer Prague 2015, 16 boards
2015, CZE, Pardubice, Czech Open 2015 + some accompanying tournaments, 100 boards

2014, HUN, Gyor, <b>World Youth U-16 Chess Olympiad 2014</b>, 108 boards
2014, CZE, Kouty nad Desnou, <b>European Union Youth Championship</b>, 50 boards
2014, SVK, Ruzomberok, <b>33rd MITROPA Cup 2014</b>, 30 boards
2014, CZE, Pardubice, Czech Open 2014 + some accompanying tournaments, 100 boards

2013, CZE, Malenovice, <b>European Union Youth Championship</b>, 50 boards
2013, CZE, Pardubice, Czech Open A 2013 + some accompanying tournaments, 100 boards

2012, CZE, Prague, <b>European Youth Chess Championship Prague 2012</b>, 100 boards

2011, SVK, Piestany, <b>IPCA World Championship 2011</b>, 50 boards
2011-2017, SVK, Various cities, Slovakian Youth Chess Championships, 62 boards
2011-2017, SVK, Various cities, Slovakian Championships, 50 boards
2010, SVK, Banska Stiavnica, Slovakian Championships, 16 boards
2007-2010, SVK, Various cities, Slovakian Youth Chess Championships, 16 boards

and <b>various other slovak chess tournaments</b> 

<h2>Changes</h2>
<ul>
version 1.0.1:
<li>fixing slashes in properties file in paths</li>
<li>shrinking informations logged into log file</li>
<li>changing some log messages</li>
</ul>
<ul>
version 1.0.0:
<li>Initial release</li>
</ul>