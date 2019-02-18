package com.santander.controlegastos.resource;

import com.santander.controlegastos.base.TestBase;
import com.santander.controlegastos.dto.CategoriaDTO;
import com.santander.controlegastos.repository.CategoriaRepository;
import com.santander.controlegastos.service.CategoriaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import static org.junit.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoriaResourceTests extends TestBase {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private CategoriaService service;

    @Test
    public void findAll() throws Exception{
        super.mvc.perform(MockMvcRequestBuilders.get(CategoriaResource.CATEGORIA_ENDPOINT))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", not(empty())))
                .andDo(print());
    }

    @Test
    public void search() throws Exception{
        super.mvc.perform(MockMvcRequestBuilders.get(CategoriaResource.CATEGORIA_ENDPOINT + "/search")
                .param("name", "tr"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", not(empty())))
                .andDo(print());
    }

    @Test
    public void findById () throws Exception {
        super.mvc.perform(MockMvcRequestBuilders.get(CategoriaResource.CATEGORIA_ENDPOINT + "/{id}", 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", not(isEmptyOrNullString())))
                .andDo(print());
    }

    @Test
    public void findByIdWithIdInvalid () throws Exception {
        super.mvc.perform(MockMvcRequestBuilders.get(CategoriaResource.CATEGORIA_ENDPOINT + "/{id}", 100))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void save () throws Exception {
        final long countBeforeSave = repository.count();
        final CategoriaDTO mock = createMockFromFile("json/categoria/categoria", CategoriaDTO.class);
        super.mvc.perform(MockMvcRequestBuilders.post(CategoriaResource.CATEGORIA_ENDPOINT)
                .contentType(APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(mock)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
        final long countAfterSave = repository.count();
        assertEquals(countBeforeSave + 1, countAfterSave);
    }

    @Test
    public void update () throws Exception {
        final CategoriaDTO beforeUpdate = service.findById(9L);
        final CategoriaDTO mock = createMockFromFile("json/categoria/categoriaUpdate", CategoriaDTO.class);
        super.mvc.perform(MockMvcRequestBuilders.put(CategoriaResource.CATEGORIA_ENDPOINT)
                .contentType(APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(mock)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

        final CategoriaDTO afterUpdate = service.findById(9L);

        assertTrue(beforeUpdate.getNome() != afterUpdate.getNome());
    }

    @Test
    public void remove () throws Exception {
        final long countBeforeSave = repository.count();
        super.mvc.perform(MockMvcRequestBuilders.delete(CategoriaResource.CATEGORIA_ENDPOINT + "/{id}", 8)
                .contentType(APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
        final long countAfterSave = repository.count();
        assertEquals(countBeforeSave - 1, countAfterSave);
    }
}
