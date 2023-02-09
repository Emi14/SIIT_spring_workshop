package org.siit.springworkshop.repository;

import org.siit.springworkshop.entity.StudentEntity;
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

}
