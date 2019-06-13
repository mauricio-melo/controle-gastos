package com.santander.controlegastos.web.controller;

import com.santander.controlegastos.dto.CategoryDTO;
import com.santander.controlegastos.mapper.CategoryMapper;
import com.santander.controlegastos.service.CategoryService;
import com.santander.controlegastos.vo.CategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(CategoryController.CATEGORY_ENDPOINT)
@Api(value = "Category")
public class CategoryController {

    public static final String CATEGORY_ENDPOINT = "/category";
    private final CategoryService service;
    private final CategoryMapper mapper;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creating a new category", responseReference = "New resource created.")
    public ResponseEntity<Void> create(@Valid @RequestBody final CategoryVO vo) {
        final CategoryDTO dto = this.service.save(this.mapper.voToDTO(vo));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updating a category", responseReference = "200 = Resource updated")
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody final CategoryVO vo,
                                              @Valid @PathVariable("id") final Long id) {
        final CategoryDTO dto = this.service.update(id, this.mapper.voToDTO(vo));
        return ResponseEntity.ok(dto);
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Searching for a specific category", response = CategoryDTO.class)
    public ResponseEntity<CategoryDTO> findById(@Valid @PathVariable final Long id) {
        final CategoryDTO dto = this.service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listing all categories", response = CategoryDTO.class, responseContainer = "List")
    public ResponseEntity<List<CategoryDTO>> findAll() {
        final List<CategoryDTO> dtoList = this.service.findAll();
        return ResponseEntity.ok(dtoList);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deleting a category", responseReference = "Resource deleted")
    public ResponseEntity<Void> delete(@Valid @PathVariable("id") final Long id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/search", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find category by name containing", response = CategoryDTO.class, responseContainer = "List")
    public ResponseEntity<List<CategoryDTO>> findByNameContaining(@Valid @RequestParam(required = false, defaultValue = "%") final String name) {
        final List<CategoryDTO> dtoList = service.findByNameContaining(name);
        return ResponseEntity.ok(dtoList);
    }

}
