package com.example.danielmeuprojeto.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.danielmeuprojeto.domain.model.Titulo;
import com.example.danielmeuprojeto.domain.model.Usuario;

public interface TituloRepository extends JpaRepository<Titulo, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM public.titulo WHERE data_vencimento BETWEEN TO_TIMESTAMPP(:periodoInicial, 'YYYY-MM-DD hh24:MI:SS') AND TO_TIMESTAMPP(:periodoFinal, 'YYYY-MM-DD hh24:MI:SS')")
    List<Titulo> obterFluxoDeCaixaPorDataVencimento(
        @Param("periodoInicial") String periodoInicial,
        @Param("periodoFinal") String periodoFinal
    );
    List<Titulo> findByUsuario(Usuario usuario);
}
 