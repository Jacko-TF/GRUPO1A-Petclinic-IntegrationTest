package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.domain.OwnerTO;
import com.tecsup.petclinic.domain.PetTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.exception.PetNotFoundException;
import com.tecsup.petclinic.mapper.OwnerMapper;
import com.tecsup.petclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author jacko tinoco
 *
 */

@RestController
@Slf4j
public class OwnerController {

    //@Autowired
    private OwnerService ownerService;

    //@Autowired
    private OwnerMapper mapper;

    public OwnerController(OwnerService ownerService, OwnerMapper mapper) {
        this.ownerService = ownerService;
        this.mapper = mapper;
    }


    /**
     * Get all owners
     *
     * @return
     */
    @GetMapping(value = "/owners")
    public ResponseEntity<List<OwnerTO>> findAllOwners() {

        List<Owner> owners = (List<Owner>) ownerService.findAll();
        log.info("owners: " + owners);
        owners.forEach(item -> log.info("Owner >>  {} ", item));

        List<OwnerTO> ownersTO = this.mapper.toOwnerTOList(owners);
        log.info("ownersTO: " + ownersTO);
        ownersTO.forEach(item -> log.info("OwnerTO >>  {} ", item));

        return ResponseEntity.ok(ownersTO);

    }

    /**
     * Create owner
     *
     * @param ownerTO
     * @return
     */
    @PostMapping(value = "/owners")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<OwnerTO> create(@RequestBody OwnerTO ownerTO) {

        Owner newOwner = this.mapper.toOwner(ownerTO);
        OwnerTO newOwnerTO = this.mapper.toOwnerTO(ownerService.create(newOwner));

        return  ResponseEntity.status(HttpStatus.CREATED).body(newOwnerTO);

    }

    /**
     * Find pet by id
     *
     * @param id
     * @return
     * @throws OwnerNotFoundException
     */

    @GetMapping(value = "/owners/{id}")
    ResponseEntity<OwnerTO> findById(@PathVariable Integer id) {
        OwnerTO ownerTO = null;
        try {
            Owner owner = ownerService.findById(id);
            ownerTO  = this.mapper.toOwnerTO(owner);

        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ownerTO);
    }

    /**
     *  Delete owner by id
     *
     * @param id
     */
    @DeleteMapping(value = "/owners/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            ownerService.deleteById(id);
            return ResponseEntity.ok("Delete ID: " + id);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
