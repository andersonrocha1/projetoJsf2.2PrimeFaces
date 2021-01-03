package br.com.project.bean.view;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;

@Controller
@Scope(value = "session")
@ManagedBean(name ="mensagemBeanView")
public class MensagemBeanView  extends BeanManagedViewAbstract{

	private static final long serialVersionUID = 1L;
	
		@Override
		public String novo() throws Exception {
			System.out.println("M�todo novo Bean chamado!!!!!!!!");
			return "";
		}

		@Override
		protected Class<?> getClassImplemente() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected InterfaceCrud<?> getController() {
			// TODO Auto-generated method stub
			return null;
		}

}