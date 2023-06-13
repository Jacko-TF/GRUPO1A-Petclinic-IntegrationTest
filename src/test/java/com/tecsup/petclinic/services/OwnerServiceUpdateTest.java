package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * @autor Luis_Corrales
 */
@SpringBootTest
@Slf4j
public class OwnerServiceUpdateTest {

    @Autowired
    private OwnerService ownerService;

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
