package com.santander.controlegastos.web.controller;

import com.santander.controlegastos.dto.EntryDTO;
import com.santander.controlegastos.mapper.EntryMapper;
import com.santander.controlegastos.service.EntryService;
import com.santander.controlegastos.vo.EntryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(EntryController.ENTRY_ENDPOINT)
@Api(value = "Category")
public class EntryController {

    public static final String ENTRY_ENDPOINT = "/entry";
    private final EntryService service;
    private final EntryMapper entryMapper;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creating a new entry", responseReference = "New resource created.")
    public ResponseEntity<Void> create(@Valid @RequestBody final EntryVO vo) {
        final EntryDTO dto = service.save(this.entryMapper.voToDTO(vo), vo.getUserId(), vo.getCategoryId());
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updating a entry", responseReference = "200 = Resource updated")
    public ResponseEntity<EntryDTO> update(@Valid @RequestBody final EntryVO vo,
                                           @Valid @PathVariable("id") final Long id) {
        final EntryDTO dto = this.service.update(id, this.entryMapper.voToDTO(vo));
        return ResponseEntity.ok(dto);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Searching for a specific entry", response = EntryDTO.class)
    public ResponseEntity<EntryDTO> findById(@Valid @PathVariable final Long id) {
        final EntryDTO dto = this.service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listing all entry", response = EntryDTO.class, responseContainer = "List")
    public ResponseEntity<List<EntryDTO>> findAll() {
        final List<EntryDTO> dtoList = this.service.findAll();
        return ResponseEntity.ok(dtoList);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleting a entry", responseReference = "Resource deleted")
    public ResponseEntity<Void> delete(@Valid @PathVariable("id") final Long id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/user/{userId}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listing all entries by user", response = EntryDTO.class, responseContainer = "List")
    public ResponseEntity<List<EntryDTO>> findEntriesByUser(@Valid @PathVariable("userId") final Long userId) {
        final List<EntryDTO> dtoList = this.service.findEntriesByUser(userId);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping(path = "/user/{userId}/{entryDate}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listing all entries by user and entry date", response = EntryDTO.class, responseContainer = "List")
    public ResponseEntity<List<EntryDTO>> findEntriesByUserAndEntryDate(@Valid @PathVariable("userId") final Long userId,
                                                                @Valid @PathVariable("entryDate") @DateTimeFormat(pattern="yyyy-MM-dd") final Date entryDate) {
        final List<EntryDTO> dtoList = this.service.findEntriesByUserAndEntryDate(userId, entryDate);
        return ResponseEntity.ok(dtoList);
    }
}
