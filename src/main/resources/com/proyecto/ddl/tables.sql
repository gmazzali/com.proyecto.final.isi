DROP TABLE IF EXISTS Rules_in_sets;
DROP TABLE IF EXISTS Rules_Sets;
DROP TABLE IF EXISTS Rules;

CREATE TABLE Rules_in_sets
(
	id_rule INTEGER UNSIGNED NOT NULL,
	id_rule_set INTEGER UNSIGNED NOT NULL,
	PRIMARY KEY (id_rule, id_rule_set),
	KEY (id_rule),
	KEY (id_rule_set)
) ;


CREATE TABLE Rules_Sets
(
	id_rule_set INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	description VARCHAR(100) NOT NULL,
	active BOOL NOT NULL,
	PRIMARY KEY (id_rule_set)
) ;


CREATE TABLE Rules
(
	id_rule INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	description VARCHAR(100) NOT NULL,
	rule TEXT NOT NULL,
	PRIMARY KEY (id_rule)
) ;

ALTER TABLE Rules_in_sets ADD CONSTRAINT FK_Rules_in_sets_Rules 
	FOREIGN KEY (id_rule) REFERENCES Rules (id_rule)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE Rules_in_sets ADD CONSTRAINT FK_Rules_in_sets_Rules_Sets 
	FOREIGN KEY (id_rule_set) REFERENCES Rules_Sets (id_rule_set)
	ON DELETE CASCADE ON UPDATE CASCADE;