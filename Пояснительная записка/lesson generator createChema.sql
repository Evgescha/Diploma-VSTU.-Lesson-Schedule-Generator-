-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema teacher_lesson_genarator
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `teacher_lesson_genarator` ;

-- -----------------------------------------------------
-- Schema teacher_lesson_genarator
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `teacher_lesson_genarator` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `teacher_lesson_genarator` ;

-- -----------------------------------------------------
-- Table `teacher_lesson_genarator`.`classes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teacher_lesson_genarator`.`classes` ;

CREATE TABLE IF NOT EXISTS `teacher_lesson_genarator`.`classes` (
  `id` BIGINT NOT NULL,
  `number` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `teacher_lesson_genarator`.`subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teacher_lesson_genarator`.`subject` ;

CREATE TABLE IF NOT EXISTS `teacher_lesson_genarator`.`subject` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `teacher_lesson_genarator`.`classes_subject_time_on_week`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teacher_lesson_genarator`.`classes_subject_time_on_week` ;

CREATE TABLE IF NOT EXISTS `teacher_lesson_genarator`.`classes_subject_time_on_week` (
  `id` BIGINT NOT NULL,
  `hours_in_week` INT NOT NULL,
  `classes_id` BIGINT NULL DEFAULT NULL,
  `subject_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK4g2j5fao8lmdhfiwhd8pyxm9y`
    FOREIGN KEY (`subject_id`)
    REFERENCES `teacher_lesson_genarator`.`subject` (`id`),
  CONSTRAINT `FKpsmx652ykbwbdhn413uepw0b1`
    FOREIGN KEY (`classes_id`)
    REFERENCES `teacher_lesson_genarator`.`classes` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FKpsmx652ykbwbdhn413uepw0b1` ON `teacher_lesson_genarator`.`classes_subject_time_on_week` (`classes_id` ASC) VISIBLE;

CREATE INDEX `FK4g2j5fao8lmdhfiwhd8pyxm9y` ON `teacher_lesson_genarator`.`classes_subject_time_on_week` (`subject_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `teacher_lesson_genarator`.`teacher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teacher_lesson_genarator`.`teacher` ;

CREATE TABLE IF NOT EXISTS `teacher_lesson_genarator`.`teacher` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `teacher_lesson_genarator`.`lesson`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teacher_lesson_genarator`.`lesson` ;

CREATE TABLE IF NOT EXISTS `teacher_lesson_genarator`.`lesson` (
  `id` BIGINT NOT NULL,
  `day` INT NOT NULL,
  `time` INT NOT NULL,
  `classes_id` BIGINT NULL DEFAULT NULL,
  `subject_id` BIGINT NULL DEFAULT NULL,
  `teacher_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK7ydr23s8y9j6lip5qrngoymx4`
    FOREIGN KEY (`subject_id`)
    REFERENCES `teacher_lesson_genarator`.`subject` (`id`),
  CONSTRAINT `FK9yhaoqrjxt5gwmn6icp1lf35n`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `teacher_lesson_genarator`.`teacher` (`id`),
  CONSTRAINT `FKswqu7pf2bcb5tq0mj0s9nskyo`
    FOREIGN KEY (`classes_id`)
    REFERENCES `teacher_lesson_genarator`.`classes` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FKswqu7pf2bcb5tq0mj0s9nskyo` ON `teacher_lesson_genarator`.`lesson` (`classes_id` ASC) VISIBLE;

CREATE INDEX `FK7ydr23s8y9j6lip5qrngoymx4` ON `teacher_lesson_genarator`.`lesson` (`subject_id` ASC) VISIBLE;

CREATE INDEX `FK9yhaoqrjxt5gwmn6icp1lf35n` ON `teacher_lesson_genarator`.`lesson` (`teacher_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `teacher_lesson_genarator`.`teacher_subjects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teacher_lesson_genarator`.`teacher_subjects` ;

CREATE TABLE IF NOT EXISTS `teacher_lesson_genarator`.`teacher_subjects` (
  `teachers_id` BIGINT NOT NULL,
  `subjects_id` BIGINT NOT NULL,
  PRIMARY KEY (`teachers_id`, `subjects_id`),
  CONSTRAINT `FK590r5o8kjhiwyp96jylu9yw3o`
    FOREIGN KEY (`subjects_id`)
    REFERENCES `teacher_lesson_genarator`.`subject` (`id`),
  CONSTRAINT `FKmh7s4b90cucnl9yovtwjo6vvd`
    FOREIGN KEY (`teachers_id`)
    REFERENCES `teacher_lesson_genarator`.`teacher` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FK590r5o8kjhiwyp96jylu9yw3o` ON `teacher_lesson_genarator`.`teacher_subjects` (`subjects_id` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
