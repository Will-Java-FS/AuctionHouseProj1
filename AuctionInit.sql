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


insert into Users values(1, 'Ryan', 'pass1');
insert into Users values(2, 'Daniel', 'pass2', true);
insert into Users values(3, 'Emmett', 'pass3', false);
insert into Users values(4, 'Alexander', 'pass4', true);

insert into Items values(1, 1, 'The Persistence of Memory', 'by Salvador Dali', 'https://www.singulart.com/blog/wp-content/uploads/2019/08/the-persistence-of-memory-1931-1140x867.jpg', true)
insert into Items values(2, 2, 'Cafe Terrace at Night', 'by Vincent Van Gogh', 'https://www.vangoghstudio.com/Files/6/102000/102147/FileBrowser/paintings/cafe-terrace-reproduction.jpg', true)
insert into Items values(3, 3, 'The Last Supper', 'by Leonardo Da Vinci', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/The_Last_Supper_-_Leonardo_Da_Vinci_-_High_Resolution_32x16.jpg/640px-The_Last_Supper_-_Leonardo_Da_Vinci_-_High_Resolution_32x16.jpg', true)


insert into Bids values(1,1,2, 150, '2024-8-18 12:20:55')
insert into Bids values(2,1,3, 500, '2024-8-10 00:10:43')
insert into Bids values(3,2,1, 650, '2024-8-17 09:13:14')
insert into Bids values(4,2,3, 200, '2024-8-01 05:54:54')
insert into Bids values(5,3,1, 100, '2024-8-12 07:26:42')
insert into Bids values(6,3,2, 100, '2024-8-17 13:34:52')

insert into Comments values(1,1,3,'Cool item!','2024-8-12 12:24:43')
insert into Comments values(2,2,1,'Cool item!','2024-8-02 12:33:19')
insert into Comments values(3,3,2,'Cool item!','2024-8-12 18:5:35')