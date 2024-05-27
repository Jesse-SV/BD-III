package com.example.danielmeuprojeto.domain.service;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.danielmeuprojeto.domain.exception.BadRequestException;
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
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        if(optUsuario.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível obter o usuário com o id " + id);
        }
        return mapper.map(optUsuario.get(), UsuarioResponseDTO.class);
    }

    @Override
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        if(dto.getEmail() == null || dto.getSenha() == null){
            throw new BadRequestException("Email e Senha são Obrigatórios");
        }
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(dto.getEmail());
        if(optUsuario.isPresent()){
            throw new BadRequestException("Um usuário existente já possui este Email");
        }
        //Transforma em usuario do da model, salva
        Usuario usuario = mapper.map(dto, Usuario.class);
        usuario.setDataCadastro(new Date());
        //Criptografar senha
        String senha = bCryptPasswordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senha);
        usuario = usuarioRepository.save(usuario);
        //Depois transforma em UsuarioResponseDTO e retorna
        return mapper.map(usuario, UsuarioResponseDTO.class);
    }

    @Override
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        UsuarioResponseDTO usuarioBanco = obterPorId(id);
        if(dto.getEmail() == null || dto.getSenha() == null){
            throw new BadRequestException("Email e Senha são Obrigatórios");
        }
        //Transforma em usuario do da model, salva
        Usuario usuario = mapper.map(dto, Usuario.class);
        usuario.setId(id);
        usuario.setSenha(dto.getSenha());
        usuario.setDataCadastro(usuarioBanco.getDataCadastro());
        usuario.setDataInativacao(usuarioBanco.getDataInativacao());
        usuario = usuarioRepository.save(usuario);
        //Depois transforma em UsuarioResponseDTO e retorna
        return mapper.map(usuario, UsuarioResponseDTO.class);
    }

    @Override
    public void deletar(Long id) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        if(optUsuario.isEmpty()){
            throw new BadRequestException("Não foi possível obter o usuário com o Id ");
        }
        Usuario usuario = optUsuario.get();
        usuario.setDataInativacao(new Date());
        usuarioRepository.save(usuario);
    }
    
}
