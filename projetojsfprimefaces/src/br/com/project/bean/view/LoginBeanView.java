package br.com.project.bean.view;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.SessionControllerImpl;
import br.com.srv.interfaces.SrvLogin;


@Controller
@Scope(value= "request")
@ManagedBean(name =  "loginBeanView")
public class LoginBeanView extends BeanManagedViewAbstract {

	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	@Resource
	private SessionControllerImpl sessionController;
	
	@Autowired
	private SrvLogin srvLogin;
	
	//Getter and Setters

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	//************************ M�todos para chamar na p�gina jsf  *****************************************************************
	
	@RequestMapping(value = "**/invalidar_session", method = RequestMethod.POST)
	public void invalidarSessionMetodos(HttpServletRequest httpServletRequest) throws Exception {
		
		System.out.println("testetesteteste");
		
	}
	
	public void invalidar(ActionEvent event) throws Exception {
		 RequestContext context = RequestContext.getCurrentInstance();
	        FacesMessage message = null;
	        boolean loggedIn = false;
	         //Verifica se o usu�rio � aut�ntico ...
	        if(srvLogin.autentico(getUsername(), getPassword())) {
	        	sessionController.invalidateSession(getUsername());
	        	loggedIn = true;
	        } else {
	            loggedIn = false;
	            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Acesso negado!, login ou senha incorretos.", "");
	        }
	        
	        if (message != null)
	        	FacesContext.getCurrentInstance().addMessage("msg", message);
	        
	        context.addCallbackParam("loggedIn", loggedIn);
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
	
	
	//***********************************************   Fim m�todos  ***********************************************************


}