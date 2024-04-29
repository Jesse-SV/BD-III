package com.example.danielmeuprojeto.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.danielmeuprojeto.domain.model.Usuario;

//Repositório é o que vai trabalhar dentro do banco de dados
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    //E-mail pertence apenas ao usuário, sendo utilizado como chave
    Optional<Usuario> findByEmail(String email);
    
}
