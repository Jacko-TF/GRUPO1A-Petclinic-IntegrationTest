package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 * @author Jacko_Tinoco
 */
@SpringBootTest
@Slf4j
public class OwnerServiceCreateTest {

    @Autowired
    private OwnerService ownerService;

    @Test
    public void testCreateOwner() {

        String OWNER_FIRST_NAME = "Jacko";
        String OWNER_LAST_NAME = "Jacko";
        String OWNER_ADRESS = "315 Huarochiri Mayorazgo";
        String OWNER_CITY = "Ate";
        String OWNER_TELEFONO = "964950100";

        Owner owner = new Owner(OWNER_FIRST_NAME,OWNER_LAST_NAME, OWNER_ADRESS, OWNER_CITY, OWNER_TELEFONO);

        Owner ownerCreated = this.ownerService.create(owner);

        log.info("OWNER CREATED :" + ownerCreated.toString());

        assertNotNull(ownerCreated.getId());
        assertEquals(OWNER_FIRST_NAME, ownerCreated.getFirstName());
        assertEquals(OWNER_LAST_NAME, ownerCreated.getLastName());
        assertEquals(OWNER_ADRESS, ownerCreated.getAddress());
        assertEquals(OWNER_CITY, ownerCreated.getCity());
        assertEquals(OWNER_TELEFONO, ownerCreated.getTelephone());

    }

   /* @Test
    public void testDeleteOwner() {

        String OWNER_FIRST_NAME = "Jacko2";
        String OWNER_LAST_NAME = "Tinoco2";
        String OWNER_ADRESS = "315 Huarochiri Mayorazgo";
        String OWNER_CITY = "Ate";
        String OWNER_TELEFONO = "964950100";



        // ------------ Create ---------------

        Owner owner = new Owner(OWNER_FIRST_NAME,OWNER_LAST_NAME, OWNER_ADRESS, OWNER_CITY, OWNER_TELEFONO);
        owner = this.ownerService.create(owner);
        log.info("" + owner);

        // ------------ Delete ---------------

        try {
            this.ownerService.deleteById(owner.getId());
        } catch (OwnerNotFoundException e) {
            fail(e.getMessage());
        }

        // ------------ Validation ---------------

        try {
            this.ownerService.findById(owner.getId());
            assertTrue(false);
        } catch (OwnerNotFoundException e) {
            assertTrue(true);
        }

    }*/
}
