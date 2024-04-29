package com.example.danielmeuprojeto.domain.service;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.danielmeuprojeto.domain.exception.ResourceNotFoundException;
import com.example.danielmeuprojeto.domain.model.Usuario;
import com.example.danielmeuprojeto.domain.model.dto.usuario.UsuarioRequestDTO;
import com.example.danielmeuprojeto.domain.model.dto.usuario.UsuarioResponseDTO;
import com.example.danielmeuprojeto.domain.repository.UsuarioRepository;

@Service
public class UsuarioService implements ICRUDService<UsuarioRequestDTO, UsuarioResponseDTO> {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<UsuarioResponseDTO> obterTodos() {

        List<Usuario> usuarios = usuarioRepository.findAll();
        //Percorra um por um declare cada usuário como objeto e coloque tudo em uma lista só
        return usuarios.stream().map(usuario ->
         mapper.map(usuario,
         UsuarioResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO obterPorId(Long id) {
        
        //Optinal é literalmente opcional, o que previne uma quebra de código
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível obter o usuário com o id" + id);
        }
        return mapper.map(usuario, UsuarioResponseDTO.class);
    }

    @Override
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastrar'");
    }

    @Override
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }
    
}
