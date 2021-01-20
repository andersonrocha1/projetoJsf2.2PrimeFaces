package br.com.project.model.classes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import br.com.project.annotation.IdentificaCampoPesquisa;
import br.com.project.enums.TipoTitulo;
import br.com.project.enums.TituloOrigem;


@Audited
@Entity
@Table(name = "titulo")
@SequenceGenerator(name = "titulo_seq", sequenceName = "titulo_seq", initialValue = 1, allocationSize = 1)
public class Titulo implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "tit_codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "titulo_seq")
	private Long tit_codigo;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Tipo", campoConsulta = "tit_tipo")
	@Enumerated(EnumType.STRING)
	private TipoTitulo tit_tipo; //PAGAR OU RECEBER
	
	@IdentificaCampoPesquisa(descricaoCampo = "Origem", campoConsulta = "tit_origem")
	@Enumerated(EnumType.STRING)
	private TituloOrigem tit_origem;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Vencimento", campoConsulta = "tit_datavencimento")
	@Temporal(TemporalType.DATE)
	private Date tit_datavencimento;

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tit_datahora = new Date();
	
	@IdentificaCampoPesquisa(descricaoCampo = "Usuário abertura", campoConsulta = "ent_codigoabertura.ent_nomefantasia", principal = 1)
	@Basic
	@ManyToOne
	@JoinColumn(nullable = false, name = "ent_codigoabertura", updatable = false)
	@ForeignKey(name = "ent_codigoabertura_fk")
	private Entidade ent_codigoabertura = new Entidade();
	
	@IdentificaCampoPesquisa(descricaoCampo = "Usuário baixa", campoConsulta = "ent_codigobaixa.ent_nomefantasia")
	@Basic
	@ManyToOne
	@JoinColumn(nullable = true, name = "ent_codigobaixa", updatable = true)
	@ForeignKey(name = "ent_codigobaixa_fk")
	private Entidade ent_codigobaixa = new Entidade();
	
	
	@IdentificaCampoPesquisa(descricaoCampo = "Entidade responsável", campoConsulta = "ent_codigo.ent_nomefantasia")
	@Basic
	@ManyToOne
	@JoinColumn(nullable = false, name = "ent_codigo")
	@ForeignKey(name = "ent_codigo_fk")
	private Entidade ent_codigo = new Entidade();
	
	@IdentificaCampoPesquisa(descricaoCampo = "Valor R$", campoConsulta = "tit_valor")
	@Column(scale = 4, precision = 15)
	private BigDecimal tit_valor = null;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Desconto em baixa", campoConsulta = "tit_descontobaixa")
	@Column(scale = 4, precision = 15)
	private BigDecimal tit_descontobaixa = null;

	@IdentificaCampoPesquisa(descricaoCampo = "Acréscimo em baixa", campoConsulta = "tit_acrescimobaixa") 
	@Column(scale = 4, precision = 15)
	private BigDecimal tit_acrescimobaixa = null;

	@IdentificaCampoPesquisa(descricaoCampo = "Valor baixa", campoConsulta = "tit_valorbaixa")
	@Column(scale = 4, precision = 15)
	private BigDecimal tit_valorbaixa = null; 

	@IdentificaCampoPesquisa(descricaoCampo = "Observação", campoConsulta = "tit_observacao")
	@Column(columnDefinition = "text", length = 500)
	private String tit_observacao;
	
	private Boolean tit_baixado = Boolean.FALSE;



	public Long getTit_codigo() {
		return tit_codigo;
	}



	public void setTit_codigo(Long tit_codigo) {
		this.tit_codigo = tit_codigo;
	}



	public Entidade getEnt_codigoabertura() {
		return ent_codigoabertura;
	}



	public void setEnt_codigoabertura(Entidade ent_codigoabertura) {
		this.ent_codigoabertura = ent_codigoabertura;
	}



	public Entidade getEnt_codigo() {
		return ent_codigo;
	}



	public void setEnt_codigo(Entidade ent_codigo) {
		this.ent_codigo = ent_codigo;
	}



	public TipoTitulo getTit_tipo() {
		return tit_tipo;
	}



	public void setTit_tipo(TipoTitulo tit_tipo) {
		this.tit_tipo = tit_tipo;
	}



	public TituloOrigem getTit_origem() {
		return tit_origem;
	}



	public void setTit_origem(TituloOrigem tit_origem) {
		this.tit_origem = tit_origem;
	}



	public Date getTit_datavencimento() {
		return tit_datavencimento;
	}



	public void setTit_datavencimento(Date tit_datavencimento) {
		this.tit_datavencimento = tit_datavencimento;
	}



	public Date getTit_datahora() {
		return tit_datahora;
	}



	public void setTit_datahora(Date tit_datahora) {
		this.tit_datahora = tit_datahora;
	}



	public Entidade getEnt_codigobaixa() {
		return ent_codigobaixa;
	}



	public void setEnt_codigobaixa(Entidade ent_codigobaixa) {
		this.ent_codigobaixa = ent_codigobaixa;
	}



	public BigDecimal getTit_valor() {
		return tit_valor;
	}



	public void setTit_valor(BigDecimal tit_valor) {
		this.tit_valor = tit_valor;
	}



	public BigDecimal getTit_descontobaixa() {
		return tit_descontobaixa;
	}



	public void setTit_descontobaixa(BigDecimal tit_descontobaixa) {
		this.tit_descontobaixa = tit_descontobaixa;
	}



	public BigDecimal getTit_acrescimobaixa() {
		return tit_acrescimobaixa;
	}



	public void setTit_acrescimobaixa(BigDecimal tit_acrescimobaixa) {
		this.tit_acrescimobaixa = tit_acrescimobaixa;
	}



	public BigDecimal getTit_valorbaixa() {
		return tit_valorbaixa;
	}



	public void setTit_valorbaixa(BigDecimal tit_valorbaixa) {
		this.tit_valorbaixa = tit_valorbaixa;
	}



	public String getTit_observacao() {
		return tit_observacao;
	}



	public void setTit_observacao(String tit_observacao) {
		this.tit_observacao = tit_observacao;
	}



	public Boolean getTit_baixado() {
		return tit_baixado;
	}



	public void setTit_baixado(Boolean tit_baixado) {
		this.tit_baixado = tit_baixado;
	}
	
	
	
	
	

}
