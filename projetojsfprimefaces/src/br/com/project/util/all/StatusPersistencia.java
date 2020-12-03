package br.com.project.util.all;

public enum StatusPersistencia {
	
	ERRO("Erro"), SUCESSO("Sucesso"),
	OBJETO_REFERENCIADO("Impossível remover, este dado pode estar relacionado a outra informação!");
	
	private String name;
	
	private StatusPersistencia(String s) {
		this.name = s;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
	

}
