package br.com.project.bean.geral;

import org.springframework.stereotype.Component;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.report.util.BeanReportView;



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

}
