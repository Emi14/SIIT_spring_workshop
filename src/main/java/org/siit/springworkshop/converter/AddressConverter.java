package org.siit.springworkshop.converter;

import org.modelmapper.ModelMapper;
import org.siit.springworkshop.dto.AddressDto;
import org.siit.springworkshop.entity.AddressEntity;
import org.springframework.stereotype.Component;

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

    public AddressDto convertEntityToDto(AddressEntity address)
    {
        return mapper.map(address, AddressDto.class);
    }
}
