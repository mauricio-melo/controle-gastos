package com.santander.controlegastos.resource;

import com.santander.controlegastos.dto.CategoriaDTO;
import com.santander.controlegastos.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(CategoriaResource.CATEGORIA_ENDPOINT)
@Api(value = "Usuario", description = "Operações disponiveis")
public class CategoriaResource {

    public static final String CATEGORIA_ENDPOINT = "/categoria";

    @Autowired
    private CategoriaService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Criação de um novo recurso", responseReference = "Novo recurso criado")
    public ResponseEntity<Void> create(@Valid @RequestBody final CategoriaDTO dto) {
        final CategoriaDTO CategoriaDTO = service.save(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(CategoriaDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualização de um recurso categoria", responseReference = "Recurso atualizado com sucesso.")
    public ResponseEntity<CategoriaDTO> update(@Valid @RequestBody final CategoriaDTO dto) {
        final CategoriaDTO CategoriaDTO = service.update(dto);
        return ResponseEntity.ok(CategoriaDTO);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Pesquisando por um recurso em específico", response = CategoriaDTO.class)
    public ResponseEntity<CategoriaDTO> findById(@PathVariable final Long id) {
        final CategoriaDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listando todos os recursos", response = CategoriaDTO.class, responseContainer = "List")
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        final List<CategoriaDTO> dtoList = service.findAll();
        return ResponseEntity.ok(dtoList);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete de um recurso", responseReference = "Recurso excluído.")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/search", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Pesquisa dos recursos", response = CategoriaDTO.class, responseContainer = "List")
    public ResponseEntity<List<CategoriaDTO>> pesquisar(@RequestParam(required = false, defaultValue = "%") final String nome) {
        final List<CategoriaDTO> dtoList = service.pesquisar(nome);
        return ResponseEntity.ok(dtoList);
    }

}
