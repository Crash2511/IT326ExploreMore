CREATE TABLE users ( -- user data
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(32) NOT NULL UNIQUE,
    password VARCHAR(32) NOT NULL,
    fname VARCHAR(16),
    lname VARCHAR(16)
);
CREATE TABLE activities ( -- specific slots that comprise the schedule
    id INT PRIMARY KEY AUTO_INCREMENT, -- unique id for every activity
    uid INT NOT NULL, -- linked to user that has this activity in their schedule
    activityName VARCHAR(32) NOT NULL, -- name of activity (park, gym, etc)
    activityDesc TEXT, -- description of activity
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE -- linked to user
);
CREATE TABLE schedules ( -- for the schedule
    id INT PRIMARY KEY AUTO_INCREMENT, -- schedule id
    uid INT NOT NULL, -- user that has that schedule
    aid INT NOT NULL, -- activity associated with the schedule (sched can have multiple)
    startDate DATE NOT NULL,
    startT TIME NOT NULL, -- beginning of schedule
    endT TIME NOT NULL, -- last activity on schedule
    FOREIGN KEY (uid) REFERENCES users(id) ON DELETE CASCADE, -- need this (linking)
    FOREIGN KEY (aid) REFERENCES activities(id) ON DELETE CASCADE -- also need
);
