package br.com.project.geral.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.enums.TipoTitulo;
import br.com.project.enums.TituloOrigem;
import br.com.project.model.classes.Titulo;
import br.com.repository.interfaces.RepositoryTitulo;
import br.com.srv.interfaces.SrvTitulo;

@Controller
public class TituloController extends ImplementacaoCrud<Titulo> implements
		InterfaceCrud<Titulo> {
	private static final long serialVersionUID = 1L;
	@Resource
	private SrvTitulo srvTitulo;
	@Resource
	private RepositoryTitulo repositoryTitulo;
	
	
	@RequestMapping("**/gerarGraficoInicial")
	public @ResponseBody  String gerarGraficoInicial(@RequestParam(value = "dias") int dias) {
		
		List<Map<String, Object>> titulosUltimosDias = getTituloUltimoDias(dias);
		
		int totalLinhas = titulosUltimosDias.size() + 1;
		boolean naoHaDados = false;
		if(totalLinhas <=1 ) {
			naoHaDados = true;
		}
		
		String[] dados = new String[totalLinhas];
		int contador = 0;
		if(naoHaDados) {
			dados[contador ++] = "[\"" + "Não Há Dados" + "\"," + "\"" + 0 + "\","  + "\"" + 0 + "\"]";
		}else {
			
			dados[contador] = "[\"" +"Tipo"+ "\"," + "\"" + "Quantidade" + "\","  + "\"" + "Média" + "\"]";
			contador ++;
			
			for(Map<String, Object> objeto : titulosUltimosDias) {
				dados[contador] = "[\"" + objeto.get("situacao") + "\"," + "\"" + objeto.get("quantidade") + "\","  + "\"" + objeto.get("media_valor") + "\"]";
				contador ++;
			}
		}
		 
		return Arrays.toString(dados);
	}

	private List<Map<String, Object>> getTituloUltimoDias(int dias) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("(Select count(1) as quantidade, tit_origem as situacao, trunc(avg(coalesce(tit_valor, 0.00)), 2) as media_valor "); 
				sql.append("from titulo "); 
				sql.append(" ");
				sql.append("where cast(tit_datahora as date) >= (current_date - "+dias+") and cast(tit_datahora as date) <= current_date ");
                sql.append(" ");
				sql.append("group by tit_origem ");
                sql.append(" ");
				sql.append("union ");
                sql.append(" ");
				sql.append("Select count(1) as quantidade, ");
				sql.append(" ");
				sql.append("case when tit_baixado then 'BAIXADO' else 'EM ABERTO' end as situacao, ");
				sql.append(" ");
				sql.append("trunc(avg(coalesce(tit_valor, 0.00)), 2) as media_valor ");
				sql.append(" ");
				sql.append("from titulo ");
				sql.append(" ");
				sql.append("where cast(tit_datahora as date) >= (current_date - "+dias+") and cast(tit_datahora as date) <= current_date ");
				sql.append(" ");
				sql.append("group by tit_baixado ");
                sql.append(" ");
				sql.append("union ");
                sql.append(" ");
				sql.append("Select count(1) as quantidade, tit_tipo as situacao, trunc(avg(coalesce(tit_valor, 0.00)), 2) as media_valor ");
				sql.append(" ");
				sql.append("from titulo ");
				sql.append(" ");
				sql.append("where cast(tit_datahora as date) >= (current_date - "+ dias +") and cast(tit_datahora as date) <= current_date ");
				sql.append(" ");
				sql.append("group by tit_tipo) order by quantidade, media_valor ");
	
		return super.getSimpleJdbcTemplate().queryForList(sql.toString());
	}

	public void setSrvTitulo(SrvTitulo srvTitulo) {
		this.srvTitulo = srvTitulo;
	}

	public void setRepositoryTitulo(RepositoryTitulo repositoryTitulo) {
		this.repositoryTitulo = repositoryTitulo;
	}

	public List<SelectItem> getListTipoTitulo() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (TipoTitulo tipoTitulo : TipoTitulo.values()) {
			items.add(new SelectItem(tipoTitulo.name(), tipoTitulo.toString()));
		}
		return items;
	}
	
	public List<SelectItem> getListTipoTituloOrigem() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (TituloOrigem tipoTitulo : TituloOrigem.values()) {
			items.add(new SelectItem(tipoTitulo.name(), tipoTitulo.toString()));
		}
		return items;
	}
	
	public List<Map<String, Object>> getMediaPorOrigem(int dias) {
		return repositoryTitulo.getMediaPorOrigem(dias);
	}
	
	public List<Map<String, Object>> getMediaPorTipoReceberPagar(int dias) {
		return repositoryTitulo.getMediaPorTipoReceberPagar(dias);
	}
	/*
	public List<Map<String, Object>> getTitulosUltimosDias(int dias) {
		return repositoryTitulo.getTitulosUltimosDias(dias);
	}*/
	
	public List<Map<String, Object>> getMediaPorTipoAbertoFechado(int dias) {
		return repositoryTitulo.getMediaPorTipoAbertoFechado(dias);
	}

}
