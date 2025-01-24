package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner initializeDatabase(StudentRepository studentRepository) {
        return args -> {
            generateStudents(studentRepository);
            //sortingStudents(studentRepository);
            PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("firstName").ascending());
            Page<Student> page = studentRepository.findAll(pageRequest);
            System.out.println(page);
        };
    }

    private void sortingStudents(StudentRepository studentRepository) {
        // Sort students by firstName
        Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
        studentRepository.findAll(sort)
                .forEach(student -> System.out.println(student.getFirstName()));

        Sort sortByLastName = Sort.by("lastName").ascending().and(Sort.by("age").descending());
        studentRepository.findAll(sortByLastName)
                .forEach(student -> System.out.println(student.getLastName() + " " + student.getAge()));
    }

    private void generateStudents(StudentRepository studentRepository) {
        // Use Faker library to generate sample data for students
        Faker faker = new Faker();

        // Generate and save 25 sample students
        final int SAMPLE_STUDENT_COUNT = 30;

        for (int i = 0; i < SAMPLE_STUDENT_COUNT; i++) {
            // Generate realistic sample data for a student
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = generateEmail(firstName, lastName);
            Integer age = faker.number().numberBetween(17, 55);

            // Create a Student object
            Student student = new Student(firstName, lastName, email, age);

            // Save the student to the repository
            studentRepository.save(student);
        }
    }

    /**
     * Generates an email address in the format "firstname.lastname@maxdeveloper.net".
     *
     * @param firstName the first name of the student
     * @param lastName  the last name of the student
     * @return the generated email address
     */
    private String generateEmail(String firstName, String lastName) {
        return String.format("%s.%s@maxdeveloper.net", firstName.toLowerCase(), lastName.toLowerCase());
    }


}
