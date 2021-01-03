package br.com.project.bean.geral;

import org.springframework.stereotype.Component;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.report.util.BeanReportView;



/**
 * Respons�vel pela rotina de consulta e abstra��o de m�todos de CRUD e outros padr�es...
 * 
 * @author ander
 *
 */
@Component
public abstract class BeanManagedViewAbstract extends BeanReportView {

	
	private static final long serialVersionUID = 1L;
	
	protected abstract Class<?> getClassImplemente();
	
	protected abstract InterfaceCrud<?> getController();

}
