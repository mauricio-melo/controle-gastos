package com.santander.controlegastos.service;

import com.santander.controlegastos.domain.Lancamento;
import com.santander.controlegastos.dto.LancamentoDTO;
import com.santander.controlegastos.exception.ResourceNotFoundException;
import com.santander.controlegastos.repository.LancamentoRepository;
import com.santander.controlegastos.translate.LancamentoTranslate;
import com.santander.controlegastos.translate.UsuarioTranslate;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;

    @Autowired
    private LancamentoTranslate translate;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioTranslate usuarioTranslate;

    public LancamentoDTO save(@NonNull final LancamentoDTO dto){
        Lancamento entity = translate.toEntity(dto);
        entity.setUsuario(usuarioTranslate.toEntity(usuarioService.findById(dto.getCodigoUsuario())));
        return translate.toDTO(repository.save(entity));
    }

    public LancamentoDTO update(@NonNull final LancamentoDTO dto){
        Lancamento entity = translate.toEntity(dto, translate.toEntity(findById(dto.getId())));
        entity.setUsuario(usuarioTranslate.toEntity(usuarioService.findById(dto.getCodigoUsuario())));
        return translate.toDTO(repository.save(entity));
    }

    public LancamentoDTO findById(final Long id) {
        return translate.toDTO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString())));
    }

    public List<LancamentoDTO> findAll() {
        return translate.toDTOList(repository.findAll());
    }

    public void delete(final Long id) {
        repository.deleteById(id);
    }

    public List<LancamentoDTO> lancamentosPorUsuario(final Long idUsuario){
        return translate.toDTOList(repository.findByUsuario_idOrderByDataLancamentoDesc(idUsuario));
    }

    public List<LancamentoDTO> lancamentosUsuarioPorData(final Long idUsuario, final Date dataLancamento){
        return translate.toDTOList(repository.findByUsuario_idAndDataLancamento(idUsuario, dataLancamento));
    }
}
