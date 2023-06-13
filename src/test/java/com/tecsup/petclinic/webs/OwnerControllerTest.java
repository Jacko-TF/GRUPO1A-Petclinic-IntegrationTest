package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.domain.OwnerTO;
import com.tecsup.petclinic.domain.PetTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.exception.PetNotFoundException;
import com.tecsup.petclinic.mapper.OwnerMapper;
import com.tecsup.petclinic.mapper.PetMapper;
import com.tecsup.petclinic.services.OwnerService;
import com.tecsup.petclinic.services.PetService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author jacko tinoco
 *
 */
@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class OwnerControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    //@Autowired
    private OwnerService ownerService;

    //@Autowired
    private OwnerMapper mapper;

    @Test
    public void testFindAllOwners() throws Exception {

        //int NRO_RECORD = 73;
        int ID_FIRST_RECORD = 1;

        this.mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                //		    .andExpect(jsonPath("$", hasSize(NRO_RECORD)))
                .andExpect(jsonPath("$[0].id", is(ID_FIRST_RECORD)));
    }


    /**
     * @throws Exception
     */
    @Test
    public void testCreateOwner() throws Exception {

        String FIRST_NAME = "JACKO";
        String LAST_NAME = "TINOCO";
        String ADDRESS = "CALLE 7";
        String CITY = "ATE";
        String TELEPHONE = "964950100";


        OwnerTO newOwnerTO = new OwnerTO();
        newOwnerTO.setFirstName(FIRST_NAME);
        newOwnerTO.setLastName(LAST_NAME);
        newOwnerTO.setAddress(ADDRESS);
        newOwnerTO.setCity(CITY);
        newOwnerTO.setTelephone(TELEPHONE);

        mockMvc.perform(post("/owners")
                        .content(om.writeValueAsString(newOwnerTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                //.andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(LAST_NAME)))
                .andExpect(jsonPath("$.address", is(ADDRESS)))
                .andExpect(jsonPath("$.city", is(CITY)))
                .andExpect(jsonPath("$.telephone", is(TELEPHONE)));

    }
    /**
     * @throws Exception
     */

    /**
     * Find owner by id
     *
     * @param id
     * @return
     * @throws OwnerNotFoundException
     */
    @Test
    public void testFindById() throws Exception {
        String OWNER_ID= 1;
        OwnerTO newOwnerTO = new OwnerTO();
        newOwnerTO.setFirstName(OWNER_ID);


    }
    @GetMapping(value = "/owners/{id}")
    ResponseEntity<OwnerTO> findById(@PathVariable Integer id) {

        OwnerTO ownerTO = null;

        try {
            Owner owner = ownerService.findById(id);
            ownerTO = this.mapper.toOwnerTO(owner);

        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ownerTO);

    }
}

}