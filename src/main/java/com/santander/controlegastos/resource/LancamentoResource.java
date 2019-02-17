package com.santander.controlegastos.resource;

import com.santander.controlegastos.dto.LancamentoDTO;
import com.santander.controlegastos.service.LancamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(LancamentoResource.LANCAMENTO_ENDPOINT)
@Api(value = "Lancamento", description = "Operações disponiveis")
public class LancamentoResource {

    public static final String LANCAMENTO_ENDPOINT = "/lancamento";

    @Autowired
    private LancamentoService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Criação de um novo recurso", responseReference = "Novo recurso criado")
    public ResponseEntity<Void> create(@Valid @RequestBody final LancamentoDTO dto) {
        final LancamentoDTO LancamentoDTO = service.save(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(LancamentoDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Pesquisando por um recurso em específico", response = LancamentoDTO.class)
    public ResponseEntity<LancamentoDTO> findById(@PathVariable final Long id) {
        final LancamentoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listando todos os recursos", response = LancamentoDTO.class, responseContainer = "List")
    public ResponseEntity<List<LancamentoDTO>> findAll() {
        final List<LancamentoDTO> dtoList = service.findAll();
        return ResponseEntity.ok(dtoList);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete de um recurso", responseReference = "Recurso excluído.")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/usuario/{codigoUsuario}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listando todos os lancamentos de determinado usuario", response = LancamentoDTO.class, responseContainer = "List")
    public ResponseEntity<List<LancamentoDTO>> lancamentosPorUsuario(@PathVariable("codigoUsuario") final Long idUsuario) {
        final List<LancamentoDTO> dtoList = service.lancamentosPorUsuario(idUsuario);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping(path = "/usuario/{codigoUsuario}/{dataLancamento}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listando todos os lancamentos de determinado usuario em determinada data", response = LancamentoDTO.class, responseContainer = "List")
    public ResponseEntity<List<LancamentoDTO>> lancamentosPorUsuario(@PathVariable("codigoUsuario") final Long idUsuario,
                                                                     @PathVariable("dataLancamento") @DateTimeFormat(pattern="yyyy-MM-dd") final Date dataLancamento) {
        final List<LancamentoDTO> dtoList = service.lancamentosUsuarioPorData(idUsuario, dataLancamento);
        return ResponseEntity.ok(dtoList);
    }
}
