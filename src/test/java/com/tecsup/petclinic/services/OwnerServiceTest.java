package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author jacko tinoco
 *
 */

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

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

    @Test
    public void testUpdateOwner() {

        String OWNER_FIRST_NAME = "Luis";
        String OWNER_LAST_NAME = "Corrales";
        String OWNER_ADRESS = "315 Huarochiri Mayorazgo";
        String OWNER_CITY = "Ate";
        String OWNER_TELEFONO = "964950100";

        String OWNER_FIRST_NAME_UPDATE = "Angel";
        String OWNER_LAST_NAME_UPDATE = "Granda";
        String OWNER_ADRESS_UPDATE = "400 Huarochiri Mayorazgo";
        String OWNER_CITY_UPDATE = "Santa Anita";
        String OWNER_TELEFONO_UPDATE = "911911911";

        // Crea un nuevo owner
        Owner owner = new Owner(OWNER_FIRST_NAME,OWNER_LAST_NAME, OWNER_ADRESS, OWNER_CITY, OWNER_TELEFONO);

        // Guarda el owner creado
        Owner ownerCreated = this.ownerService.create(owner);

        // Actualiza los datos del owner creado
        ownerCreated.setFirstName(OWNER_FIRST_NAME_UPDATE);
        ownerCreated.setLastName(OWNER_LAST_NAME_UPDATE);
        ownerCreated.setAddress(OWNER_ADRESS_UPDATE);
        ownerCreated.setCity(OWNER_CITY_UPDATE);
        ownerCreated.setTelephone(OWNER_TELEFONO_UPDATE);

        // Guarda los cambios
        Owner ownerUpdated = this.ownerService.update(ownerCreated);

        log.info("OWNER UPDATED :" + ownerUpdated.toString());

        // Compara los datos del owner creado con los datos del owner actualizado

        assertNotEquals(OWNER_FIRST_NAME, ownerUpdated.getFirstName());
        assertNotEquals(OWNER_LAST_NAME, ownerUpdated.getLastName());
        assertNotEquals(OWNER_ADRESS, ownerUpdated.getAddress());
        assertNotEquals(OWNER_CITY, ownerUpdated.getCity());
        assertNotEquals(OWNER_TELEFONO, ownerUpdated.getTelephone());

        assertEquals(OWNER_FIRST_NAME_UPDATE, ownerUpdated.getFirstName());
        assertEquals(OWNER_LAST_NAME_UPDATE, ownerUpdated.getLastName());
        assertEquals(OWNER_ADRESS_UPDATE, ownerUpdated.getAddress());
        assertEquals(OWNER_CITY_UPDATE, ownerUpdated.getCity());
        assertEquals(OWNER_TELEFONO_UPDATE, ownerUpdated.getTelephone());
    }

}