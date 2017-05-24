<h1>DGT Delay stream</h1>

Delay an DGT LiveChess stream from digital chess boards with this app

<h2>Download</h2>
<p>
<a href="http://dgtlivechessdelay.jogo.sk/dgtlivechessdelay.zip">download here</a>
</p>

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