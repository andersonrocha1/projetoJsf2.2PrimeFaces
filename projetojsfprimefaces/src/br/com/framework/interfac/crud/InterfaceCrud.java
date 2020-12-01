package br.com.framework.interfac.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.implementacao.crud.SimpleJdbcClassImpl;


@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable {
	// M�todo gen�rico que salva qualquer objeto
	void save(T obj) throws Exception;

	
	void persist(T obj) throws Exception;
	
	//Salva ou atualiza
	void saveOrUpdate(T obj) throws Exception;
	
	//Realiza o update /atualiza��o de dados
	void update(T obj) throws Exception;
	
	
	//Realiza a exclus�o dos dados
	void delete(T obj) throws Exception;
	
	//Salva ou atualiza e retorna o objeto em estado de persist�ncia
	T merge(T obj) throws Exception;
	
	
	//Carrega a lista de dados de determinada classe
	List<T> findList(Class<T> objs) throws Exception;
	
	
	Object findById(Class<T> entidade, Long id) throws Exception;
	
	
	T findByPorId(Class<T> entidade, Long id) throws Exception;
	
	
	List<T> findListByQueryDinamica(String s) throws Exception;
	
	//Executar update com HQL 
	void executeUpdateQueryDinamica(String s) throws Exception;
	
	
	//Executa update com SQL puro
	void executeUpdateSQLDinamica(String s) throws Exception;
	
	// Limpa a sess�o do Hibernate
	void clearSession() throws Exception;
	
	// Retira um objeto da sess�o do hibernate
	void evict (Object objs) throws Exception;
	
	
	Session getSession() throws Exception;
	
	
	List<?> getListSQLDinamica(String sql) throws Exception;
	
	
	
	//JDBC do Spring
	JdbcTemplate geJdbcTemplate();
	
	SimpleJdbcTemplate geSimpleJdbcTemplate();
	
	SimpleJdbcInsert getSimpleJdbcInsert();
	
	//SimpleJdbcClassImpl getSimpleJdbcClassImpl();
	
	Long totalRegistro(String table) throws Exception;
	
	
	Query obterQuery(String query) throws Exception;
	
	
	
	
	//Carregamento din�mico com JSF e PrimeFaces
	List<T> findListByQueryDinamica(String query,  int iniciaNoRegistro, int maximoResultado) throws Exception;


	
		
	
	
	
}
