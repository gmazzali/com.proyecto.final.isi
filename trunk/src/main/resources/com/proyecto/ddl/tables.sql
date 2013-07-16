#####################################################
# Las tablas de las reglas y los conjuntos de estas #
#####################################################

DROP TABLE IF EXISTS RULES_IN_SETS;
DROP TABLE IF EXISTS RULES_SETS;
DROP TABLE IF EXISTS RULES;

CREATE TABLE RULES_IN_SETS (
	ID_RULE INTEGER UNSIGNED NOT NULL,
	ID_RULE_SET INTEGER UNSIGNED NOT NULL,
	PRIMARY KEY (ID_RULE, ID_RULE_SET),
	KEY (ID_RULE),
	KEY (ID_RULE_SET)
);

CREATE TABLE RULES_SETS (
	ID_RULE_SET INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	DESCRIPTION VARCHAR(100) NOT NULL,
	ACTIVE BOOL NOT NULL,
	PRIMARY KEY (ID_RULE_SET)
);

CREATE TABLE RULES (
	ID_RULE INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	DESCRIPTION VARCHAR(100) NOT NULL,
	RULE TEXT NOT NULL,
	PRIMARY KEY (ID_RULE)
);

ALTER TABLE RULES_IN_SETS ADD CONSTRAINT FK_RULES_IN_SETS_RULES 
	FOREIGN KEY (ID_RULE) REFERENCES RULES (ID_RULE)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE RULES_IN_SETS ADD CONSTRAINT FK_RULES_IN_SETS_RULES_SETS 
	FOREIGN KEY (ID_RULE_SET) REFERENCES RULES_SETS (ID_RULE_SET)
	ON DELETE CASCADE ON UPDATE CASCADE;
	
	
###########################################################
# Las tablas que corresponde a los agentes y las materias #
###########################################################

DROP TABLE IF EXISTS AGENTS_IN_SUBJECTS;
DROP TABLE IF EXISTS SUBJECTS;
DROP TABLE IF EXISTS AGENTS;

CREATE TABLE AGENTS_IN_SUBJECTS (
	ID_AGENT INTEGER UNSIGNED NOT NULL,
	ID_SUBJECT INTEGER UNSIGNED NOT NULL,
	PRIMARY KEY (ID_AGENT, ID_SUBJECT),
	KEY (ID_SUBJECT),
	KEY (ID_AGENT)
);

CREATE TABLE SUBJECTS (
	ID_SUBJECT INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(75) NOT NULL,
	PRIMARY KEY (ID_SUBJECT)
);

CREATE TABLE AGENTS (
	ID_AGENT INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(50) NOT NULL,
	PRIMARY KEY (ID_AGENT)
);

ALTER TABLE AGENTS_IN_SUBJECTS ADD CONSTRAINT FK_AGENTS_IN_SUBJECTS_SUBJECTS 
	FOREIGN KEY (ID_SUBJECT) REFERENCES SUBJECTS (ID_SUBJECT)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE AGENTS_IN_SUBJECTS ADD CONSTRAINT FK_AGENTS_IN_SUBJECTS_AGENTS 
	FOREIGN KEY (ID_AGENT) REFERENCES AGENTS (ID_AGENT)
	ON DELETE CASCADE ON UPDATE CASCADE;
	
	
###########################################################
###### Las tablas que corresponde a los instrumentos ######
###########################################################

DROP TABLE IF EXISTS SEMIFORMAL_INSTRUMENTS;
DROP TABLE IF EXISTS UNRESTRICTED_ESSAY_INSTRUMENTS;
DROP TABLE IF EXISTS RESTRICTED_ESSAY_INSTRUMENTS;
DROP TABLE IF EXISTS ESSAY_INSTRUMENTS;
DROP TABLE IF EXISTS COMPLETION_INSTRUMENTS;
DROP TABLE IF EXISTS MULTIPLE_CHOICE_INSTRUMENTS;
DROP TABLE IF EXISTS SINGLE_CHOICE_INSTRUMENTS;
DROP TABLE IF EXISTS CHOICE_INSTRUMENTS;
DROP TABLE IF EXISTS CORRESPONDENCE_INSTRUMENTS;
DROP TABLE IF EXISTS OBJECTIVE_INSTRUMENTS;
DROP TABLE IF EXISTS FORMAL_INSTRUMENTS;
DROP TABLE IF EXISTS INSTRUMENTS;

