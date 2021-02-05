package br.com.dao.implementacao;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.repository.interfaces.RepositoryLogin;


@Repository
public class DaoLogin extends ImplementacaoCrud<Object> implements RepositoryLogin {

	private static final long serialVersionUID = 1L;

	@Override
	public void atualizaBanco(String sql, String fileName) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contemVersao(String fileSql) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contemTabela(String string) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean autentico(String login, String senha) throws Exception {
		String sql = "select count(1) as autenticar from entidade where ent_login = ? and ent_senha = ?";
		SqlRowSet sqlRowSet = super.getJdbcTemplate().queryForRowSet(sql, new Object[]{login, senha});
		return sqlRowSet.next() ? sqlRowSet.getInt("autenticar") > 0 : false;
	}

}
