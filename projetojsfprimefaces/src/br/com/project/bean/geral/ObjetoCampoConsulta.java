package br.com.project.bean.geral;

import java.io.Serializable;
import java.util.Comparator;

public class ObjetoCampoConsulta implements Serializable, Comparator<ObjetoCampoConsulta> {

	private static final long serialVersionUID = 1L;

	private String descricao;
	private String campobanco;
	private Object tipoclass;
	private Integer principal;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCampobanco() {
		return campobanco;
	}

	public void setCampobanco(String campobanco) {
		this.campobanco = campobanco;
	}

	public Object getTipoclass() {
		return tipoclass;
	}

	public void setTipoclass(Object tipoclass) {
		this.tipoclass = tipoclass;
	}

	public Integer getPrincipal() {
		return principal;
	}

	public void setPrincipal(Integer principal) {
		this.principal = principal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campobanco == null) ? 0 : campobanco.hashCode());
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
		ObjetoCampoConsulta other = (ObjetoCampoConsulta) obj;
		if (campobanco == null) {
			if (other.campobanco != null)
				return false;
		} else if (!campobanco.equals(other.campobanco))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return getDescricao();
	}

	@Override
	public int compare(ObjetoCampoConsulta o1, ObjetoCampoConsulta o2) {

		return ((ObjetoCampoConsulta) o1).getPrincipal().compareTo(((ObjetoCampoConsulta) o2).getPrincipal());
	}

}
