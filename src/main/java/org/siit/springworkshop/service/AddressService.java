package org.siit.springworkshop.service;

import org.siit.springworkshop.converter.AddressConverter;
import org.siit.springworkshop.dto.AddressDto;
import org.siit.springworkshop.entity.AddressEntity;
import org.siit.springworkshop.entity.StudentEntity;
import org.siit.springworkshop.exception.DataNotFound;
import org.siit.springworkshop.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressConverter addressConverter;

    public AddressService(AddressRepository addressRepository, AddressConverter addressConverter) {
        this.addressRepository = addressRepository;
        this.addressConverter = addressConverter;
    }

    public List<AddressEntity> saveAll(List<AddressDto> addressDtos, StudentEntity student) {
        List<AddressEntity> addressEntities = addressDtos.stream()
                .map(addressDto -> {
                            AddressEntity addressEntity = addressConverter.convertDtoToEntity(addressDto);
                            addressEntity.setStudent(student);
                            return addressEntity;
                        }
                )
                .collect(Collectors.toList());
        return (List<AddressEntity>) addressRepository.saveAll(addressEntities);
    }

    public AddressDto getAddressById(Long id) throws DataNotFound {
        Optional<AddressEntity> address = addressRepository.findById(id);
        if(address.isEmpty())
        {
            throw new DataNotFound(String.format("The address with id %s could not be found", id));
        }
        return addressConverter.convertEntityToDto(address.get());
    }

    public int deleteAddressesByStudentId(Long studentId)
    {
        return addressRepository.deleteAddressEntitiesByStudentId(studentId);
    }
}
