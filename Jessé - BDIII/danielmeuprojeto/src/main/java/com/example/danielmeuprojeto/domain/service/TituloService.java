package com.example.danielmeuprojeto.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.danielmeuprojeto.domain.exception.ResourceNotFoundException;
import com.example.danielmeuprojeto.domain.model.Titulo;
import com.example.danielmeuprojeto.domain.model.Usuario;
import com.example.danielmeuprojeto.domain.model.dto.titulos.TituloRequestDTO;
import com.example.danielmeuprojeto.domain.model.dto.titulos.TituloResponseDTO;
import com.example.danielmeuprojeto.domain.repository.TituloRepository;

@Service
public class TituloService implements ICRUDService<TituloRequestDTO, TituloResponseDTO> {
    @Autowired
    private TituloRepository tituloRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<TituloResponseDTO> obterTodos() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Titulo> titulos = tituloRepository.findByUsuario(usuario);
        return titulos.stream().map(titulo -> mapper.map(titulo, TituloResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public TituloResponseDTO obterPorId(Long id) {
        Optional<Titulo> optTitulo = tituloRepository.findById(id);
        if(optTitulo.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar o título com o id: " + id);
        }
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(optTitulo.get().getUsuario().getId() != usuario.getId()){
            throw new ResourceNotFoundException("O título com o id: " + id + "não existe");
        }
        return mapper.map(optTitulo.get(), TituloResponseDTO.class);
    }

    @Override
    public TituloResponseDTO cadastrar(TituloRequestDTO dto) {
        validarTitulo(dto);
        Titulo titulo = mapper.map(dto, Titulo.class);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        titulo.setUsuario(usuario);
        titulo.setId(null);
        titulo.setDataCadastro(new Date());
        titulo = tituloRepository.save(titulo);
        return mapper.map(titulo, TituloResponseDTO.class);
    }

    @Override
    public TituloResponseDTO atualizar(Long id, TituloRequestDTO dto) {
        obterPorId(id);
        validarTitulo(dto);
        Titulo titulo = mapper.map(dto, Titulo.class);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        titulo.setUsuario(usuario);
        titulo.setId(id);
        titulo = tituloRepository.save(titulo);
        return mapper.map(titulo, TituloResponseDTO.class);
    }

    @Override
    public void deletar(Long id) {
        obterPorId(id);
        tituloRepository.deleteById(id);
    }

    private void validarTitulo(TituloRequestDTO dto){
        if((dto.getTipo() == null) || (dto.getDataVencimento() == null) || (dto.getValor() == null || dto.getDescricao() == null)){
            throw new com.example.danielmeuprojeto.domain.exception.BadRequestException("Título inválido");
        }
    }

    public List<TituloResponseDTO> obterPorDataVencimento(String periodoInicial, String periodoFinal){
        List<Titulo> titulos = tituloRepository.obterFluxoDeCaixaPorDataVencimento(periodoInicial, periodoFinal);
        return titulos.stream().map(titulo -> mapper.map(titulo, TituloResponseDTO.class)).collect(Collectors.toList());
    }
    
}
