insert into issues(description) values('bug in entering a name');
insert into issues(description) values('bug in output to console');
insert into issues(description) values('bug in output to user interface');
insert into issues(description) values('bug in send file');

insert into users(name) values('Anton');
insert into users(name) values('Boris');
insert into users(name) values('Tom');

insert into projects(name, user_id, issue_id) values('Project_1', 1, 1);
insert into projects(name, user_id, issue_id) values('Project_2', 1, 2);
insert into projects(name, user_id, issue_id) values('Project_3', 2, 1);
insert into projects(name, user_id, issue_id) values('Project_1', 1, 3);
insert into projects(name, user_id, issue_id) values('Project_1', 1, 4);
insert into projects(name, user_id, issue_id) values('Project_3', 3, 3);
insert into projects(name, user_id, issue_id) values('Project_3', 3, 4);