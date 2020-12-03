package br.com.project.util.all;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class Messagens extends FacesContext implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	public Messagens() {
		
	}
	
	public static FacesContext getFacesContext() {
		
		return FacesContext.getCurrentInstance();
		
	}
	
	private static boolean facesContextValido() {
		return getFacesContext() != null;
	}
	
	public static void msg(String msg) {
		if(facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(msg));
		}
	}
	
	public static void responseOperation(StatusPersistencia statusPersistencia) {
		if(statusPersistencia != null && statusPersistencia.equals(StatusPersistencia.SUCESSO)) {
			
			sucesso();
			
		}else if(statusPersistencia != null && statusPersistencia.equals(StatusPersistencia.OBJETO_REFERENCIADO)) {
			msgSeverityFatal(StatusPersistencia.OBJETO_REFERENCIADO.toString());
		}else {
			erroOperacao();
		}
	}
	
	public static void sucesso() {
		msgSeverityInfor(Constante.OPERACAO_REALIZADA_COM_SUCESSO);
	}
	
	public static void erroOperacao() {
		if(facesContextValido()) {
			msgSeverityFatal(Constante.ERRO_NA_OPERACAO);
		}
	}
	
	
	public static void msgSeverityInfor(String msg) {
		if(facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		}
		
	}
	
	public static void msgSeverityWarn(String msg) {
		if(facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
		}
		
	}
	
	
	public static void msgSeverityFatal(String msg) {
		if(facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
		}
		
	}
	
	public static void msgSeverityError(String msg) {
		if(facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
		}
		
	}
	

}
