Drop table if exists Comments;
Drop table if exists Bids ;
Drop table if exists Items;
Drop table if exists Users;


CREATE Table Users (
	user_id serial primary key,
	username char(24) unique not null,
	password char(24) not null,
	isAdmin bool default false,
	userImage char(255)
	
);

Create Table Items (
	item_id serial primary key,
	user_owner int references Users(user_id),
	itemName char(255) unique not null,
	itemDescription char(1023),
	itemImage char(255),
	isApproved bool default false
	
);

Create Table Bids (
	bid_id serial primary key,
	user_bidder int references Users(user_id),
	item_bidFor int references Items(item_id),
	amount int not null,
	bidTime timestamp
);



Create Table Comments (
		comment_id serial primary key,
		user_commenter int references Users(user_id),
		item_commentOn int references Items(item_id),
		content char(1023),
		commentTime timestamp
	);


insert into Users values(default, 'Ryan', 'pass1');
insert into Users values(default, 'Daniel', 'pass2', true);
insert into Users values(default, 'Emmett', 'pass3', false);
insert into Users values(default, 'Alexander', 'pass4', true);