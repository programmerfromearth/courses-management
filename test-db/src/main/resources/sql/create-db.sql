-- -----------------------------------------------------
-- Table teacher
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS TEACHER (
    ID_T   INT         NOT NULL AUTO_INCREMENT,
    NAME_T VARCHAR(45) NOT NULL,
    PRIMARY KEY (ID_T)
);

-- -----------------------------------------------------
-- Table course
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS COURSE (
    ID_C          INT         NOT NULL AUTO_INCREMENT,
    NAME_C        VARCHAR(45) NOT NULL,
    DESCRIPTION_C VARCHAR(45) NOT NULL,
    ID_T          INT         NOT NULL,
    UNIQUE INDEX id_t_UNIQUE (id_t ASC),
    PRIMARY KEY (ID_C),
    CONSTRAINT fk_course_teacher
        FOREIGN KEY (ID_T)
            REFERENCES TEACHER (ID_T)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table student
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS STUDENT (
    ID_S     INT         NOT NULL AUTO_INCREMENT,
    NUMBER_S VARCHAR(45) NOT NULL,
    NAME_S   VARCHAR(45) NOT NULL,
    PRIMARY KEY (ID_S),
    UNIQUE INDEX number_s_UNIQUE (NUMBER_S ASC)
);

-- -----------------------------------------------------
-- Table feedback
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS FEEDBACK (
   ID_F      INT         NOT NULL AUTO_INCREMENT,
   COMMENT_F LONGTEXT    NOT NULL,
   VALUE_F   INT         NOT NULL,
   PRIMARY KEY (ID_F)
);

-- -----------------------------------------------------
-- Table student_course
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS STUDENT_COURSE_FEEDBACK (
    ID_C       INT         NOT NULL,
    ID_S       INT         NOT NULL,
    ID_F       INT,
    PRIMARY KEY (ID_C, ID_S),
    CONSTRAINT fk_student_course_feedback_student
        FOREIGN KEY (ID_S)
            REFERENCES STUDENT (ID_S)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT fk_student_course_feedback_course
        FOREIGN KEY (ID_C)
            REFERENCES COURSE (ID_C)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT fk_student_course_feedback_feedback
            FOREIGN KEY (ID_F)
            REFERENCES FEEDBACK (ID_F)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    UNIQUE INDEX id_f_UNIQUE (ID_F ASC)
);