package opinion;

import java.time.LocalDate;

public class Opinion {
	private TipoOpinion tipoOpinion;
	private String tipoUsuario;
	private LocalDate fecha; 
	
	public Opinion(TipoOpinion tipoOpinion, String tipoUsuario) {
		this.tipoOpinion = tipoOpinion;
		this.tipoUsuario = tipoUsuario;
		this.fecha= LocalDate.now();
	}
	

	public TipoOpinion getTipoOpinion() {
		return tipoOpinion;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	public boolean esDeExperto() {
		// TODO Auto-generated method stub (LO CREE PARA QUE NO ME ROMPA LEI! PERDON)
		return false;
	}

	public TipoOpinion getTipo() {
		// TODO Auto-generated method stub(TAMBNIEN LO CREE APRA QUE NO ROMPA! ACA DEBERIA DE RETORNAR ALGO DE TIPO DE OPINION DE ENUM)
		return null;
	}

}
