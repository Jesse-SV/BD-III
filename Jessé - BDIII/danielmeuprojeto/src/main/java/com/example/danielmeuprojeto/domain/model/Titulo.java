package com.example.danielmeuprojeto.domain.model;
import com.example.danielmeuprojeto.domain.Enum.ETipoTitulo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.List;

@Entity
public class Titulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTitulo")
    private Long id;
    @Column(nullable = false)
    private String descricao;
    //Vários títulos podem ter um usuário
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    private ETipoTitulo tipo;
    //Um centro de custo tem vários títulos, e um título tem vários centros de custo
    @ManyToMany
    @JoinTable(name = "titulo_centrodecusto",
    joinColumns = @JoinColumn(name = "idTitulo"),
    inverseJoinColumns = @JoinColumn(name="idCentroDeCusto"))
    private List<CentroDeCusto> centrosDeCustos;
    @Column(nullable = false)
    private Double valor;
    private Date dataCadastro;
    private Date dataReferencia;
    private Date dataVencimento;
    private Date dataPagamento;
    @Column(columnDefinition = "TEXT")
    private String obervacao;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public ETipoTitulo getTipo() {
        return tipo;
    }
    public void setTipo(ETipoTitulo tipo) {
        this.tipo = tipo;
    }
    public List<CentroDeCusto> getCentroDeCustos() {
        return centrosDeCustos;
    }
    public void setCentroDeCustos(List<CentroDeCusto> centroDeCustos) {
        this.centrosDeCustos = centroDeCustos;
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
    public Date getDataReferencia() {
        return dataReferencia;
    }
    public void setDataReferencia(Date dataReferencia) {
        this.dataReferencia = dataReferencia;
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
    public String getObervacao() {
        return obervacao;
    }
    public void setObervacao(String obervacao) {
        this.obervacao = obervacao;
    }
}
