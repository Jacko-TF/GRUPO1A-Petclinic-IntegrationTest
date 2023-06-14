package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository
        extends CrudRepository<Owner,Integer> {
    // Fetch owners by first_name
    List<Owner> findByFirstName(String first_name);
    // Fetch owners by id


    @Override
    List<Owner> findAll();
}



