package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

import br.com.project.annotation.IdentificaCampoPesquisa;


@SuppressWarnings("deprecation")
@Audited
@Entity
@Table(name = "entidade")
@SequenceGenerator(name = "entidade_seq", sequenceName = "entidade_seq", initialValue = 1, allocationSize = 1)
public class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "ent_codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entidade_seq")
	private Long ent_codigo;
	
	private String ent_login = null;
	private String ent_senha;
	private boolean ent_inativo = false;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date ent_ultimaacesso;
	

	
	
	public boolean isEnt_inativo() {
		return ent_inativo;
	}
	public void setEnt_inativo(boolean ent_inativo) {
		this.ent_inativo = ent_inativo;
	}
	public Date getEnt_ultimaacesso() {
		return ent_ultimaacesso;
	}
	public void setEnt_ultimaacesso(Date ent_ultimaacesso) {
		this.ent_ultimaacesso = ent_ultimaacesso;
	}
	public Long getEnt_codigo() {
		return ent_codigo;
	}
	public void setEnt_codigo(Long ent_codigo) {
		this.ent_codigo = ent_codigo;
	}
	public String getEnt_login() {
		return ent_login;
	}
	public void setEnt_login(String ent_login) {
		this.ent_login = ent_login;
	}
	public String getEnt_senha() {
		return ent_senha;
	}
	public void setEnt_senha(String ent_senha) {
		this.ent_senha = ent_senha;
	}
	
	

}
