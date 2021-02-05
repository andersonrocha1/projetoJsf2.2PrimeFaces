package br.com.project.geral.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Entidade;
import br.com.srv.interfaces.SrvEntidade;

@Controller
public class EntidadeController extends ImplementacaoCrud<Entidade> implements
		InterfaceCrud<Entidade> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SrvEntidade srvEntidade;

	public Entidade findUserLogado(String userLogado) throws Exception {
		return super.findUninqueByProperty(Entidade.class, userLogado,
				"ent_login", " and entity.ent_inativo is false ");
	}

	public Date getUltimoAcessoEntidadeLogada(String login) {
		return srvEntidade.getUltimoAcessoEntidadeLogada(login);
	}

	public void updateUltimoAcessoUser(String login) {
		srvEntidade.updateUltimoAcessoUser(login);
	}



	public boolean existeUsuario(String ent_login) {
		return srvEntidade.existeUsuario(ent_login);
	}

	public Entidade findUninqueByPropertyId(Class<Entidade> class1, Long codResponsavel, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Entidade> pesquisaPorNome(String nome) throws Exception {
		
		return (List<Entidade>) getSession().createQuery(" from Entidade where ent_nomefantasia like '%" + nome + "%'").list();
	}

	public boolean existeCpf(String cpf) throws Exception {
		
	return super.findListByQueryDinamica("from Entidade where cpf = '"+ cpf +"'").size() > 0;
		
		
	}



}
