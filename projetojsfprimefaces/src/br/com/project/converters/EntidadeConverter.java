package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Entidade;

/**
 * Responsavel por efetuar a conversão nos combos de campos de estado
 * @author ander
 *
 */
@FacesConverter(forClass = Entidade.class, value="entidadeConverter")
public class EntidadeConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.isEmpty()) {
				return HibernateUtil.getCurrentSession().get(
						Entidade.class, new Long(value));
		}
		return value;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			Entidade c = (Entidade) value;
			return c.getEnt_codigo() != null && c.getEnt_codigo() > 0 ? c.getEnt_codigo().toString() : null;
		} else {
			return null;
		}
	}

}
