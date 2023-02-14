package org.siit.springworkshop.repository;

import org.siit.springworkshop.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

    int deleteAddressEntitiesByStudentId(Long studentId);

}
