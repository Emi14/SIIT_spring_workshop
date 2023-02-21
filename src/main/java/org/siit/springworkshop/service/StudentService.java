package org.siit.springworkshop.service;

import jakarta.transaction.Transactional;
import org.siit.springworkshop.converter.AddressConverter;
import org.siit.springworkshop.converter.StudentConverter;
import org.siit.springworkshop.dto.StudentDto;
import org.siit.springworkshop.entity.StudentEntity;
import org.siit.springworkshop.exception.DataNotFound;
import org.siit.springworkshop.helper.StudentInfoContributor;
import org.siit.springworkshop.repository.StudentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final AddressService addressService;
    private final StudentConverter studentConverter;
    private final AddressConverter addressConverter;
    private final StudentInfoContributor studentInfoContributor;

    public StudentService(StudentRepository studentRepository, AddressService addressService, StudentConverter studentConverter, AddressConverter addressConverter, StudentInfoContributor studentInfoContributor) {
        this.studentRepository = studentRepository;
        this.addressService = addressService;
        this.studentConverter = studentConverter;
        this.addressConverter = addressConverter;
        this.studentInfoContributor = studentInfoContributor;
    }

    @Transactional
    public Long saveStudent(StudentDto studentDto, boolean throwExceptionOnSave) {
        StudentEntity student = studentConverter.fromDtoToEntity(studentDto);
        student.setAddresses(addressConverter.convertDtosToEntities(studentDto.getAddresses()));
        StudentEntity savedStudent = studentRepository.save(student);

        if (throwExceptionOnSave) {
            throw new RuntimeException("Exception after student was saved");
        }

        addressService.saveAll(studentDto.getAddresses(), savedStudent);

        return savedStudent.getId();
    }

    @Transactional
    public Long saveStudentWithCascade(StudentDto studentDto) {
        StudentEntity studentEntity = studentConverter.fromDtoToEntity(studentDto);
        studentEntity.setAddresses(addressConverter.convertDtosToEntities(studentDto.getAddresses()));
        studentEntity.getAddresses().forEach(address -> address.setStudent(studentEntity));
//        studentRepository.updateStudentAge(2402L, 55);
        return studentRepository.save(studentEntity).getId();
    }

    public StudentDto getStudentById(Long id) throws DataNotFound {
        Optional<StudentEntity> student = studentRepository.findById(id);

        if (student.isEmpty()) {
            studentInfoContributor.incrementNumberOfDataNotFoundExceptions();
            throw new DataNotFound(String.format("The student with id %s could not be found in database.", id));
        }
        return studentConverter.fromEntityToDto(student.get());
    }

    public StudentDto getStudentByFirstNameAndAge(String firstName, Integer age) throws DataNotFound {
//        List<StudentEntity> student = studentRepository.findStudent(firstName, age);
//        List<StudentEntity> student = studentRepository.findAllByFirstNameAndAge(firstName, age);
        List<StudentEntity> student = studentRepository.findAllByFirstNameAndAge(firstName, age);
        List<StudentEntity> studentEntitiesByCity = studentRepository.findAllByAddresses_City("Bucharest");
        if (student.isEmpty()) {
            studentInfoContributor.incrementNumberOfDataNotFoundExceptions();
            throw new DataNotFound(String.format("The student with first name %s and age %s could not be found in database.", firstName, age));
        }
        return studentConverter.fromEntityToDto(student.get(0));
    }

    public StudentDto getStudentByFirstNameContainingAndAgeGreaterThan(String firstName, Integer age) throws DataNotFound {
//        List<StudentEntity> student = studentRepository.findStudent(firstName, age);
//        List<StudentEntity> student = studentRepository.findAllByFirstNameAndAge(firstName, age);
//        Optional<StudentEntity> student = studentRepository.findFirstByFirstNameContainingAndAgeGreaterThan(firstName, age);
        Optional<StudentEntity> student = studentRepository.findFirstByFirstNameOrderByIdDesc(firstName);

        if (student.isEmpty()) {
            studentInfoContributor.incrementNumberOfDataNotFoundExceptions();
            throw new DataNotFound(String.format("The student with first name %s and age %s could not be found in database.", firstName, age));
        }
        return studentConverter.fromEntityToDto(student.get());
    }

    @Transactional
    public void deleteStudentById(Long studentId, boolean throwExceptionOnSave) throws DataNotFound {
        if (!studentRepository.existsById(studentId)) {
            studentInfoContributor.incrementNumberOfDataNotFoundExceptions();
            throw new DataNotFound(String.format("The student with id %s could not be found in database.", studentId));
        }
        addressService.deleteAddressesByStudentId(studentId);
        if (throwExceptionOnSave) {
            throw new RuntimeException("Exception after student was saved");
        }
        studentRepository.deleteById(studentId);
    }

    public void deleteStudentWithCascade(Long studentId) throws DataNotFound {
        if (!studentRepository.existsById(studentId)) {
            studentInfoContributor.incrementNumberOfDataNotFoundExceptions();
            throw new DataNotFound(String.format("The student with id %s could not be found in database.", studentId));
        }
        studentRepository.deleteById(studentId);
    }

    public Long upsertStudent(StudentDto studentDto) throws DataNotFound {
        if (studentDto.getId() != null) {
            return updateStudent(studentDto);
        }
        return saveStudentWithCascade(studentDto);
    }

    private Long updateStudent(StudentDto studentDto) throws DataNotFound {
        Optional<StudentEntity> studentEntityOptional = studentRepository.findById(studentDto.getId());
        if (studentEntityOptional.isEmpty()) {
            studentInfoContributor.incrementNumberOfDataNotFoundExceptions();
            throw new DataNotFound(String.format("The student with id %s could not be found in database.", studentDto.getId()));
        }
        StudentEntity student = studentEntityOptional.get();
        if (studentDto.getFirstName() != null) {
            student.setFirstName(studentDto.getFirstName());
        }
        if (studentDto.getLastName() != null) {
            student.setLastName(studentDto.getLastName());
        }
        return studentRepository.save(student).getId();
    }

    public List<StudentEntity> findAll() {
        return (List<StudentEntity>) studentRepository.findAll();
    }

    public List<StudentDto> findAllPaginated(int pageNumber, int pageSize, String sortBy, String order) {
        Sort sort = getSort(sortBy, order);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

//        return studentRepository.findAll(pageRequest).getContent().stream()
//                .map(studentConverter::fromEntityToDto)
//                .collect(Collectors.toList());

        return studentRepository.findAll(pageRequest).stream()
                .map(studentConverter::fromEntityToDto)
                .collect(Collectors.toList());
    }

    private Sort getSort(String sortBy, String order) {
        Sort sort;
        if (order == null) {
            sort = Sort.by(sortBy);
        } else if (order.equalsIgnoreCase("ascending")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }
        return sort.and(Sort.by("id").ascending());
    }

    @Async
    public void sendEmail(String studentId, boolean throwExceptionOnSend) throws InterruptedException {

        /*
        Call email service - duration 5000ms
         */
        Thread.sleep(5000);

        System.out.println("The email is going to be sent for student id " + studentId);
        studentInfoContributor.incrementNumberOfSentEmails();
    }
}
