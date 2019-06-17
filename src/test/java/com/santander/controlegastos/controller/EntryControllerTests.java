package com.santander.controlegastos.controller;

import com.santander.controlegastos.base.TestBase;
import com.santander.controlegastos.dto.EntryDTO;
import com.santander.controlegastos.repository.EntryRepository;
import com.santander.controlegastos.service.EntryService;
import com.santander.controlegastos.vo.EntryVO;
import com.santander.controlegastos.web.controller.EntryController;
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

public class EntryControllerTests extends TestBase {

    @Autowired
    private EntryRepository repository;

    @Autowired
    private EntryService service;

    @Test
    public void findAll() throws Exception{
        super.mvc.perform(MockMvcRequestBuilders.get(EntryController.ENTRY_ENDPOINT))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", not(empty())))
                .andDo(print());
    }

    @Test
    public void findById () throws Exception {
        super.mvc.perform(MockMvcRequestBuilders.get(EntryController.ENTRY_ENDPOINT  + "/{id}", 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", not(isEmptyOrNullString())))
                .andDo(print());
    }

    @Test
    public void findByIdWithIdInvalid () throws Exception {
        super.mvc.perform(MockMvcRequestBuilders.get(EntryController.ENTRY_ENDPOINT  + "/{id}", 100))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void findEntriesByUser () throws Exception {
        super.mvc.perform(MockMvcRequestBuilders.get(EntryController.ENTRY_ENDPOINT  + "/user/{userId}", 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", not(isEmptyOrNullString())))
                .andDo(print());
    }

    @Test
    public void findEntriesByUserAndEntryDate () throws Exception {
        super.mvc.perform(MockMvcRequestBuilders.get(EntryController.ENTRY_ENDPOINT  + "/user/{id}/{userId}", 1, "2018-12-27"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", not(isEmptyOrNullString())))
                .andDo(print());
    }

    @Test
    public void save () throws Exception {
        final long countBeforeSave = repository.count();
        final EntryVO mock = createMockFromFile("json/entry/entry", EntryVO.class);
        super.mvc.perform(MockMvcRequestBuilders.post(EntryController.ENTRY_ENDPOINT )
                .contentType(APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(mock)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
        final long countAfterSave = repository.count();
        assertEquals(countBeforeSave + 1, countAfterSave);
    }

    @Test
    public void saveEntryWithoutCategoria () throws Exception {
        final long countBeforeSave = repository.count();
        final EntryVO mock = createMockFromFile("json/entry/entryWithoutCategory", EntryVO.class);
        super.mvc.perform(MockMvcRequestBuilders.post(EntryController.ENTRY_ENDPOINT )
                .contentType(APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(mock)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
        final long countAfterSave = repository.count();
        assertEquals(countBeforeSave + 1, countAfterSave);
    }

    @Test
    public void update () throws Exception {
        final EntryDTO beforeUpdate = service.findById(4L);
        final EntryVO mock = createMockFromFile("json/entry/entryUpdate", EntryVO.class);
        super.mvc.perform(MockMvcRequestBuilders.put(EntryController.ENTRY_ENDPOINT  + "/{id}", 4)
                .contentType(APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(mock)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

        final EntryDTO afterUpdate = service.findById(4L);

        assertNotSame(beforeUpdate.getAmount(), afterUpdate.getAmount());
    }

    @Test
    public void remove () throws Exception {
        final long countBeforeSave = repository.count();
        super.mvc.perform(MockMvcRequestBuilders.delete(EntryController.ENTRY_ENDPOINT  + "/{id}", 5)
                .contentType(APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
        final long countAfterSave = repository.count();
        assertEquals(countBeforeSave - 1, countAfterSave);
    }
}
