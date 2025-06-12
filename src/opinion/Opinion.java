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


}
