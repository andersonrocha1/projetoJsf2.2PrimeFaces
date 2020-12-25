package br.com.dao.implementacao;

import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Entidade;
import br.com.repository.interfaces.RepositoryEntidade;

@Repository
public class DaoEntidade extends ImplementacaoCrud<Entidade> implements
		RepositoryEntidade {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Date getUltimoAcessoEntidadeLogada(String name) {
		SqlRowSet rowSet = super.geJdbcTemplate().queryForRowSet(
				"select ent_ultimaacesso from entidade where ent_inativo is false and ent_login = ?",
				new Object[] { name });
		return rowSet.next() ? rowSet.getDate("ent_ultimaacesso") : null;
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		String sql = "update entidade set ent_ultimaacesso = current_timestamp where ent_inativo is false and ent_login = ? ";
		super.geSimpleJdbcTemplate().update(sql, login);
	}

	@Override
	public boolean existeUsuario(String ent_login) {
		StringBuilder builder = new StringBuilder();
		builder.append(" select count(1) >= 1 from entidade where ent_login = '").append(ent_login).append("' ");
		return super.geJdbcTemplate().queryForObject(builder.toString(), Boolean.class);
	}

}
