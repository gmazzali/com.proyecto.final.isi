#####################################################
# Las tablas de las reglas y los conjuntos de estas #
#####################################################

DROP TABLE IF EXISTS Rules_in_sets;
DROP TABLE IF EXISTS Rules_Sets;
DROP TABLE IF EXISTS Rules;

CREATE TABLE Rules_in_sets (
	id_rule INTEGER UNSIGNED NOT NULL,
	id_rule_set INTEGER UNSIGNED NOT NULL,
	PRIMARY KEY (id_rule, id_rule_set),
	KEY (id_rule),
	KEY (id_rule_set)
);

CREATE TABLE Rules_Sets (
	id_rule_set INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	description VARCHAR(100) NOT NULL,
	active BOOL NOT NULL,
	PRIMARY KEY (id_rule_set)
);

CREATE TABLE Rules (
	id_rule INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	description VARCHAR(100) NOT NULL,
	rule TEXT NOT NULL,
	PRIMARY KEY (id_rule)
);

ALTER TABLE Rules_in_sets ADD CONSTRAINT FK_Rules_in_sets_Rules 
	FOREIGN KEY (id_rule) REFERENCES Rules (id_rule)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE Rules_in_sets ADD CONSTRAINT FK_Rules_in_sets_Rules_Sets 
	FOREIGN KEY (id_rule_set) REFERENCES Rules_Sets (id_rule_set)
	ON DELETE CASCADE ON UPDATE CASCADE;
	
###########################################################
# Las tablas que corresponde a los agentes y las materias #
###########################################################

DROP TABLE IF EXISTS Agents_Subjects;
DROP TABLE IF EXISTS Subjects;
DROP TABLE IF EXISTS Agents;

CREATE TABLE Agents_Subjects (
	id_agent INTEGER UNSIGNED NOT NULL,
	id_subject INTEGER UNSIGNED NOT NULL,
	PRIMARY KEY (id_agent, id_subject),
	KEY (id_subject),
	KEY (id_agent)
);

CREATE TABLE Subjects (
	id_subject INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	name VARCHAR(75) NOT NULL,
	PRIMARY KEY (id_subject)
);

CREATE TABLE Agents (
	id_agent INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	PRIMARY KEY (id_agent)
);

ALTER TABLE Agents_Subjects ADD CONSTRAINT FK_Agents_Subjects_Subjects 
	FOREIGN KEY (id_subject) REFERENCES Subjects (id_subject)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE Agents_Subjects ADD CONSTRAINT FK_Agents_Subjects_Agents 
	FOREIGN KEY (id_agent) REFERENCES Agents (id_agent)
	ON DELETE CASCADE ON UPDATE CASCADE;
