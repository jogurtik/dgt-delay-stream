<h1>Chess online streaming</h1>
<p>This application is provided by slovakian online chess streaming team.
We are a skilled team of profesional international arbiters and we can offer you
online chess streaming from your chess tournaments. Online stream can be transfered
in real time or it can be also delayed when you wish. This is for you an
perfect solution, while you are not getting only online streaming, but also an
internation arbiter for your tournament. Every from our team members can create
and operate online stream alone - without any support. By bigger tournaments there
is already required our cooperation of multiple members of our team. Our references
you can find bellow. In cooperation with Slovak Chess Federation its possible also to
lend Slovak chess boards for online streaming (now so about 50 stock).
</p>
<h3>Contacts regarding making an online chess stream:</h3>
Bury Rastislav (bury.rastislav@gmail.com), Dobrotka Martin, Pekar Karol, Palecek Peter, Diviak Rastislav

<h3>Our history of making live chess streams:</h3>
<ul>
<li>2018, SVK, Poprad, <b>European Individual Women Chess Championship 2018</b>, 72 boards</li>       
<li>2011-2018, SVK, Various cities, Extraliga - Slovak team championship, 48 boards</li>
<li>2011-2018, SVK, Various cities, Slovakian Youth Chess Championships, 62 boards</li>
<li>2011-2018, SVK, Various cities, Slovakian Championships, 50 boards</li>  
</ul>

<ul>
<li>2017, BLR, Minsk, <b>European Individual Chess Championship 2017</b>, 100 boards</li>       
<li>2017, BLR, Minsk, <b>1st World Cadet Rapid Chess Championship 2017</b>, 100 boards</li>
<li>2017, BLR, Minsk, <b>1st World Cadet Blitz Chess Championship 2017</b>, 100 boards</li>
<li>2017, SVK, Ruzomberok, <b>The 17th IPCA World Individual Chess Championship</b>, 12 boards</li>
<li>2017, CZE, Kouty nad Desnou, Czech youth chess championship Kouty 2017, 50 boards</li>
<li>2017, CZE, Pardubice, Czech Open 2015 + some accompanying tournaments, 100 boards</li>
</ul>

<ul>
<li>2016, CZE, Marienbad, <b>26th World Senior Chess Championship 2016</b>, 100 boards</li>
<li>2016, CZE, Prague, <b>European Youth Chess Championship Prague 2016</b>, 100 boards</li>
<li>2016, SVK, Poprad, <b>FIDE World Youth U16 Chess Olympiad SLOVAKIA 2016</b>, 100 boards</li>
<li>2016, CZE, Kouty nad Desnou, <b>European Union Youth Championship</b>, 50 boards</li>
<li>2016, CZE, Kouty nad Desnou, Czech republic youth chess championship Kouty 2016, 50 boards</li>
<li>2016, CZE, Olomouc, Olomouc Chess Summer 2016, 20 boards</li>
<li>2016, CZE, Prague, Summer Prague 2016, 12 boards</li>
<li>2016, CZE, Pardubice, Czech Open 2015 + some accompanying tournaments, 100 boards</li>
</ul>

<ul>
<li>2015, SVK, Bratislava, <b>IPCA World Individual Chess Championship 2015</b>, 60 boards</li>
<li>2015, CZE, Kouty nad Desnou, <b>European Union Youth Championship</b>, 50 boards</li>
<li>2015, CZE, Prague, Summer Prague 2015, 16 boards</li>
<li>2015, CZE, Pardubice, Czech Open 2015 + some accompanying tournaments, 100 boards</li>
</ul>

<ul>
<li>2014, HUN, Gyor, <b>World Youth U-16 Chess Olympiad 2014</b>, 108 boards</li>
<li>2014, CZE, Kouty nad Desnou, <b>European Union Youth Championship</b>, 50 boards</li>
<li>2014, SVK, Ruzomberok, <b>33rd MITROPA Cup 2014</b>, 30 boards</li>
<li>2014, CZE, Pardubice, Czech Open 2014 + some accompanying tournaments, 100 boards</li>
</ul>

