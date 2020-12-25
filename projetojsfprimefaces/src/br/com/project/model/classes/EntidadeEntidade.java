package br.com.project.model.classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "entidade_entidade")
@SequenceGenerator(name = "entidade_entidade_seq", sequenceName = "entidade_entidade_seq", initialValue = 1, allocationSize = 1)
public class EntidadeEntidade implements Serializable {

	private static final long serialVersionUID = -5109692706237143193L;

	@Id
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "ents_codigo")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entidade_entidade_seq")
	private Long ents_codigo;

	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	protected int getVersionNum() { 
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}



	public Long getEnts_codigo() {
		return ents_codigo;
	}

	public void setEnts_codigo(Long ents_codigo) {
		this.ents_codigo = ents_codigo;
	}


	

}
