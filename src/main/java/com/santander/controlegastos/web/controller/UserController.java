package com.santander.controlegastos.web.controller;

import com.santander.controlegastos.dto.UserDTO;
import com.santander.controlegastos.mapper.UserMapper;
import com.santander.controlegastos.service.UserService;
import com.santander.controlegastos.vo.UserVO;
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
@RequestMapping(UserController.USER_ENDPOINT)
@Api(value = "Category")
public class UserController {

    public static final String USER_ENDPOINT = "/user";
    private final UserService service;
    private final UserMapper mapper;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creating a new user", responseReference = "New resource created.")
    public ResponseEntity<Void> create(@Valid @RequestBody final UserVO vo) {
        final UserDTO usuarioDTO = this.service.save(this.mapper.voToDTO(vo));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuarioDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updating a user", responseReference = "200 = Resource updated")
    public ResponseEntity<UserDTO> update(@Valid @RequestBody final UserVO vo,
                                          @Valid @PathVariable("id") final Long id) {
        final UserDTO dto = this.service.update(id, this.mapper.voToDTO(vo));
        return ResponseEntity.ok(dto);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Searching for a specific user", response = UserDTO.class)
    public ResponseEntity<UserDTO> findById(@Valid @PathVariable final Long id) {
        final UserDTO dto = this.service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listing all users", response = UserDTO.class, responseContainer = "List")
    public ResponseEntity<List<UserDTO>> findAll() {
        final List<UserDTO> dtoList = this.service.findAll();
        return ResponseEntity.ok(dtoList);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleting a user", responseReference = "Resource deleted")
    public ResponseEntity<Void> delete(@Valid @PathVariable("id") final Long id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

}
