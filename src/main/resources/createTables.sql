CREATE TABLE `issues` (
	`id`	INTEGER,
	`description`	TEXT,
	PRIMARY KEY(`id`)
);

CREATE TABLE `users` (
	`id`	INTEGER,
	`name`	TEXT UNIQUE,
	PRIMARY KEY(`id`)
);

CREATE TABLE `projects` (
	`id`	INTEGER,
	`name`	TEXT,
	`user_id`	INTEGER NOT NULL,
	`issue_id`	INTEGER NOT NULL,
	FOREIGN KEY(`user_id`) REFERENCES `users`(`id`),
	FOREIGN KEY(`issue_id`) REFERENCES `issues`(`id`),
	PRIMARY KEY(`id`)
);