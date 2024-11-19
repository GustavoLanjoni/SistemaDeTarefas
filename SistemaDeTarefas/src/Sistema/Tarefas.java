package Sistema;

public class Tarefas {
	String titulos;
	String descricao;
	String status; 
	
	public Tarefas(String titulos, String descricao, String status) {
		this.titulos = titulos;
		this.descricao = descricao;
		this.status = status;
	}

	public String getTitulos() {
		return titulos;
	}

	public void setTitulos(String titulos) {
		this.titulos = titulos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
