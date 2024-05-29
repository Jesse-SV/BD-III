package com.example.danielmeuprojeto.domain.model.dto.titulos;

import java.util.Date;
import java.util.List;

import com.example.danielmeuprojeto.domain.Enum.ETipoTitulo;
import com.example.danielmeuprojeto.domain.model.dto.centrodecusto.CentroDeCustoRequestDTO;

public class TituloRequestDTO {
    private String descricao;
    private ETipoTitulo tipo;
    private List<CentroDeCustoRequestDTO> centrosDeCustos;
    private Double valor;
    private Date dataCadastro;
    private Date dataReferencial;
    private Date dataVencimento;
    private Date dataPagamento;
    private String observacao;
    
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public ETipoTitulo getTipo() {
        return tipo;
    }
    public void setTipo(ETipoTitulo tipo) {
        this.tipo = tipo;
    }
    public List<CentroDeCustoRequestDTO> getCentrosDeCustos() {
        return centrosDeCustos;
    }
    public void setCentrosDeCustos(List<CentroDeCustoRequestDTO> centrosDeCustos) {
        this.centrosDeCustos = centrosDeCustos;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Date getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public Date getDataReferencial() {
        return dataReferencial;
    }
    public void setDataReferencial(Date dataReferencial) {
        this.dataReferencial = dataReferencial;
    }
    public Date getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    public Date getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
