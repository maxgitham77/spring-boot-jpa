package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student rosemary = new Student(
                    "Rosemary",
                    "Cowell",
                    "rosemary.cowell@me.org",
                    21
            );
            Student paul = new Student(
                    "Paul",
                    "Henry",
                    "paul.henry@me.org",
                    27
            );
            studentRepository.saveAll(List.of(rosemary, paul));

            /*System.out.print("Number of students: ");
            System.out.println(studentRepository.count());

            studentRepository
                    .findById(2L)
                    .ifPresentOrElse(
                    System.out::println, () -> System.out.println("Student with ID 2 not found")
            );

            studentRepository
                    .findById(3L)
                    .ifPresentOrElse(
                            System.out::println, () -> System.out.println("Student with ID 3 not found")
                    );

            List<Student> students = studentRepository.findAll();
            students.forEach(System.out::println);

            //studentRepository.deleteById(2L);

            System.out.println(studentRepository.count());*/

            studentRepository.findStudentByEmail("paul.henry@me.org").ifPresentOrElse(
                    System.out::println, () -> System.out.println("Student with the email does not exist")
            );

            studentRepository.findStudentsByFistNameEqualsAndAgeEquals(
                    "Rosemary",
                    21
            ).forEach(System.out::println);

            studentRepository.findStudentsByFistNameEqualsAndAgeEqualsParam(
                    "Rosemary",
                    21
            ).forEach(System.out::println);

        };
    }

}
