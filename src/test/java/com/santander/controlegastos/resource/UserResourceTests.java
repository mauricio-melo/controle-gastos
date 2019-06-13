package com.santander.controlegastos.resource;

import com.santander.controlegastos.base.TestBase;
import com.santander.controlegastos.dto.UserDTO;
import com.santander.controlegastos.repository.UserRepository;
import com.santander.controlegastos.service.UserService;
import com.santander.controlegastos.web.controller.UserController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertNotSame;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserResourceTests extends TestBase {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService service;

    @Test
    public void findAll() throws Exception{
        super.mvc.perform(MockMvcRequestBuilders.get(UserController.USER_ENDPOINT))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", not(empty())))
                .andDo(print());
    }

    @Test
    public void findById () throws Exception {
        super.mvc.perform(MockMvcRequestBuilders.get(UserController.USER_ENDPOINT + "/{id}", 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", not(isEmptyOrNullString())))
                .andDo(print());
    }

    @Test
    public void findByIdWithIdInvalid () throws Exception {
        super.mvc.perform(MockMvcRequestBuilders.get(UserController.USER_ENDPOINT + "/{id}", 100))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void save () throws Exception {
        final long countBeforeSave = repository.count();
        final UserDTO mock = createMockFromFile("json/user/user", UserDTO.class);
        super.mvc.perform(MockMvcRequestBuilders.post(UserController.USER_ENDPOINT)
                .contentType(APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(mock)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
        final long countAfterSave = repository.count();
        assertEquals(countBeforeSave + 1, countAfterSave);
    }

    @Test
    public void update () throws Exception {
        final UserDTO beforeUpdate = service.findById(3L);
        final UserDTO mock = createMockFromFile("json/user/user", UserDTO.class);
        super.mvc.perform(MockMvcRequestBuilders.put(UserController.USER_ENDPOINT)
                .contentType(APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(mock)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

        final UserDTO afterUpdate = service.findById(3L);

        assertNotSame(beforeUpdate.getName(), afterUpdate.getName());
    }

    @Test
    public void remove () throws Exception {
        final long countBeforeSave = repository.count();
        super.mvc.perform(MockMvcRequestBuilders.delete(UserController.USER_ENDPOINT + "/{id}", 4)
                .contentType(APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
        final long countAfterSave = repository.count();
        assertEquals(countBeforeSave - 1, countAfterSave);
    }
}
