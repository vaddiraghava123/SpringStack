use mysqldb;

-- Table: Student
CREATE TABLE Student_AI (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Table: Course
CREATE TABLE Course_AI (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Table: StudentCourse (Mapping table for many-to-many relationship)
CREATE TABLE StudentCourse_AI (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Student_ai(id),
    FOREIGN KEY (course_id) REFERENCES Course_ai(id)
);

commit;
TRUNCATE  table Student_AI;
TRUNCATE  table Course_AI;
-- Insert Students
INSERT INTO Student_AI (name) VALUES ('Ram'), ('Murali'), ('Manoj'),('Shiva'),('Srujan');

-- Insert Courses
INSERT INTO Course_AI (name) VALUES ('Java'), ('Oracle'), ('React JS'),('Spring AI'),('Cloud');

-- Map Students to Courses
INSERT INTO StudentCourse_AI (student_id, course_id) VALUES
(4, 4), 
(4, 5), 
(5, 5), 
(6, 4),
(6, 5),
(6, 6),
(7, 7),
(7, 8),
(7, 6);
CREATE INDEX idx_student_id ON StudentCourse_AI(student_id);
CREATE INDEX idx_course_id ON StudentCourse_AI(course_id);