CREATE TABLE SEMIFORMAL_INSTRUMENTS (
	ID_INSTRUMENT INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (ID_INSTRUMENT)
);

CREATE TABLE UNRESTRICTED_ESSAY_INSTRUMENTS (
	ID_INSTRUMENT INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (ID_INSTRUMENT)
);

CREATE TABLE RESTRICTED_ESSAY_INSTRUMENTS (
	ID_INSTRUMENT INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (ID_INSTRUMENT)
);

CREATE TABLE ESSAY_INSTRUMENTS (
	ID_INSTRUMENT INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (ID_INSTRUMENT)
);

CREATE TABLE COMPLETION_INSTRUMENTS (
	ID_INSTRUMENT INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (ID_INSTRUMENT)
);

CREATE TABLE MULTIPLE_CHOICE_INSTRUMENTS (
	ID_INSTRUMENT INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (ID_INSTRUMENT)
);

CREATE TABLE SINGLE_CHOICE_INSTRUMENTS (
	ID_INSTRUMENT INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (ID_INSTRUMENT)
);

CREATE TABLE CHOICE_INSTRUMENTS (
	ID_INSTRUMENT INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (ID_INSTRUMENT)
);

CREATE TABLE CORRESPONDENCE_INSTRUMENTS (
	ID_INSTRUMENT INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (ID_INSTRUMENT)
);

CREATE TABLE OBJECTIVE_INSTRUMENTS (
	ID_INSTRUMENT INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (ID_INSTRUMENT)
);

CREATE TABLE FORMAL_INSTRUMENTS (
	ID_INSTRUMENT INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (ID_INSTRUMENT)
);

CREATE TABLE INSTRUMENTS (
	ID_INSTRUMENT INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	DESCRIPTION TEXT NOT NULL,
	PRIMARY KEY (ID_INSTRUMENT)
);

