package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Jacko_Tinoco
 */
@Service
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    OwnerRepository ownerRepository;

    public OwnerServiceImpl (OwnerRepository ownerRepository) {
        this. ownerRepository = ownerRepository;
    }
    /**
     *
     * @param owner
     * @return
     */
    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    /**
     *
     * @param owner
     * @return
     */
    @Override
    public Owner update(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public List<Owner> findAll() {
        //
        return ownerRepository.findAll();

    }

    @Override
    public void delete(String firstName) throws OwnerNotFoundException {
        deleteByName(firstName);
    }

    @Override
    public void deleteById(Integer id) throws OwnerNotFoundException {
        Owner owner = findById(id);
        ownerRepository.delete(owner);
    }

    @Override
    public void deleteByName(String firstName) throws OwnerNotFoundException {
        List<Owner> owners = findByFirstName(firstName);

        if (owners.isEmpty()) {
            throw new OwnerNotFoundException("Registro no encontrado...!");
        }

        for (Owner owner : owners) {
            ownerRepository.delete(owner);
        }
    }

    @Override
    public Owner findById(Integer id) throws OwnerNotFoundException {
        Optional<Owner> owner = ownerRepository.findById(id);

        if (!owner.isPresent())
            throw new OwnerNotFoundException("Record not found...!");

        return owner.get();
    }

    @Override
    public List<Owner> findByName(String firstName) {
        return null;
    }

//    @Override
//    public List<Owner> findByName(String firstName) {
//        Optional<Owner> owners = OwnerRepository.findByName(firstName);
//
//        owners.stream().forEach(owner -> log.info("" + owner));
//
//        return owners;
//    }


    @Override
    public List<Owner> findByFirstName(String firstName) {
        return ownerRepository.findByFirstName(firstName);
    }

}
