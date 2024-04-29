package com.example.danielmeuprojeto.domain.service;

import java.util.List;

//Modelo Service genérico
public interface ICRUDService<Request,Response> {

    List<Response> obterTodos();
    Response obterPorId(Long id);
    Response cadastrar(Request dto);
    Response atualizar(Long id, Request dto);
    void deletar(Long id);
}
