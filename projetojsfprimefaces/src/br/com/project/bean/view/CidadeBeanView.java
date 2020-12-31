package br.com.project.bean.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.CidadeController;
import br.com.project.model.classes.Cidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "cidadeBeanView")
public class CidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	
	private Cidade objetoSelecionado = new Cidade();
	
	@Autowired
	private CidadeController cidadeController;
	
	@Override
	public String save() throws Exception {
		
		objetoSelecionado = cidadeController.merge(objetoSelecionado);
		
		return "";
	}

	public Cidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Cidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public CidadeController getCidadeController() {
		return cidadeController;
	}

	public void setCidadeController(CidadeController cidadeController) {
		this.cidadeController = cidadeController;
	}

	
	
	

}
