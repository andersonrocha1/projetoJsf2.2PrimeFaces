package br.com.project.bean.view;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.EstadoController;
import br.com.project.model.classes.Estado;
import br.com.project.model.classes.Pais;

@Controller
@Scope(value = "session")
@ManagedBean(name = "estadoBeanView")
public class EstadoBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;


	@Resource
	private EstadoController estadoController;
	
	
	public List<SelectItem> getEstados() throws Exception {
		
		return estadoController.getListEstado();
	}


	@Override
	protected Class<Estado> getClassImplemente() {
		
		return Estado.class;
	}


	@Override
	protected InterfaceCrud<Estado> getController() {
		
		return estadoController;
	}


	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	


}
