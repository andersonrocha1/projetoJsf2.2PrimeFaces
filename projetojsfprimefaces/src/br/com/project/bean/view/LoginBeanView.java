package br.com.project.bean.view;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.project.bean.geral.BeanManagedViewAbstract;


@Controller
@Scope(value="request")
@ManagedBean(name="loginBeanView")
public class LoginBeanView extends BeanManagedViewAbstract {

	
	private static final long serialVersionUID = 1L;

}
