package br.com.project.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;
import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.acessos.Permissao;
import br.com.project.annotation.IdentificaCampoPesquisa;
import br.com.project.enums.TipoCadastro;
import br.com.project.enums.TipoPessoa;



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
	
	@Enumerated(EnumType.STRING)
	private TipoCadastro ent_tipo;

	@Enumerated(EnumType.STRING)
	private TipoPessoa ent_tipopessoa;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Nome Fantasia", campoConsulta = "ent_nomefantasia", principal = 1)
	@Column(length = 100)
	private String ent_nomefantasia;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date ent_ultimaacesso;
	
	@CollectionOfElements
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@JoinTable(name = "entidadeacesso", uniqueConstraints = { @UniqueConstraint(name = "unique_acesso_entidade_key", columnNames = {
			"ent_codigo", "esa_codigo" }) }, joinColumns = { @JoinColumn(name = "ent_codigo") })
	@Column(name = "esa_codigo", length = 20)
	private Set<String> acessos = new HashSet<String>();
	
	private String tipoEntidade = "";
	

	
	
	public TipoCadastro getEnt_tipo() {
		return ent_tipo;
	}
	public void setEnt_tipo(TipoCadastro ent_tipo) {
		this.ent_tipo = ent_tipo;
	}
	public TipoPessoa getEnt_tipopessoa() {
		return ent_tipopessoa;
	}
	public void setEnt_tipopessoa(TipoPessoa ent_tipopessoa) {
		this.ent_tipopessoa = ent_tipopessoa;
	}
	public String getTipoEntidade() {
		return tipoEntidade;
	}
	public void setTipoEntidade(String tipoEntidade) {
		this.tipoEntidade = tipoEntidade;
	}
	public Set<String> getAcessos() {
		return acessos;
	}
	public void setAcessos(Set<String> acessos) {
		this.acessos = acessos;
	}
	public String getEnt_nomefantasia() {
		return ent_nomefantasia;
	}
	public void setEnt_nomefantasia(String ent_nomefantasia) {
		this.ent_nomefantasia = ent_nomefantasia;
	}
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
	
	
	public List<String> getAcessosOrdenadas() {
		List<String> retorno = new ArrayList<String>();
		for (String acesso : acessos) {
			retorno.add(acesso);
		}
		Collections.sort(retorno);
		return retorno;
	}

	public Set<Permissao> getAcessosPermissao() {
		Set<Permissao> permissoes = new HashSet<Permissao>();
		for (String acesso : getAcessosOrdenadas()) {
			for (Permissao acess : Permissao.values()) {
				if (acesso.equalsIgnoreCase(acess.name())) {
					permissoes.add(acess);
				}
			}
		}
		return permissoes;
	}
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ent_codigo == null) ? 0 : ent_codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entidade other = (Entidade) obj;
		if (ent_codigo == null) {
			if (other.ent_codigo != null)
				return false;
		} else if (!ent_codigo.equals(other.ent_codigo))
			return false;
		return true;
	}
	
	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("ent_codigo", ent_codigo);
		map.put("ent_login", ent_login);
		map.put("ent_nomefantasia", ent_nomefantasia);
		return new JSONObject(map);
	}

}
