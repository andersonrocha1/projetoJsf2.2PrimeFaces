package br.com.project.bean.geral;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.annotation.IdentificaCampoPesquisa;
import br.com.project.enums.CondicaoPesquisa;
import br.com.project.report.util.BeanReportView;
import br.com.project.util.all.UtilitariaRegex;



/**
 * Responsável pela rotina de consulta e abstração de métodos de CRUD e outros padrões...
 * 
 * @author ander
 *
 */
@Component
public abstract class BeanManagedViewAbstract extends BeanReportView {

	
	private static final long serialVersionUID = 1L;
	
	protected abstract Class<?> getClassImplemente();
	
	protected abstract InterfaceCrud<?> getController();
	
	public ObjetoCampoConsulta objetoCampoConsultaSelecionado;
	
	public List<SelectItem> listaCampoPesquisa ;
	
	public List<SelectItem> listaCondicaoPesquisa;
	
	public CondicaoPesquisa condicaoPesquisaSelecionado;
	
	public String valorPesquisa;
	
	public abstract String condicaoAndParaPesquisa() throws Exception;
	
	
	public List<SelectItem> getListaCondicaoPesquisa() {
		listaCondicaoPesquisa = new ArrayList<SelectItem>();
		for(CondicaoPesquisa condicaoPesquisa : CondicaoPesquisa.values()) {
			listaCondicaoPesquisa.add(new SelectItem(condicaoPesquisa, condicaoPesquisa.toString()));
			
		}
		return listaCondicaoPesquisa;
	}
	
	
	
	public String getValorPesquisa() {
		return valorPesquisa != null ? new UtilitariaRegex().retiraAcentos(valorPesquisa) : "";
	}

	public void setValorPesquisa(String valorPesquisa) {
		this.valorPesquisa = valorPesquisa;
	}

	public void setCondicaoPesquisaSelecionado(CondicaoPesquisa condicaoPesquisaSelecionado) {
		this.condicaoPesquisaSelecionado = condicaoPesquisaSelecionado;
	}
	
	public CondicaoPesquisa getCondicaoPesquisaSelecionado() {
		return condicaoPesquisaSelecionado;
	}

	public ObjetoCampoConsulta getObjetoCampoConsultaSelecionado() {
		return objetoCampoConsultaSelecionado;
	}
	
	

	public void setObjetoCampoConsultaSelecionado(ObjetoCampoConsulta objetoCampoConsultaSelecionado) {

		if(objetoCampoConsultaSelecionado != null) {
		
			for(Field field : getClassImplemente().getDeclaredFields()) {
			
				if(field.isAnnotationPresent(IdentificaCampoPesquisa.class)) {
					
					if(objetoCampoConsultaSelecionado.getCampobanco().equalsIgnoreCase(field.getName())) {
						String descricao = field.getAnnotation(IdentificaCampoPesquisa.class).descricaoCampo();
					
						objetoCampoConsultaSelecionado.setDescricao(descricao);
						objetoCampoConsultaSelecionado.setTipoclass(field.getType().getCanonicalName());
						objetoCampoConsultaSelecionado.setPrincipal(field.getAnnotation(IdentificaCampoPesquisa.class).principal());
						
						break;							
					}				
				}
			
			}
		
		}
	
			this.objetoCampoConsultaSelecionado = objetoCampoConsultaSelecionado;
	}
	
	//Lista da combo de pesquisa
	public List<SelectItem> getListaCampoPesquisa() {
		
		listaCampoPesquisa = new ArrayList<SelectItem>();
		List<ObjetoCampoConsulta> listTemp = new ArrayList<ObjetoCampoConsulta>();
		
		for(Field field : getClassImplemente().getDeclaredFields() ) {
			
			if(field.isAnnotationPresent(IdentificaCampoPesquisa.class)) {
				
				String descricao = field.getAnnotation(IdentificaCampoPesquisa.class).descricaoCampo();
				String descricaoCampoPesquisa = field.getAnnotation(IdentificaCampoPesquisa.class).campoConsulta();
				int isPrincipal = field.getAnnotation(IdentificaCampoPesquisa.class).principal();
				
				ObjetoCampoConsulta objetoCampoConsulta = new ObjetoCampoConsulta();
				objetoCampoConsulta.setDescricao(descricao);
				objetoCampoConsulta.setCampobanco(descricaoCampoPesquisa);
				objetoCampoConsulta.setTipoclass(field.getType().getCanonicalName());
				objetoCampoConsulta.setPrincipal(isPrincipal);
				listTemp.add(objetoCampoConsulta);
				
				
			}
			
		}
		
		orderReverse(listTemp);
		
		for(ObjetoCampoConsulta objetoCampoConsulta : listTemp) {
			listaCampoPesquisa.add(new SelectItem(objetoCampoConsulta));
		}
		
		return listaCampoPesquisa;
	}

	private void orderReverse(List<ObjetoCampoConsulta> listTemp) {
		Collections.sort(listTemp, new Comparator<ObjetoCampoConsulta>() {

			@Override
			public int compare(ObjetoCampoConsulta objt1, ObjetoCampoConsulta objt2) {
				
				return objt1.getPrincipal().compareTo(objt2.getPrincipal());
			}
		});
		
	}

	public String getSqlLazyQuery() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select entity from ");
	
		sql.append(getQueryConsulta());
		sql.append(" order by entity.");
		sql.append(objetoCampoConsultaSelecionado.getCampobanco());
		return sql.toString();
	}

	private String getQueryConsulta() {
		
		return null;
	}

	public int totalRegistroConsulta() throws Exception {
		Query query = getController().obterQuery(" select count(entity) from " + getQueryConsulta());
		Number result = (Number) query.uniqueResult();
		return result.intValue();
	}
	

	

}