ALTER TABLE SEMIFORMAL_INSTRUMENTS ADD CONSTRAINT FK_SEMIFORMAL_INSTRUMENTS_INSTRUMENTS 
	FOREIGN KEY (ID_INSTRUMENT) REFERENCES INSTRUMENTS (ID_INSTRUMENT)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE UNRESTRICTED_ESSAY_INSTRUMENTS ADD CONSTRAINT FK_UNRESTRICTED_ESSAY_INSTRUMENTS_ESSAY_INSTRUMENTS 
	FOREIGN KEY (ID_INSTRUMENT) REFERENCES ESSAY_INSTRUMENTS (ID_INSTRUMENT)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE RESTRICTED_ESSAY_INSTRUMENTS ADD CONSTRAINT FK_RESTRICTED_ESSAY_INSTRUMENTS_ESSAY_INSTRUMENTS 
	FOREIGN KEY (ID_INSTRUMENT) REFERENCES ESSAY_INSTRUMENTS (ID_INSTRUMENT)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ESSAY_INSTRUMENTS ADD CONSTRAINT FK_ESSAY_INSTRUMENTS_FORMAL_INSTRUMENTS 
	FOREIGN KEY (ID_INSTRUMENT) REFERENCES FORMAL_INSTRUMENTS (ID_INSTRUMENT)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE COMPLETION_INSTRUMENTS ADD CONSTRAINT FK_COMPLETION_INSTRUMENTS_OBJECTIVE_INSTRUMENTS 
	FOREIGN KEY (ID_INSTRUMENT) REFERENCES OBJECTIVE_INSTRUMENTS (ID_INSTRUMENT)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE MULTIPLE_CHOICE_INSTRUMENTS ADD CONSTRAINT FK_MULTIPLE_CHOICE_INSTRUMENTS_CHOICE_INSTRUMENTS 
	FOREIGN KEY (ID_INSTRUMENT) REFERENCES CHOICE_INSTRUMENTS (ID_INSTRUMENT)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE SINGLE_CHOICE_INSTRUMENTS ADD CONSTRAINT FK_SINGLE_CHOICE_INSTRUMENTS_CHOICE_INSTRUMENTS 
	FOREIGN KEY (ID_INSTRUMENT) REFERENCES CHOICE_INSTRUMENTS (ID_INSTRUMENT)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE CHOICE_INSTRUMENTS ADD CONSTRAINT FK_CHOICE_INSTRUMENTS_OBJECTIVE_INSTRUMENTS 
	FOREIGN KEY (ID_INSTRUMENT) REFERENCES OBJECTIVE_INSTRUMENTS (ID_INSTRUMENT)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE CORRESPONDENCE_INSTRUMENTS ADD CONSTRAINT FK_CORRESPONDENCE_INSTRUMENTS_OBJECTIVE_INSTRUMENTS 
	FOREIGN KEY (ID_INSTRUMENT) REFERENCES OBJECTIVE_INSTRUMENTS (ID_INSTRUMENT)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE OBJECTIVE_INSTRUMENTS ADD CONSTRAINT FK_OBJECTIVE_INSTRUMENTS_FORMAL_INSTRUMENTS 
	FOREIGN KEY (ID_INSTRUMENT) REFERENCES FORMAL_INSTRUMENTS (ID_INSTRUMENT)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE FORMAL_INSTRUMENTS ADD CONSTRAINT FK_FORMAL_INSTRUMENTS_INSTRUMENTS 
	FOREIGN KEY (ID_INSTRUMENT) REFERENCES INSTRUMENTS (ID_INSTRUMENT)
	ON DELETE CASCADE ON UPDATE CASCADE;


###########################################################
######## Las tablas que corresponde a las opciones ########
###########################################################
	
DROP TABLE IF EXISTS DISTRACTORS;
DROP TABLE IF EXISTS TRUE_OPTIONS;
DROP TABLE IF EXISTS OPTIONS;

CREATE TABLE DISTRACTORS (
	ID_OPTION INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (ID_OPTION)
);

CREATE TABLE TRUE_OPTIONS (
	ID_OPTION INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (ID_OPTION)
);

CREATE TABLE OPTIONS (
	ID_OPTION INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	DESCRIPTION VARCHAR(255) NOT NULL,
	ID_INSTRUMENT INTEGER UNSIGNED NOT NULL,
	PRIMARY KEY (ID_OPTION),
	KEY (ID_INSTRUMENT)
);

ALTER TABLE DISTRACTORS ADD CONSTRAINT FK_DISTRACTORS_OPTIONS 
	FOREIGN KEY (ID_OPTION) REFERENCES OPTIONS (ID_OPTION)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE TRUE_OPTIONS ADD CONSTRAINT FK_TRUE_OPTIONS_OPTIONS 
	FOREIGN KEY (ID_OPTION) REFERENCES OPTIONS (ID_OPTION)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE OPTIONS ADD CONSTRAINT FK_OPTIONS_CHOICE_INSTRUMENTS 
	FOREIGN KEY (ID_INSTRUMENT) REFERENCES CHOICE_INSTRUMENTS (ID_INSTRUMENT)
	ON DELETE CASCADE ON UPDATE CASCADE;


###########################################################
####### Las tablas que corresponde a las respuestas #######
###########################################################

DROP TABLE IF EXISTS ESSAY_ANSWERS;
DROP TABLE IF EXISTS COMPLETION_ANSWERS;
DROP TABLE IF EXISTS TRUE_FALSE_ANSWERS;
DROP TABLE IF EXISTS RELATION_ANSWERS;
DROP TABLE IF EXISTS ANSWERS;

