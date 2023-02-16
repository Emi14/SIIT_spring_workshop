package org.siit.springworkshop.repository;

import org.siit.springworkshop.entity.StudentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {

    //    @Query(value = "SELECT * FROM STUDENTS WHERE first_name = ? and age = ?", nativeQuery = true)
//    @Query(value = "SELECT s FROM students s where s.firstName = ?1 and s.age = ?2")
    @Query(value = "SELECT s FROM students s where s.age = :age and s.firstName = :name")
    List<StudentEntity> findStudent(@Param("name") String firstName, int age);

    List<StudentEntity> findAllByFirstNameAndAge(String firstName, int age);

    Optional<StudentEntity> findFirstByFirstNameContainingAndAgeGreaterThan(String firstName, int age);

    Optional<StudentEntity> findFirstByFirstNameOrderByIdDesc(String firstName);

    List<StudentEntity> findFirstThreeByFirstName(String name);

    @Modifying
    @Query(value = "UPDATE students s SET s.age = :age WHERE s.id = :studentId")
    int updateStudentAge(Long studentId, int age);

    List<StudentEntity> findAllByAddresses_City(String city);

    List<StudentEntity> findAll(Pageable pageable); //todo how to eliminate count query

    List<StudentEntity> findAll(Sort sort);
}
