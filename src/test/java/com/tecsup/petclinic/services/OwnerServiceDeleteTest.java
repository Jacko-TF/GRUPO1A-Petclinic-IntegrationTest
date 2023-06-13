package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j

public class OwnerServiceDeleteTest {
    @Autowired
    private OwnerService ownerService;
    @Test
    public void testDeleteOwnerById() {
        int OWNER_ID = 26;
        String OWNER_FIRST_NAME = "Nicole";
        String OWNER_LAST_NAME  = "Arguedas";
        String OWNER_ADRESS = "Calle Ibiza 250";
        String OWNER_CITY = "La Molina";
        String OWNER_TELEFONO = "9085551023";

        Owner owner = new Owner(OWNER_ID, OWNER_FIRST_NAME, OWNER_LAST_NAME, OWNER_ADRESS, OWNER_CITY, OWNER_TELEFONO);
        Owner ownerCreated = this.ownerService.create(owner);

        log.info("OWNER CREATED: " + ownerCreated.toString());

        try {
            this.ownerService.delete(String.valueOf(owner.getId()));
        } catch (OwnerNotFoundException e) {
            fail(e.getMessage());
        }

        assertThrows(OwnerNotFoundException.class, () -> {
            this.ownerService.findById(owner.getId());
        });
    }
    @Test
    public void testDeleteOwnerByName() {
        int OWNER_ID = 16;
        String OWNER_FIRST_NAME = "Nicole";
        String OWNER_LAST_NAME  = "Arguedas";
        String OWNER_ADRESS = "Calle Ibiza 250";
        String OWNER_CITY = "La Molina";
        String OWNER_TELEFONO = "9085551023";

        Owner owner = new Owner(OWNER_ID, OWNER_FIRST_NAME, OWNER_LAST_NAME, OWNER_ADRESS, OWNER_CITY, OWNER_TELEFONO);
        Owner ownerCreated = this.ownerService.create(owner);

        log.info("OWNER CREATED: " + ownerCreated.toString());

        try {
            this.ownerService.delete(owner.getFirstName());
        } catch (OwnerNotFoundException e) {
            fail(e.getMessage());
        }

        assertThrows(OwnerNotFoundException.class, () -> {
            this.ownerService.findById(owner.getId());
        });
    }
}

