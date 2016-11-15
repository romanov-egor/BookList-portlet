create index IX_C54CC64 on BL_Author (groupId);
create index IX_AA3A27EE on BL_Author (uuid_);
create index IX_A2593B7A on BL_Author (uuid_, companyId);
create unique index IX_260E587C on BL_Author (uuid_, groupId);

create index IX_809D9B06 on BL_Book (groupId);
create index IX_E24A3710 on BL_Book (uuid_);
create index IX_2E91C318 on BL_Book (uuid_, companyId);
create unique index IX_BF602F9A on BL_Book (uuid_, groupId);

create index IX_CEC39EEC on BL_Book_Author (authorId);
create index IX_427F164A on BL_Book_Author (bookId);