package com.santander.controlegastos.controller;

import com.santander.controlegastos.base.TestBase;
import com.santander.controlegastos.dto.CategoryDTO;
import com.santander.controlegastos.repository.CategoryRepository;
import com.santander.controlegastos.service.CategoryService;
import com.santander.controlegastos.vo.CategoryVO;
import com.santander.controlegastos.web.controller.CategoryController;
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

public class CategoryControllerTests extends TestBase {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryService service;

    @Test
    public void findAll() throws Exception{
        super.mvc.perform(MockMvcRequestBuilders.get(CategoryController.CATEGORY_ENDPOINT))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", not(empty())))
                .andDo(print());
    }

    @Test
    public void search() throws Exception{
        super.mvc.perform(MockMvcRequestBuilders.get(CategoryController.CATEGORY_ENDPOINT + "/search")
                .param("name", "tr"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", not(empty())))
                .andDo(print());
    }

    @Test
    public void findById () throws Exception {
        super.mvc.perform(MockMvcRequestBuilders.get(CategoryController.CATEGORY_ENDPOINT + "/{id}", 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", not(isEmptyOrNullString())))
                .andDo(print());
    }

    @Test
    public void findByIdWithIdInvalid () throws Exception {
        super.mvc.perform(MockMvcRequestBuilders.get(CategoryController.CATEGORY_ENDPOINT + "/{id}", 100))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void save () throws Exception {
        final long countBeforeSave = repository.count();
        final CategoryVO mock = createMockFromFile("json/category/category", CategoryVO.class);
        super.mvc.perform(MockMvcRequestBuilders.post(CategoryController.CATEGORY_ENDPOINT)
                .contentType(APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(mock)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
        final long countAfterSave = repository.count();
        assertEquals(countBeforeSave + 1, countAfterSave);
    }

    @Test
    public void update () throws Exception {
        final CategoryDTO beforeUpdate = service.findById(9L);
        final CategoryVO mock = createMockFromFile("json/category/categoryUpdate", CategoryVO.class);
        super.mvc.perform(MockMvcRequestBuilders.put(CategoryController.CATEGORY_ENDPOINT + "/{id}", 9L)
                .contentType(APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(mock)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

        final CategoryDTO afterUpdate = service.findById(9L);

        assertNotSame(beforeUpdate.getName(), afterUpdate.getName());
    }

    @Test
    public void remove () throws Exception {
        final long countBeforeSave = repository.count();
        super.mvc.perform(MockMvcRequestBuilders.delete(CategoryController.CATEGORY_ENDPOINT + "/{id}", 8)
                .contentType(APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
        final long countAfterSave = repository.count();
        assertEquals(countBeforeSave - 1, countAfterSave);
    }
}
