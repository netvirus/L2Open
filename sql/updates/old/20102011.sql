ALTER TABLE `mail` ADD COLUMN `hideSender` TINYINT UNSIGNED NOT NULL DEFAULT 0 AFTER `unread`;
