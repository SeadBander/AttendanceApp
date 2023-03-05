CREATE TABLE `users` (
                         `user_id` int(11) NOT NULL AUTO_INCREMENT,
                         `jmbg` int(15) NOT NULL,
                         `username` varchar(50) NOT NULL,
                         `password` varchar(50) NOT NULL,
                         `role` varchar(50) NOT NULL,
                         `isActive` varchar(50) NOT NULL,
                         `first_name` varchar(50) NOT NULL,
                         `last_name` varchar(50) NOT NULL,
                         `email` varchar(50) NOT NULL,
                         PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `timeentry` (
                             `timeentry_id` int(11) NOT NULL AUTO_INCREMENT,
                             `clocks_in` varchar(50) NOT NULL,
                             `clocks_out` varchar(50) NOT NULL,
                             `user_id` int(11) NOT NULL,
                             PRIMARY KEY (`timeentry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
