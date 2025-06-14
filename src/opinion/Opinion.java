package opinion;

import java.time.LocalDate;

import usuario.TipoUsuario;

public class Opinion {
	private TipoOpinion tipoOpinion;
	private TipoUsuario tipoUsuario;
	private LocalDate fecha; 
	
	public Opinion(TipoOpinion tipoOpinion, TipoUsuario tipoUsuario) {
		this.tipoOpinion = tipoOpinion;
		this.tipoUsuario = tipoUsuario;
		this.fecha= LocalDate.now();
	}
	

	public TipoOpinion getTipoOpinion() {
		return tipoOpinion;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}


}
