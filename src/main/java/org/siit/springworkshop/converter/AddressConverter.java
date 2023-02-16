package org.siit.springworkshop.converter;

import org.modelmapper.ModelMapper;
import org.siit.springworkshop.dto.AddressDto;
import org.siit.springworkshop.entity.AddressEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressConverter {

    private ModelMapper mapper;

    public AddressConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public AddressEntity convertDtoToEntity(AddressDto addressDto)
    {
        return mapper.map(addressDto, AddressEntity.class);
    }
    public List<AddressEntity> convertDtosToEntities(List<AddressDto> addressDtos)
    {
        return addressDtos.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }

    public AddressDto convertEntityToDto(AddressEntity address)
    {
        return mapper.map(address, AddressDto.class);
    }
}
