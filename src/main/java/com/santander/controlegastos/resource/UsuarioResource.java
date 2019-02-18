package com.santander.controlegastos.resource;

import com.santander.controlegastos.dto.UsuarioDTO;
import com.santander.controlegastos.service.UsuarioService;
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
@RequestMapping(UsuarioResource.USUARIO_ENDPOINT)
@Api(value = "Usuario", description = "Operações disponiveis")
public class UsuarioResource {

    public static final String USUARIO_ENDPOINT = "/usuario";

    @Autowired
    private UsuarioService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Criação de um novo recurso", responseReference = "Novo recurso criado")
    public ResponseEntity<Void> create(@Valid @RequestBody final UsuarioDTO dto) {
        final UsuarioDTO usuarioDTO = service.save(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuarioDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualização de um recurso usuario", responseReference = "Recurso atualizado com sucesso.")
    public ResponseEntity<UsuarioDTO> update(@Valid @RequestBody final UsuarioDTO dto) {
        final UsuarioDTO usuarioDTO = service.update(dto);
        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Pesquisando por um recurso em específico", response = UsuarioDTO.class)
    public ResponseEntity<UsuarioDTO> findById(@PathVariable final Long id) {
        final UsuarioDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listando todos os recursos", response = UsuarioDTO.class, responseContainer = "List")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        final List<UsuarioDTO> dtoList = service.findAll();
        return ResponseEntity.ok(dtoList);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete de um recurso", responseReference = "Recurso excluído.")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
