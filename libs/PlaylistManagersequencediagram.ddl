CREATE TABLE Track (TrackID  INTEGER NOT NULL PRIMARY KEY, name varchar(255) NOT NULL, artist varchar(255) NOT NULL, length double(10) NOT NULL, AlbumID integer(10) NOT NULL, FOREIGN KEY(AlbumID) REFERENCES Album(AlbumID));
CREATE TABLE Playlist (PlaylistID  INTEGER NOT NULL PRIMARY KEY, name varchar(255) NOT NULL, playlistType varchar(255) NOT NULL, CustomerID integer(10) NOT NULL, FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID));
CREATE TABLE TrackPlaylist (TrackID integer(10) NOT NULL, playlistID integer(10) NOT NULL, dateAdded date NOT NULL, TrackPlaylistID integer(10) NOT NULL, PRIMARY KEY (TrackID, playlistID, TrackPlaylistID), FOREIGN KEY(TrackID) REFERENCES Track(TrackID), FOREIGN KEY(playlistID) REFERENCES Playlist(PlaylistID));
CREATE TABLE Artist (ArtistID  INTEGER NOT NULL PRIMARY KEY, name varchar(255) NOT NULL, genre varchar(255) NOT NULL);
CREATE TABLE Album (AlbumID  INTEGER NOT NULL PRIMARY KEY, ArtistID integer(10) NOT NULL, name varchar(255) NOT NULL, FOREIGN KEY(ArtistID) REFERENCES Artist(ArtistID));
CREATE TABLE Customer (CustomerID  INTEGER NOT NULL PRIMARY KEY, name varchar(255) NOT NULL, PaymentID integer(10) NOT NULL, AddressID integer(10) NOT NULL, number integer(14), email varchar(255), isSubscribed integer(1) NOT NULL, isDiscoverable integer(1) NOT NULL, lastReplaceDate date NOT NULL, FOREIGN KEY(AddressID) REFERENCES Address(AddressID), FOREIGN KEY() REFERENCES Friend());
CREATE TABLE Address (AddressID  INTEGER NOT NULL PRIMARY KEY, line1 varchar(255) NOT NULL, line2 varchar(255), townCity varchar(255), county varchar(255), postcode varchar(255) NOT NULL);
CREATE TABLE Owns (CustomerID integer(10) NOT NULL, DeviceID integer(10) NOT NULL, FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID), FOREIGN KEY(DeviceID) REFERENCES Device(DeviceID));
CREATE TABLE Device (DeviceID  INTEGER NOT NULL PRIMARY KEY, name varchar(255) NOT NULL);
CREATE TABLE Friend (CustomerID integer(10) NOT NULL, CustomerIDFriend integer(10) NOT NULL, hasAccepted integer(1) NOT NULL, FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID));
CREATE TABLE Messages (MessageID  INTEGER NOT NULL PRIMARY KEY, subject integer(10), content varchar(255), FOREIGN KEY() REFERENCES Inbox());
CREATE TABLE Inbox (MessageID integer(10) NOT NULL, SentFromID integer(10) NOT NULL, SentToID integer(10) NOT NULL, "date" date NOT NULL, FOREIGN KEY(SentToID) REFERENCES Customer(CustomerID));
CREATE TABLE PaymentType (isCreditCard integer(1) NOT NULL, CustomerID integer(10) NOT NULL, PaymentID integer(10) NOT NULL, hasPaid integer(1) NOT NULL, FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID));
CREATE TABLE Paypal (PaypalID  INTEGER NOT NULL PRIMARY KEY, email varchar(255) NOT NULL, password varchar(255) NOT NULL, FOREIGN KEY() REFERENCES PaymentType());
CREATE TABLE CreditCard (CreditCardID  INTEGER NOT NULL PRIMARY KEY, cardNumber integer(20) NOT NULL, securityCode integer(3) NOT NULL, expDate date NOT NULL, FOREIGN KEY() REFERENCES PaymentType());