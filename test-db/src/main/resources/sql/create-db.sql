-- -----------------------------------------------------
-- Table `course`.`teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course`.`teacher` (
  `id_t` INT(11) NOT NULL AUTO_INCREMENT,
  `name_t` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_t`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `course`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course`.`course` (
  `id_c` INT(11) NOT NULL,
  `name_c` VARCHAR(45) NOT NULL,
  `description_c` VARCHAR(45) NOT NULL,
  `id_t` INT(11) NOT NULL,
  PRIMARY KEY (`id_c`),
  INDEX `fk_course_teacher_idx` (`id_t` ASC),
  CONSTRAINT `fk_course_teacher`
    FOREIGN KEY (`id_t`)
    REFERENCES `course`.`teacher` (`id_t`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `course`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course`.`student` (
  `id_s` INT(11) NOT NULL,
  `name_s` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_s`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `course`.`student_course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `course`.`student_course` (
  `id_s` INT(11) NOT NULL,
  `id_c` INT(11) NOT NULL,
  `comment_sc` VARCHAR(45) NOT NULL,
  `value_sc` INT(11) NOT NULL,
  PRIMARY KEY (`id_s`, `id_c`))
ENGINE = InnoDB;