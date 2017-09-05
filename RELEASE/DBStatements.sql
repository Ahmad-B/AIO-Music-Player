BEGIN TRANSACTION;
CREATE TABLE "TrackPlaylist" (
	`TrackID`	integer(10) NOT NULL,
	`playlistID`	integer(10) NOT NULL,
	`dateAdded`	TEXT NOT NULL,
	`TrackPlaylistID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	FOREIGN KEY(`TrackID`) REFERENCES `Track`(`TrackID`),
	FOREIGN KEY(`playlistID`) REFERENCES `Playlist`(`PlaylistID`)
);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (36,1,'24/03/2016',110);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (38,1,'24/03/2016',111);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (39,1,'24/03/2016',112);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (148,2,'24/03/2016',113);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (146,2,'24/03/2016',114);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (147,2,'24/03/2016',115);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (143,2,'24/03/2016',116);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (37,3,'24/03/2016',117);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (39,3,'24/03/2016',118);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (145,3,'24/03/2016',119);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (146,3,'24/03/2016',120);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (44,4,'24/03/2016',121);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (45,4,'24/03/2016',122);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (186,4,'24/03/2016',125);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (192,4,'24/03/2016',126);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (193,4,'24/03/2016',128);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (37,5,'24/03/2016',129);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (36,5,'24/03/2016',130);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (45,5,'24/03/2016',131);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (46,5,'24/03/2016',132);
INSERT INTO `TrackPlaylist` (TrackID,playlistID,dateAdded,TrackPlaylistID) VALUES (47,5,'24/03/2016',133);
CREATE TABLE "Track" (
	`TrackID`	INTEGER NOT NULL,
	`name`	varchar(255) NOT NULL,
	`artist`	varchar(255) NOT NULL,
	`AlbumID`	integer(10) NOT NULL,
	`trackPath`	VARCHAR,
	`genre`	TEXT,
	PRIMARY KEY(TrackID)
);
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (1,'Space Bound','Eminem',1,'http://se38.x10.mx/music/Eminem/10.Space_Bound.mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (2,'Cinderella Man','Eminem',1,'http://se38.x10.mx/music/Eminem/11.Cinderella_Man.mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (3,'Cold Wind Blows','Eminem',1,'http://se38.x10.mx/music/Eminem/1.Cold_Wind_Blows.mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (4,'25 to Life','Eminem',1,'http://se38.x10.mx/music/Eminem/12.25_to_Life.mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (5,'So Bad','Eminem',1,'http://se38.x10.mx/music/Eminem/13.So_Bad.mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (6,'Almost Famous','Eminem',1,'http://se38.x10.mx/music/Eminem/14.Almost_Famous.mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (7,'Love the Way You Lie','Eminem',1,'http://se38.x10.mx/music/Eminem/15.Love_the_Way_You_Lie_(feat._Rihanna).mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (8,'Youre Never Over','Eminem',1,'http://se38.x10.mx/music/Eminem/16.Youre_Never_Over.mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (9,'You Dont Own Me','Eminem',1,'http://se38.x10.mx/music/Eminem/17.You%20Dont%20Own%20Me.mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (10,'Talkin 2 Myself','Eminem',1,'http://se38.x10.mx/music/Eminem/2.Talkin_2_Myself_(feat._Kobe).mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (11,'On Fire','Eminem',1,'http://se38.x10.mx/music/Eminem/3.On_Fire.mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (12,'Wont Back Down','Eminem',1,'http://se38.x10.mx/music/Eminem/4.Wont_Back_Down_(feat._Pink).mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (13,'W.T.P','Eminem',1,'http://se38.x10.mx/music/Eminem/5.W.T.P..mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (14,'Going Through Changes','Eminem',1,'http://se38.x10.mx/music/Eminem/6.Going_Through_Changes.mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (15,'Not Afraid','Eminem',1,'http://se38.x10.mx/music/Eminem/7.Not_Afraid.mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (16,'Seduction','Eminem',1,'http://se38.x10.mx/music/Eminem/8.Seduction.mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (17,'No Love','Eminem',1,'http://se38.x10.mx/music/Eminem/9.No_Love_(feat._Lil_Wayne).mp3','Rap');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (18,'Legend','Drake',2,'http://se38.x10.mx/music/Drake/01%20Legend.mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (19,'Energy','Drake',2,'http://se38.x10.mx/music/Drake/02%20Energy.mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (20,'10 Bands','Drake',2,'http://se38.x10.mx/music/Drake/03%2010%20Bands.mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (21,'Know Yourself','Drake',2,'http://se38.x10.mx/music/Drake/04%20Know%20Yourself.mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (22,'No Tellin','Drake',2,'http://se38.x10.mx/music/Drake/05%20No%20Tellin.mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (23,'Madonna','Drake',2,'http://se38.x10.mx/music/Drake/06%20Madonna.mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (24,'6 God','Drake',2,'http://se38.x10.mx/music/Drake/07%206%20God.mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (25,'Star67','Drake',2,'http://se38.x10.mx/music/Drake/08%20Star67.mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (26,'Preach','Drake',2,'http://se38.x10.mx/music/Drake/09%20Preach%20(feat.%20PARTYNEXTDOOR).mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (27,'Wednesday Night Interlude','Drake',2,'http://se38.x10.mx/music/Drake/10%20Wednesday%20Night%20Interlude%20(feat.%20PARTYNEXTDOOR).mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (28,'Used To','Drake',2,'http://se38.x10.mx/music/Drake/11%20Used%20To%20(feat.%20Lil%20Wayne).mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (29,'6 Man','Drake',2,'http://se38.x10.mx/music/Drake/12%206%20Man.mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (30,'Now & Forever','Drake',2,'http://se38.x10.mx/music/Drake/13%20Now%20&%20Forever.mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (31,'Company','Drake',2,'http://se38.x10.mx/music/Drake/14%20Company%20(feat.%20Travi$%20Scott).mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (32,'You & The 6','Drake',2,'http://se38.x10.mx/music/Drake/15%20You%20&%20The%206.mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (33,'Jungle','Drake',2,'http://se38.x10.mx/music/Drake/16%20Jungle.mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (34,'6PM In New York','Drake',2,'http://se38.x10.mx/music/Drake/17%206PM%20In%20New%20York.mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (35,'Welcome to New York','Taylor Swift',3,'http://se38.x10.mx/music/Swift/01.%20Welcome%20to%20New%20York.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (36,'Blank Space','Taylor Swift',3,'http://se38.x10.mx/music/Swift/02.%20Blank%20Space.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (37,'Style','Taylor Swift',3,'http://se38.x10.mx/music/Swift/03.%20Style.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (38,'Out of the Woods','Taylor Swift',3,'http://se38.x10.mx/music/Swift/04.%20Out%20of%20the%20Woods.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (39,'All You Had to Do Was Stay','Taylor Swift',3,'http://se38.x10.mx/music/Swift/05.%20All%20You%20Had%20to%20Do%20Was%20Stay.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (40,'Shake It Off','Taylor Swift',3,'http://se38.x10.mx/music/Swift/06.%20Shake%20It%20Off.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (41,'I Wish You Would','Taylor Swift',3,'http://se38.x10.mx/music/Swift/07.%20I%20Wish%20You%20Would.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (42,'Wildest Dreams','Taylor Swift',3,'http://se38.x10.mx/music/Swift/08.%20Wildest%20Dreams.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (43,'Bad Blood','Taylor Swift',3,'http://se38.x10.mx/music/Swift/09.%20Bad%20Blood.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (44,'How You Get the Girl','Taylor Swift',3,'http://se38.x10.mx/music/Swift/10.%20How%20You%20Get%20the%20Girl.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (45,'This Love','Taylor Swift',3,'http://se38.x10.mx/music/Swift/11.%20This%20Love.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (46,'I Know Places','Taylor Swift',3,'http://se38.x10.mx/music/Swift/12.%20I%20Know%20Places.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (47,'Clean','Taylor Swift',3,'http://se38.x10.mx/music/Swift/13.%20Clean.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (48,'Wonderland','Taylor Swift',3,'http://se38.x10.mx/music/Swift/14.%20Wonderland.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (49,'You R In Love','Taylor Swift',3,'http://se38.x10.mx/music/Swift/15.%20You%20R%20In%20Love.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (50,'New Romance','Taylor Swift',3,'http://se38.x10.mx/music/Swift/16.%20New%20Romance.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (51,'Out of the Woods (Voice Memo)','Taylor Swift',3,'http://se38.x10.mx/music/Swift/17.%20Out%20of%20the%20Woods%20(Voice%20Memo).mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (52,'Shake It Off (Voice Memo)','Taylor Swift',3,'http://se38.x10.mx/music/Swift/18.%20Shake%20It%20Off%20(Voice%20Memo).mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (53,'I Know Places (Voice Memo)','Taylor Swift',3,'http://se38.x10.mx/music/Swift/19.%20I%20Know%20Places%20(Voice%20Memo).mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (54,'Addicted To You','Avicii',4,'http://se38.x10.mx/music/Avicii/04%20-%20Avicii%20-%20Addicted%20To%20You.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (55,'You Make Me','Avicii',4,'http://se38.x10.mx/music/Avicii/02%20-%20Avicii%20-%20You%20Make%20Me.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (56,'Liar Liar','Avicii',4,'http://se38.x10.mx/music/Avicii/06%20-%20Avicii%20-%20Liar%20Liar.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (57,'Wake Me Up','Avicii',4,'http://se38.x10.mx/music/Avicii/01%20-%20Avicii%20-%20Wake%20Me%20Up.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (58,'Shame On Me','Avicii',4,'http://se38.x10.mx/music/Avicii/07%20-%20Avicii%20-%20Shame%20On%20Me.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (59,'Hey Brother','Avicii',4,'http://se38.x10.mx/music/Avicii/03%20-%20Avicii%20-%20Hey%20Brother.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (60,'Heart Upon My Sleeve','Avicii',4,'http://se38.x10.mx/music/Avicii/10%20-%20Avicii%20-%20Heart%20Upon%20My%20Sleeve.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (61,'Lay Me Down','Avicii',4,'http://se38.x10.mx/music/Avicii/08%20-%20Avicii%20-%20Lay%20Me%20Down.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (62,'Hope Theres Someone','Avicii',4,'http://se38.x10.mx/music/Avicii/09%20-%20Avicii%20-%20Hope%20Theres%20Someone.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (63,'Dear Boy','Avicii',4,'http://se38.x10.mx/music/Avicii/05%20-%20Avicii%20-%20Dear%20Boy.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (64,'Miracle','Blackmill',5,'http://se38.x10.mx/music/Blackmill/01.%20Blackmill%20-%20Miracle.mp3','Dubstep');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (65,'Spirit Of Life','Blackmill',5,'http://se38.x10.mx/music/Blackmill/02.%20Blackmill%20-%20Spirit%20Of%20Life.mp3','Dubstep');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (66,'Let It Be','Blackmill',5,'http://se38.x10.mx/music/Blackmill/03.%20Blackmill%20-%20Let%20It%20Be%20(feat.%20Veela).mp3','Dubstep');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (67,'Embrace','Blackmill',5,'http://se38.x10.mx/music/Blackmill/04.%20Blackmill%20-%20Embrace.mp3','Dubstep');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (68,'Dont Let Me Down','Blackmill',5,'http://se38.x10.mx/music/Blackmill/05.%20Blackmill%20-%20Dont%20Let%20Me%20Down%20(feat.%20Cat%20Martin).mp3','Dubstep');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (69,'My Love','Blackmill',5,'http://se38.x10.mx/music/Blackmill/06.%20Blackmill%20-%20My%20Love.mp3','Dubstep');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (70,'The Drift','Blackmill',5,'http://se38.x10.mx/music/Blackmill/07.%20Blackmill%20-%20The%20Drift.mp3','Dubstep');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (71,'Lucid Truth','Blackmill',5,'http://se38.x10.mx/music/Blackmill/08.%20Blackmill%20-%20Lucid%20Truth.mp3','Dubstep');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (72,'Love At Heart','Blackmill',5,'http://se38.x10.mx/music/Blackmill/09.%20Blackmill%20-%20Love%20At%20Heart.mp3','Dubstep');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (73,'Sarajevo','Blackmill',5,'http://se38.x10.mx/music/Blackmill/10.%20Loz%20Contreras%20-%20Sarajevo%20(Blackmill%20Remix).mp3','Dubstep');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (74,'Fortune Soul','Blackmill',5,'http://se38.x10.mx/music/Blackmill/11.%20Blackmill%20-%20Fortune%20Soul.mp3','Dubstep');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (75,'Life','Blackmill',5,'http://se38.x10.mx/music/Blackmill/Blackmill%20feat.%20Veela%20-%20Life.mp3','Dubstep');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (76,'I Dont Play About My Paper','DJ Khaled',6,'http://se38.x10.mx/music/DJ/01.%20I%20Dont%20Play%20About%20My%20Paper%20(feat.%20Future%20&%20Rick%20Ross).mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (77,'I Ride','DJ Khaled',6,'http://se38.x10.mx/music/DJ/02.%20I%20Ride%20(feat.%20Boosie%20Badazz,%20Future,%20Rick%20Ross%20&%20Jeezy).mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (78,'Gold Slugs','DJ Khaled',6,'http://se38.x10.mx/music/DJ/03.%20Gold%20Slugs%20(feat.%20Chris%20Brown,%20August%20Alsina%20&%20Fetty%20Wap).mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (79,'I Swear I Never Tell Another Soul','DJ Khaled',6,'http://se38.x10.mx/music/DJ/04.%20I%20Swear%20I%20Never%20Tell%20Another%20Soul%20(feat.%20Future,%20Yo%20Gotti%20&%20Trick%20Daddy).mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (80,'I Lied','DJ Khaled',6,'http://se38.x10.mx/music/DJ/05.%20I%20Lied%20(feat.%20French%20Montana,%20Meek%20Mill,%20Beanie%20Sigel%20&%20Jadakiss).mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (81,'How Many Times','DJ Khaled',6,'http://se38.x10.mx/music/DJ/06.%20How%20Many%20Times%20(feat.%20Chris%20Brown,%20Lil%20Wayne%20&%20Big%20Sean).mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (82,'You Mine','DJ Khaled',6,'http://se38.x10.mx/music/DJ/08.%20Every%20Time%20We%20Come%20Around%20(feat.%20French%20Montana,%20Jadakiss,%20Ace%20Hood%20&%20Vado).mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (83,'Every Time We Come Around','DJ Khaled',6,'http://se38.x10.mx/music/DJ/08.%20Every%20Time%20We%20Come%20Around%20(feat.%20French%20Montana,%20Jadakiss,%20Ace%20Hood%20&%20Vado).mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (84,'I Aint Worried','DJ Khaled',6,'http://se38.x10.mx/music/DJ/09.%20I%20Aint%20Worried%20(feat.%20Ace%20Hood%20&%20Rick%20Ross).mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (85,'They Dont Love You No More','DJ Khaled',6,'http://se38.x10.mx/music/DJ/10.%20They%20Dont%20Love%20You%20No%20More%20(feat.%20JAY%20Z,%20Meek%20Mill,%20Rick%20Ross%20&%20French%20Montana).mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (86,'Hold You Down','DJ Khaled',6,'http://se38.x10.mx/music/DJ/12.%20Hold%20You%20Down%20(feat.%20Chris%20Brown,%20August%20Alsina,%20Future%20&%20Jeremih).mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (87,'Most High','DJ Khaled',6,'http://se38.x10.mx/music/DJ/13.%20Most%20High%20(feat.%20John%20Legend).mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (88,'Hold You Down (Remix)','DJ Khaled',6,'http://se38.x10.mx/music/DJ/14.%20Hold%20You%20Down%20(Remix)%20%5bfeat.%20Usher,%20Rick%20Ross,%20Fabolous%20&%20Ace%20Hood%5d.mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (89,'Gold Slugs','DJ Khaled',6,'http://se38.x10.mx/music/DJ/15.%20Gold%20Slugs%20(Instrumental)%20%5bfeat.%20Chris%20Brown,%20August%20Alsina%20&%20Fetty%20Wap%5d.mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (90,'I Lied (Instrumental)','DJ Khaled',6,'http://se38.x10.mx/music/DJ/16.%20I%20Lied%20(Instrumental)%20%5bfeat.%20French%20Montana,%20Meek%20Mill,%20Beanie%20Sigel%20&%20Jadakiss%5d.mp3','Hip-Hop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (91,'Iron Lion Zion','Bob Marley',7,'http://se38.x10.mx/music/Bob1/01%20.%20Iron%20Lion%20Zion%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (92,'Could You Be Loved','Bob Marley',7,'http://se38.x10.mx/music/Bob1/02%20.%20Could%20You%20Be%20Loved%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (93,'Is This Love','Bob Marley',7,'http://se38.x10.mx/music/Bob1/03%20.%20Is%20This%20Love%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (94,'I Shot The Sherrif','Bob Marley',7,'http://se38.x10.mx/music/Bob1/04%20.%20I%20Shot%20The%20Sheriff%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (95,'Jamming','Bob Marley',7,'http://se38.x10.mx/music/Bob1/05%20.%20Jamming%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (96,'One Love','Bob Marley',7,'http://se38.x10.mx/music/Bob1/06%20.%20One%20Love%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (97,'No Woman No Cry','Bob Marley',7,'http://se38.x10.mx/music/Bob1/07%20.%20No%20Woman,%20No%20Cry%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (98,'Get Up Stand Up','Bob Marley',7,'http://se38.x10.mx/music/Bob1/08%20.%20Get%20Up%20Stand%20Up%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (99,'Buffalo Solider','Bob Marley',7,'http://se38.x10.mx/music/Bob1/09%20.%20Buffalo%20Soldier%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (100,'Sun Is Shining','Bob Marley',7,'http://se38.x10.mx/music/Bob1/11%20.%20Sun%20Is%20Shining%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (101,'Roots Rock Reggage','Bob Marley',7,'http://se38.x10.mx/music/Bob1/12%20.%20Roots%20Rock%20Reggae%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (102,'So Much Trouble In The World','Bob Marley',7,'http://se38.x10.mx/music/Bob1/13%20.%20So%20Much%20Trouble%20In%20The%20World%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (103,'Waiting In Vain','Bob Marley',7,'http://se38.x10.mx/music/Bob1/14%20.%20Waiting%20In%20Vain%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (104,'Three Little Birds','Bob Marley',7,'http://se38.x10.mx/music/Bob1/15%20.%20Three%20Little%20Birds%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (105,'Stir It Up','Bob Marley',7,'http://se38.x10.mx/music/Bob1/16%20.%20Stir%20It%20Up%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (106,'Satisfy My Soul','Bob Marley',7,'http://se38.x10.mx/music/Bob1/17%20.%20Satisfy%20My%20Soul%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (107,'Punky Reggage Party','Bob Marley',7,'http://se38.x10.mx/music/Bob1/18%20.%20Punky%20Reggae%20Party%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (108,'Exodus','Bob Marley',7,'http://se38.x10.mx/music/Bob1/10%20.%20Exodus%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (109,'Positive Vibration','Bob Marley',8,'http://se38.x10.mx/music/Bob2/01%20.%20Positive%20Vibration%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (110,'Survival','Bob Marley',8,'http://se38.x10.mx/music/Bob2/02%20.%20Survival%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (111,'Kaya','Bob Marley',8,'http://se38.x10.mx/music/Bob2/03%20.%20Kaya%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (112,'Keep On Moving','Bob Marley',8,'http://se38.x10.mx/music/Bob2/04%20.%20Keep%20On%20Moving%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (113,'Lively Up Yourself','Bob Marley',8,'http://se38.x10.mx/music/Bob2/05%20.%20Lively%20Up%20Yourself%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (114,'Concrete Jungle','Bob Marley',8,'http://se38.x10.mx/music/Bob2/06%20.%20Concrete%20Jungle%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (115,'Africe Unite','Bob Marley',8,'http://se38.x10.mx/music/Bob2/07%20.%20Africa%20Unite%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (116,'Easy Skanking','Bob Marley',8,'http://se38.x10.mx/music/Bob2/08%20.%20Easy%20Skanking%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (117,'Chant Down Babylon','Bob Marley',8,'http://se38.x10.mx/music/Bob2/09%20.%20Chant%20Down%20Babylon%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (118,'Kinky Reggage','Bob Marley',8,'http://se38.x10.mx/music/Bob2/10%20.%20Kinky%20Reggae%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (119,'Natural Music','Bob Marley',8,'http://se38.x10.mx/music/Bob2/11%20.%20Natural%20Music%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (120,'Put It On','Bob Marley',8,'http://se38.x10.mx/music/Bob2/12%20.%20Put%20It%20On%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (121,'Ride Natty Ride','Bob Marley',8,'http://se38.x10.mx/music/Bob2/13%20.%20Ride%20Natty%20Ride%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (122,'Them Belly Full','Bob Marley',8,'http://se38.x10.mx/music/Bob2/14%20.%20Them%20Belly%20Full%20(But%20We%20Hungry)%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (123,'Zion Train','Bob Marley',8,'http://se38.x10.mx/music/Bob2/15%20.%20Zion%20Train%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (124,'One Drop','Bob Marley',8,'http://se38.x10.mx/music/Bob2/16%20.%20One%20Drop%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (125,'Duppy Conqueror','Bob Marley',8,'http://se38.x10.mx/music/Bob2/17%20.%20Duppy%20Conqueror%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (126,'Rebel Music','Bob Marley',8,'http://se38.x10.mx/music/Bob2/18%20.%20Rebel%20Music%20(3%20OClock%20Road%20Block)%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (127,'Zimbabwe','Bob Marley',8,'http://se38.x10.mx/music/Bob2/19%20.%20Zimbabwe%20%20-%20Bob%20Marley.mp3','Reggae');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (128,'Be Together','Major Lazer',9,'http://se38.x10.mx/music/ML/01%20Be%20Together.mp3','Electronic');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (129,'Too Original','Major Lazer',9,'http://se38.x10.mx/music/ML/02%20Too%20Original.mp3','Electronic');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (130,'Blaze Up the Fire','Major Lazer',9,'http://se38.x10.mx/music/ML/03%20Blaze%20Up%20the%20Fire.mp3','Electronic');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (131,'Lean On','Major Lazer',9,'http://se38.x10.mx/music/ML/04%20Lean%20On.mp3','Electronic');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (132,'Powerful','Major Lazer',9,'http://se38.x10.mx/music/ML/05%20Powerful.mp3','Electronic');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (133,'Light It Up','Major Lazer',9,'http://se38.x10.mx/music/ML/06%20Light%20It%20Up.mp3','Electronic');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (134,'Roll the Bass','Major Lazer',9,'http://se38.x10.mx/music/ML/07%20Roll%20the%20Bass.mp3','Electronic');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (135,'Night Riders','Major Lazer',9,'http://se38.x10.mx/music/ML/08%20Night%20Riders.mp3','Electronic');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (136,'All My Love','Major Lazer',9,'http://se38.x10.mx/music/ML/09%20All%20My%20Love.mp3','Electronic');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (137,'Light it Up','Major Lazer',9,'http://se38.x10.mx/music/ML/10.%20Light%20it%20Up.mp3','Electronic');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (138,'MOTi','Major Lazer',9,'http://se38.x10.mx/music/ML/11.%20MOTi.mp3','Electronic');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (139,'Wave','Major Lazer',9,'http://se38.x10.mx/music/ML/12.%20Wave.mp3','Electronic');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (140,'Thunder & Lightning','Major Lazer',9,'http://se38.x10.mx/music/ML/13.%20Thunder%20&%20Lightning.mp3','Electronic');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (141,'Lost','Major Lazer',9,'http://se38.x10.mx/music/ML/14.%20Lost.mp3','Electronic');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (142,'Come Together','The Beatles',10,'http://se38.x10.mx/music/Beatle/01%20Come%20Together.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (143,'Something','The Beatles',10,'http://se38.x10.mx/music/Beatle/02%20Something.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (144,'Maxwells Silver Hammer','The Beatles',10,'http://se38.x10.mx/music/Beatle/03%20Maxwells%20Silver%20Hammer.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (145,'Oh! Darling','The Beatles',10,'http://se38.x10.mx/music/Beatle/04%20Oh!%20Darling.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (146,'Octopuss Garen','The Beatles',10,'http://se38.x10.mx/music/Beatle/05%20Octopuss%20Garden.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (147,'I Want You','The Beatles',10,'http://se38.x10.mx/music/Beatle/06%20I%20Want%20You.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (148,'Here Comes the Sun','The Beatles',10,'http://se38.x10.mx/music/Beatle/07%20Here%20Comes%20the%20Sun.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (149,'Because','The Beatles',10,'http://se38.x10.mx/music/Beatle/08%20Because.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (150,'You Never Give Me Your Money','The Beatles',10,'http://se38.x10.mx/music/Beatle/09%20You%20Never%20Give%20Me%20Your%20Money.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (151,'Sun Sking','The Beatles',10,'http://se38.x10.mx/music/Beatle/10%20Sun%20King.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (152,'Mean Mr Mustard','The Beatles',10,'http://se38.x10.mx/music/Beatle/11%20Mean%20Mr%20Mustard.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (153,'Polythene Pam','The Beatles',10,'http://se38.x10.mx/music/Beatle/12%20Polythene%20Pam.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (154,'She Came In Through the Bathroom','The Beatles',10,'http://se38.x10.mx/music/Beatle/13%20She%20Came%20In%20Through%20the%20Bathroom.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (155,'Golden Slumbers','The Beatles',10,'http://se38.x10.mx/music/Beatle/14%20Golden%20Slumbers.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (156,'Carry That Weight','The Beatles',10,'http://se38.x10.mx/music/Beatle/15%20Carry%20That%20Weight.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (157,'The End','The Beatles',10,'http://se38.x10.mx/music/Beatle/16%20The%20End.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (158,'Her Majesty','The Beatles',10,'http://se38.x10.mx/music/Beatle/17%20Her%20Majesty.mp3','Rock');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (159,'Mark My Words','Justin Bieber',11,'http://se38.x10.mx/music/JB/01.%20Mark%20My%20Words.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (160,'Ill Show You','Justin Bieber',11,'http://se38.x10.mx/music/JB/02.%20Ill%20Show%20You.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (161,'What Do You Mean','Justin Bieber',11,'http://se38.x10.mx/music/JB/03.%20What%20Do%20You%20Mean.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (162,'Sorry','Justin Bieber',11,'http://se38.x10.mx/music/JB/04.%20Sorry.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (163,'Love Yourself','Justin Bieber',11,'http://se38.x10.mx/music/JB/05.%20Love%20Yourself.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (164,'Company','Justin Bieber',11,'http://se38.x10.mx/music/JB/06.%20Company.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (165,'No Pressure','Justin Bieber',11,'http://se38.x10.mx/music/JB/07.%20No%20Pressure.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (166,'No Sense','Justin Bieber',11,'http://se38.x10.mx/music/JB/08.%20No%20Sense.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (167,'The Feeling','Justin Bieber',11,'http://se38.x10.mx/music/JB/09.%20The%20Feeling.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (168,'Life Is Worth Living','Justin Bieber',11,'http://se38.x10.mx/music/JB/10.%20Live%20Is%20Worth%20Living.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (169,'Where Are U Now','Justin Bieber',11,'http://se38.x10.mx/music/JB/11.%20Where%20Are%20U%20Now.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (170,'Children','Justin Bieber',11,'http://se38.x10.mx/music/JB/12.%20Children.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (171,'Purpose','Justin Bieber',11,'http://se38.x10.mx/music/JB/13.%20Purpose.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (172,'Been You','Justin Bieber',11,'http://se38.x10.mx/music/JB/14.%20Been%20You.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (173,'Get Used To It','Justin Bieber',11,'http://se38.x10.mx/music/JB/15.%20Get%20Used%20To%20Me.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (174,'We Are','Justin Bieber',11,'http://se38.x10.mx/music/JB/16.%20We%20Are.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (175,'Trust','Justin Bieber',11,'http://se38.x10.mx/music/JB/17.%20Trust.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (176,'All in It','Justin Bieber',11,'http://se38.x10.mx/music/JB/18.%20All%20In%20It.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (177,'Hit The Ground','Justin Bieber',11,'http://se38.x10.mx/music/JB/19.%20Hit%20the%20Ground.mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (178,'The Most','Justin Bieber',11,'http://se38.x10.mx/music/JB/20.%20The%20Most.mp3','Dance');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (179,'What Do You Mean (Remix)','Justin Bieber',11,'http://se38.x10.mx/music/JB/21.%20What%20Do%20You%20Mean%20(Remix).mp3','Pop');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (180,'Real Life','The Weeknd',12,'http://se38.x10.mx/music/Weekend/01%20Real%20Life.mp3','R&B');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (181,'Losers','The Weeknd',12,'http://se38.x10.mx/music/Weekend/02%20Losers.mp3','R&B');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (182,'Tell Your Friends','The Weeknd',12,'http://se38.x10.mx/music/Weekend/03%20Tell%20Your%20Friends.mp3','R&B');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (183,'Often','The Weeknd',12,'http://se38.x10.mx/music/Weekend/04%20Often.mp3','R&B');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (184,'The Hills','The Weeknd',12,'http://se38.x10.mx/music/Weekend/05%20The%20Hills.mp3','R&B');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (185,'Acquainted','The Weeknd',12,'http://se38.x10.mx/music/Weekend/06%20Acquainted.mp3','R&B');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (186,'Cant Feel My Face','The Weeknd',12,'http://se38.x10.mx/music/Weekend/07%20Cant%20Feel%20My%20Face.mp3','R&B');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (187,'ShameLess','The Weeknd',12,'http://se38.x10.mx/music/Weekend/08%20Shameless.mp3','R&B');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (188,'Earned It','The Weeknd',12,'http://se38.x10.mx/music/Weekend/09%20Earned%20It.mp3','R&B');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (189,'In The Night','The Weeknd',12,'http://se38.x10.mx/music/Weekend/10%20In%20The%20Night.mp3','R&B');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (190,'As You Are','The Weeknd',12,'http://se38.x10.mx/music/Weekend/11%20As%20You%20Are.mp3','R&B');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (191,'Dark Times','The Weeknd',12,'http://se38.x10.mx/music/Weekend/12%20Dark%20Times.mp3','R&B');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (192,'Prisioner','The Weeknd',12,'http://se38.x10.mx/music/Weekend/13%20Prisoner.mp3','R&B');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (193,'Angel','The Weeknd',12,'http://se38.x10.mx/music/Weekend/14%20Angel.mp3','R&B');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (194,'Faith','Calvin Harris',13,'http://se38.x10.mx/music/CH/01%20Faith.mp3','House');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (195,'Under Control','Calvin Harris',13,'http://se38.x10.mx/music/CH/02%20Under%20Control.mp3','House');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (196,'Blame','Calvin Harris',13,'http://se38.x10.mx/music/CH/03%20Blame.mp3','House');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (197,'Love Now','Calvin Harris',13,'http://se38.x10.mx/music/CH/04%20Love%20Now.mp3','House');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (198,'Slow Acid','Calvin Harris',13,'http://se38.x10.mx/music/CH/05%20Slow%20Acid.mp3','House');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (199,'Outside','Calvin Harris',13,'http://se38.x10.mx/music/CH/06%20Outside.mp3','House');
INSERT INTO `Track` (TrackID,name,artist,AlbumID,trackPath,genre) VALUES (200,'It Was You','Calvin Harris',13,'http://se38.x10.mx/music/CH/07%20It%20Was%20You.mp3','House');
CREATE TABLE Playlist (PlaylistID  INTEGER NOT NULL PRIMARY KEY, name varchar(255) NOT NULL, playlistType varchar(255) NOT NULL, CustomerID integer(10) NOT NULL, FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID));
INSERT INTO `Playlist` (PlaylistID,name,playlistType,CustomerID) VALUES (1,'Gym','friend',2);
INSERT INTO `Playlist` (PlaylistID,name,playlistType,CustomerID) VALUES (2,'Work','Private',2);
INSERT INTO `Playlist` (PlaylistID,name,playlistType,CustomerID) VALUES (3,'Workout','friend',3);
INSERT INTO `Playlist` (PlaylistID,name,playlistType,CustomerID) VALUES (4,'Home','friend',4);
INSERT INTO `Playlist` (PlaylistID,name,playlistType,CustomerID) VALUES (5,'Workout','friend',5);
CREATE TABLE "Paypal" (
	`PaypalID`	INTEGER NOT NULL,
	`email`	varchar(255) NOT NULL,
	`password`	varchar(255) NOT NULL,
	PRIMARY KEY(PaypalID),
	FOREIGN KEY(`PaypalID`) REFERENCES `PaymentType`(`PaymentID`)
);
INSERT INTO `Paypal` (PaypalID,email,password) VALUES (1,'steve@gmail.com','steve');
INSERT INTO `Paypal` (PaypalID,email,password) VALUES (2,'bob@gmail.com','plookify');
INSERT INTO `Paypal` (PaypalID,email,password) VALUES (4,'ray@gmai.com','ray');
CREATE TABLE "PaymentType" (
	`isCreditCard`	integer(1) NOT NULL,
	`CustomerID`	integer(10) NOT NULL,
	`PaymentID`	INTEGER NOT NULL,
	`hasPaid`	integer(1) NOT NULL,
	`payBy`	INTEGER NOT NULL,
	PRIMARY KEY(PaymentID),
	FOREIGN KEY(`CustomerID`) REFERENCES `Customer`(`CustomerID`)
);
INSERT INTO `PaymentType` (isCreditCard,CustomerID,PaymentID,hasPaid,payBy) VALUES (0,2,1,1,1461476765907);
INSERT INTO `PaymentType` (isCreditCard,CustomerID,PaymentID,hasPaid,payBy) VALUES (0,1,2,1,1461476881556);
INSERT INTO `PaymentType` (isCreditCard,CustomerID,PaymentID,hasPaid,payBy) VALUES (1,3,3,1,1461477877944);
INSERT INTO `PaymentType` (isCreditCard,CustomerID,PaymentID,hasPaid,payBy) VALUES (0,4,4,1,1461478120422);
CREATE TABLE "Owns" (
	`CustomerID`	integer(10) NOT NULL,
	`DeviceID`	INTEGER NOT NULL,
	FOREIGN KEY(`CustomerID`) REFERENCES `Customer`(`CustomerID`),
	FOREIGN KEY(`DeviceID`) REFERENCES `Device`(`DeviceID`)
);
INSERT INTO `Owns` (CustomerID,DeviceID) VALUES (2,201);
INSERT INTO `Owns` (CustomerID,DeviceID) VALUES (2,202);
INSERT INTO `Owns` (CustomerID,DeviceID) VALUES (4,401);
INSERT INTO `Owns` (CustomerID,DeviceID) VALUES (4,402);
INSERT INTO `Owns` (CustomerID,DeviceID) VALUES (5,501);
CREATE TABLE "NowPlaying" (
	`CustomerID`	INTEGER NOT NULL,
	`TrackID`	INTEGER NOT NULL,
	FOREIGN KEY(`CustomerID`) REFERENCES `Customer`(`CustomerID`),
	FOREIGN KEY(`TrackID`) REFERENCES Track(TrackID)
);
INSERT INTO `NowPlaying` (CustomerID,TrackID) VALUES (1,36);
INSERT INTO `NowPlaying` (CustomerID,TrackID) VALUES (1,38);
INSERT INTO `NowPlaying` (CustomerID,TrackID) VALUES (1,39);
CREATE TABLE "Inbox" (
	`SentFromID`	integer(10) NOT NULL,
	`SentToID`	integer(10) NOT NULL,
	`Message`	varchar(255) NOT NULL,
	`date`	TEXT NOT NULL,
	FOREIGN KEY(`SentFromID`) REFERENCES `Customer`(`CustomerID`),
	FOREIGN KEY(`SentToID`) REFERENCES `Customer`(`CustomerID`)
);
CREATE TABLE Friend (CustomerID integer(10) NOT NULL, CustomerIDFriend integer(10) NOT NULL, hasAccepted integer(1) NOT NULL, FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID), FOREIGN KEY(CustomerIDFriend) REFERENCES Customer(CustomerID));
INSERT INTO `Friend` (CustomerID,CustomerIDFriend,hasAccepted) VALUES (2,1,1);
INSERT INTO `Friend` (CustomerID,CustomerIDFriend,hasAccepted) VALUES (1,2,1);
INSERT INTO `Friend` (CustomerID,CustomerIDFriend,hasAccepted) VALUES (3,1,1);
INSERT INTO `Friend` (CustomerID,CustomerIDFriend,hasAccepted) VALUES (1,3,1);
CREATE TABLE Device (DeviceID  INTEGER NOT NULL PRIMARY KEY, name varchar(255) NOT NULL, FOREIGN KEY(DeviceID) REFERENCES Owns(DeviceID));
INSERT INTO `Device` (DeviceID,name) VALUES (201,'Iphone 6');
INSERT INTO `Device` (DeviceID,name) VALUES (202,'IPad');
INSERT INTO `Device` (DeviceID,name) VALUES (401,'Samsung Galaxy');
INSERT INTO `Device` (DeviceID,name) VALUES (402,'Laptop');
INSERT INTO `Device` (DeviceID,name) VALUES (501,'Iphone 5S');
CREATE TABLE "Customer" (
	`CustomerID`	INTEGER NOT NULL,
	`name`	varchar(255) NOT NULL,
	`PaymentID`	integer(10) NOT NULL,
	`number`	TEXT,
	`email`	varchar(255),
	`isSubscribed`	INTEGER NOT NULL,
	`isDiscoverable`	INTEGER NOT NULL,
	`lastReplaceDate`	INTEGER NOT NULL,
	`username`	TEXT,
	`password`	TEXT,
	`addressLine1`	TEXT,
	`postCode`	TEXT,
	`surname`	TEXT,
	`deadLine`	INTEGER,
	PRIMARY KEY(CustomerID)
);
INSERT INTO `Customer` (CustomerID,name,PaymentID,number,email,isSubscribed,isDiscoverable,lastReplaceDate,username,password,addressLine1,postCode,surname,deadLine) VALUES (1,'Bob',2,'07069464535','bob@gmail.com',1,1,1453487820,'Bob','plookify','12','e12 8hw','Smith',1461476881556);
INSERT INTO `Customer` (CustomerID,name,PaymentID,number,email,isSubscribed,isDiscoverable,lastReplaceDate,username,password,addressLine1,postCode,surname,deadLine) VALUES (2,'Steve',1,'07889 839174','steve@gmai.com',1,0,1458846911229,'steve','steve','2','ME16 5FD','Blanket',1461476765907);
INSERT INTO `Customer` (CustomerID,name,PaymentID,number,email,isSubscribed,isDiscoverable,lastReplaceDate,username,password,addressLine1,postCode,surname,deadLine) VALUES (3,'John',3,'06876 182937','john@gmail.com',1,0,1458848085586,'john','john','67','E20 3DS','Stevenson',1461477877944);
INSERT INTO `Customer` (CustomerID,name,PaymentID,number,email,isSubscribed,isDiscoverable,lastReplaceDate,username,password,addressLine1,postCode,surname,deadLine) VALUES (4,'Ray',4,'07664 652162','ray@gmail.com',1,0,1458848258198,'ray','ray','21','ME17 5FD','Dell',1461478120422);
INSERT INTO `Customer` (CustomerID,name,PaymentID,number,email,isSubscribed,isDiscoverable,lastReplaceDate,username,password,addressLine1,postCode,surname,deadLine) VALUES (5,'Emily',-1,'07865 172635','emily@gmail.com',0,0,1458848457828,'emily','emily','55 Mitchell Road','E3 4NH','Banks',NULL);
CREATE TABLE "CreditCard" (
	`CreditCardID`	INTEGER NOT NULL,
	`cardNumber`	integer(20) NOT NULL,
	`securityCode`	integer(3) NOT NULL,
	`expDate`	TEXT NOT NULL,
	`name`	TEXT NOT NULL,
	PRIMARY KEY(CreditCardID),
	FOREIGN KEY(`CreditCardID`) REFERENCES `PaymentType`(`PaymentID`)
);
INSERT INTO `CreditCard` (CreditCardID,cardNumber,securityCode,expDate,name) VALUES (3,172847292712,876,'10/19','John Stevenson');
CREATE TABLE Artist (ArtistID  INTEGER NOT NULL PRIMARY KEY, name varchar(255) NOT NULL, genre varchar(255) NOT NULL);
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (1,'Eminem','Rap');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (2,'Drake','Rap');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (3,'Taylor Swift','Dance');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (4,'Avicii','Dance');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (5,'Blackmill','Electronic');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (6,'DJ Khaled','Hip-Hop');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (7,'Bob Marley','Reggae');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (8,'Major Lazer','Dance');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (9,'The Beatles','Rock');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (10,'Justin Bieber','R&B');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (11,'The Weeknd','R&B');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (12,'Calvin Harris','Dance');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (13,'Drake','Hip-Hop');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (14,'The Weekend','Hip-Hop');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (15,'Justin Bieber','Dance');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (16,'Justin Bieber','Pop');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (17,'Major Lazer','Reggae');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (18,'Major Lazer','Dance');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (19,'Calvin Harris','Rock');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (20,'Calvin Harris','Electronic');
INSERT INTO `Artist` (ArtistID,name,genre) VALUES (21,'Taylor Swift','Pop');
CREATE TABLE Album (AlbumID  INTEGER NOT NULL PRIMARY KEY, ArtistID integer(10) NOT NULL, name varchar(255) NOT NULL, FOREIGN KEY(ArtistID) REFERENCES Artist(ArtistID));
INSERT INTO `Album` (AlbumID,ArtistID,name) VALUES (1,'Eminem','EminemCD1');
INSERT INTO `Album` (AlbumID,ArtistID,name) VALUES (2,'Drake','DrakeCD1');
INSERT INTO `Album` (AlbumID,ArtistID,name) VALUES (3,'Taylor Swift','TaylorCD1');
INSERT INTO `Album` (AlbumID,ArtistID,name) VALUES (4,'Avicii','AviciiCD1');
INSERT INTO `Album` (AlbumID,ArtistID,name) VALUES (5,'Blackmill','BlackmillCD1');
INSERT INTO `Album` (AlbumID,ArtistID,name) VALUES (6,'DJ Khaled','DJCD1');
INSERT INTO `Album` (AlbumID,ArtistID,name) VALUES (7,'Bob Marley','BobCD1');
INSERT INTO `Album` (AlbumID,ArtistID,name) VALUES (8,'Bob Marley','BobCD2');
INSERT INTO `Album` (AlbumID,ArtistID,name) VALUES (9,'Major Lazer','MajorCD1');
INSERT INTO `Album` (AlbumID,ArtistID,name) VALUES (10,'The Beatles','BeatlesCD1');
INSERT INTO `Album` (AlbumID,ArtistID,name) VALUES (11,'Justin Bieber','JustinCD1');
INSERT INTO `Album` (AlbumID,ArtistID,name) VALUES (12,'The Weeknd','WeekndCD1');
INSERT INTO `Album` (AlbumID,ArtistID,name) VALUES (13,'Calvin Harris','CalvinCD1');
COMMIT;
