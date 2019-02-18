package com.santander.controlegastos.resource;

import com.santander.controlegastos.base.TestBase;
import com.santander.controlegastos.dto.LancamentoDTO;
import com.santander.controlegastos.repository.LancamentoRepository;
import com.santander.controlegastos.service.LancamentoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LancamentoResourceTests extends TestBase {

    @Autowired
    private LancamentoRepository repository;

    @Autowired
    private LancamentoService service;

    @Test
    public void findAll() throws Exception{
        super.mvc.perform(MockMvcRequestBuilders.get(LancamentoResource.LANCAMENTO_ENDPOINT ))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", not(empty())))
                .andDo(print());
    }

    @Test
    public void findById () throws Exception {
        super.mvc.perform(MockMvcRequestBuilders.get(LancamentoResource.LANCAMENTO_ENDPOINT  + "/{id}", 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", not(isEmptyOrNullString())))
                .andDo(print());
    }

    @Test
    public void findByIdWithIdInvalid () throws Exception {
        super.mvc.perform(MockMvcRequestBuilders.get(LancamentoResource.LANCAMENTO_ENDPOINT  + "/{id}", 100))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void findByUsuario () throws Exception {
        super.mvc.perform(MockMvcRequestBuilders.get(LancamentoResource.LANCAMENTO_ENDPOINT  + "/usuario/{id}", 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", not(isEmptyOrNullString())))
                .andDo(print());
    }
//
    @Test
    public void findByData () throws Exception {
        super.mvc.perform(MockMvcRequestBuilders.get(LancamentoResource.LANCAMENTO_ENDPOINT  + "/usuario/{id}/{data}", 1, "2018-12-27"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", not(isEmptyOrNullString())))
                .andDo(print());
    }

    @Test
    public void save () throws Exception {
        final long countBeforeSave = repository.count();
        final LancamentoDTO mock = createMockFromFile("json/lancamento/lancamento", LancamentoDTO.class);
        super.mvc.perform(MockMvcRequestBuilders.post(LancamentoResource.LANCAMENTO_ENDPOINT )
                .contentType(APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(mock)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
        final long countAfterSave = repository.count();
        assertEquals(countBeforeSave + 1, countAfterSave);
    }

    @Test
    public void saveLancamentoWithoutCategoria () throws Exception {
        final long countBeforeSave = repository.count();
        final LancamentoDTO mock = createMockFromFile("json/lancamento/lancamentoSemCategoria", LancamentoDTO.class);
        super.mvc.perform(MockMvcRequestBuilders.post(LancamentoResource.LANCAMENTO_ENDPOINT )
                .contentType(APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(mock)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
        final long countAfterSave = repository.count();
        assertEquals(countBeforeSave + 1, countAfterSave);
    }

    @Test
    public void update () throws Exception {
        final LancamentoDTO beforeUpdate = service.findById(3L);
        final LancamentoDTO mock = createMockFromFile("json/lancamento/lancamentoUpdate", LancamentoDTO.class);
        super.mvc.perform(MockMvcRequestBuilders.put(LancamentoResource.LANCAMENTO_ENDPOINT)
                .contentType(APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(mock)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

        final LancamentoDTO afterUpdate = service.findById(3L);

        assertTrue(beforeUpdate.getValor() != afterUpdate.getValor());
    }

    @Test
    public void remove () throws Exception {
        final long countBeforeSave = repository.count();
        super.mvc.perform(MockMvcRequestBuilders.delete(LancamentoResource.LANCAMENTO_ENDPOINT  + "/{id}", 4)
                .contentType(APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
        final long countAfterSave = repository.count();
        assertEquals(countBeforeSave - 1, countAfterSave);
    }
}