<ul>
<li>2013, CZE, Malenovice, <b>European Union Youth Championship</b>, 50 boards</li>
<li>2013, CZE, Pardubice, Czech Open A 2013 + some accompanying tournaments, 100 boards</li>
</ul>

<ul>
<li>2012, CZE, Prague, <b>European Youth Chess Championship Prague 2012</b>, 100 boards</li>
</ul>

<ul>
<li>2011, SVK, Piestany, <b>IPCA World Championship 2011</b>, 50 boards</li>
<li>2010, SVK, Banska Stiavnica, Slovakian Championships, 16 boards</li>
<li>2007-2010, SVK, Various cities, Slovakian Youth Chess Championships, 16 boards</li>
<li>and <b>various other slovak chess tournaments</b></li> 
</ul>
<BR><BR><BR>
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
<li>Java 1.8 and higher</li>
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
<li>directory.finished - finished directory. This directory will contain finished games from games.pgn</li>
<li>delay.games - delay time of game stream in seconds. To delay game for 15 minutes, you need to set it as 15*60 = 900 (put value 900 there)</li>
<li>delay.finishedgames - When game will finish, shall be direct published or still shall be delayed. Possible values "true" = it shall be delayed or "false". If "false" value set, this setting requires "onlypgn" as "true", else it will be delayed</li>
<li>delay.finishedround - When all games will finish, shall all games direct published or still shall be delayed. If true, you need to run script the whole delayed time till end. Possible values "true" = it shall be delayed or "false".</li>
<li>delay.games.define - You can specify which games shall be delayed. If asterix "*" all games will be delayed. Else you can define number of games separated with semicolon. Example: "1;3;4;9;10". If board numbers defined, this setting requires "onlypgn" as "true", else it will be disabled</li>
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
<li>After application start if publish directory is empty, current livechess files will be directly published. After this first check every next time the file will be published only when file was changed. For file change app is detecting datetimeModify property of file and Md5 checksum.</li>
<li>FTP connection to server is opening only when at least 1 file will be copied or publish directory contains at least one directory</li>
<li>Application is copying all files from backup/publish directories. It doesn't check if they are from DGT Livechess or not (check property file - setting ftp.onlypgn=false)</li>
<li>If you want to publish players to webservice and don't want to wait for delay.games time you only need to clean all files from publish directory</li>
<li>You can delay all games or some of them</li>
<li>You can choose if finished games are still delayed or they will be directly published</li>
<li>You can choose how big shall be the delay</li>
</ul>

<h2>How to install ftp server on windows (e.g. localy)</h2>
<ul>
<li>Check this manual how to do it: <a href="https://www.howtogeek.com/140352/how-to-host-an-ftp-server-on-windows-with-filezilla/" target="new">https://www.howtogeek.com/140352/how-to-host-an-ftp-server-on-windows-with-filezilla/</a></li>
</ul>

<h2>Usefull applications</h2>
<ul>
<li>DGT Delay stream log analyser - <a href="https://github.com/jogurtik/dgt-delay-stream-log-analyser">https://github.com/jogurtik/dgt-delay-stream-log-analyser</a></li>
</ul>

<h2>Changes</h2>
<ul>
version 3.0.0:
<li>Updating to Java 21</li>
<li>Updating to spring boot 3.3.3</li>
<li>Updating dependences</li>
<li>By creating backup direcotory copy just .pgn files. Fewer chance to have lock by livechess</li>
<li>Major changes, creating version 3.0.0</li>
</ul>

<ul>
version 2.1.0:
</ul>

<ul>
version 2.0.0:
<li>if still nothing was published (publish folder is empty), current livechess upload will be uploaded</li>
<li>if all games are finished, current livechess upload will be uploaded (is possible to change in property file)</li>
<li>it's possible to delay only some of the games</li>
<li>it's possible to configure in property file, that finished games are not delayed but all moves till end of game will be pushed at once</li>
</ul>

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