CREATE TABLE ESSAY_ANSWERS (
	ID_ANSWER INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	ANSWER TEXT NOT NULL,
	ID_INSTRUMENT INTEGER UNSIGNED NOT NULL,
	PRIMARY KEY (ID_ANSWER),
	KEY (ID_INSTRUMENT)
);

CREATE TABLE COMPLETION_ANSWERS (
	ID_ANSWER INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	PHRASE VARCHAR(255) NOT NULL,
	PHRASE_INDEX INTEGER UNSIGNED NOT NULL,
	ID_INSTRUMENT INTEGER UNSIGNED NOT NULL,
	PRIMARY KEY (ID_ANSWER),
	KEY (ID_INSTRUMENT)
);

CREATE TABLE TRUE_FALSE_ANSWERS (
	ID_ANSWER INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	VALUE BOOL NOT NULL,
	ID_OPTION INTEGER UNSIGNED NOT NULL,
	PRIMARY KEY (ID_ANSWER),
	KEY (ID_OPTION)
);

CREATE TABLE RELATION_ANSWERS (
	ID_ANSWER INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	LEFT_SIDE VARCHAR(255) NULL,
	RIGHT_SIDE VARCHAR(255) NULL,
	ID_INSTRUMENT INTEGER UNSIGNED NOT NULL,
	PRIMARY KEY (ID_ANSWER),
	KEY (ID_INSTRUMENT)
);

CREATE TABLE ANSWERS (
	ID_ANSWER INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (ID_ANSWER)
);

ALTER TABLE ESSAY_ANSWERS ADD CONSTRAINT FK_ESSAY_ANSWERS_ESSAY_INSTRUMENTS 
	FOREIGN KEY (ID_INSTRUMENT) REFERENCES ESSAY_INSTRUMENTS (ID_INSTRUMENT)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ESSAY_ANSWERS ADD CONSTRAINT FK_ESSAY_ANSWERS_ANSWERS 
	FOREIGN KEY (ID_ANSWER) REFERENCES ANSWERS (ID_ANSWER)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE COMPLETION_ANSWERS ADD CONSTRAINT FK_COMPLETION_ANSWERS_ANSWERS 
	FOREIGN KEY (ID_ANSWER) REFERENCES ANSWERS (ID_ANSWER)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE COMPLETION_ANSWERS ADD CONSTRAINT FK_COMPLETION_ANSWERS_COMPLETION_INSTRUMENTS 
	FOREIGN KEY (ID_INSTRUMENT) REFERENCES COMPLETION_INSTRUMENTS (ID_INSTRUMENT)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE TRUE_FALSE_ANSWERS ADD CONSTRAINT FK_TRUE_FALSE_ANSWERS_ANSWERS 
	FOREIGN KEY (ID_ANSWER) REFERENCES ANSWERS (ID_ANSWER)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE TRUE_FALSE_ANSWERS ADD CONSTRAINT FK_TRUE_FALSE_ANSWERS_OPTIONS 
	FOREIGN KEY (ID_OPTION) REFERENCES OPTIONS (ID_OPTION)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE RELATION_ANSWERS ADD CONSTRAINT FK_RELATION_ANSWERS_ANSWERS 
	FOREIGN KEY (ID_ANSWER) REFERENCES ANSWERS (ID_ANSWER)
	ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE RELATION_ANSWERS ADD CONSTRAINT FK_RELATION_ANSWERS_CORRESPONDENCE_INSTRUMENTS 
	FOREIGN KEY (ID_INSTRUMENT) REFERENCES CORRESPONDENCE_INSTRUMENTS (ID_INSTRUMENT)
	ON DELETE CASCADE ON UPDATE CASCADE;
	
	
############################################################
############ El script de borrado de las tablas ############
############################################################

DELETE FROM ANSWERS;
DELETE FROM OPTIONS;
DELETE FROM INSTRUMENTS;

############################################################
############################################################
############################################################