create table BL_Author (
	uuid_ VARCHAR(75) null,
	authorId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	firstName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	birthDate DATE null,
	email VARCHAR(75) null
);

create table BL_Book (
	uuid_ VARCHAR(75) null,
	bookId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title VARCHAR(75) null,
	isbn VARCHAR(75) null,
	releaseDate DATE null
);

create table BL_Book_Author (
	authorId LONG not null,
	bookId LONG not null,
	primary key (authorId, bookId)
);