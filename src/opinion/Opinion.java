package opinion;

import java.time.LocalDate;

import usuario.TipoUsuario;
import usuario.Usuario;

public class Opinion {
	private TipoOpinion tipoOpinion;
	private TipoUsuario tipoUsuario;
	private LocalDate fecha;
	private Usuario usuario;

	public Opinion(TipoOpinion tipoOpinion, TipoUsuario tipoUsuario, Usuario usuario) {
		this.tipoOpinion = tipoOpinion;
		this.tipoUsuario = tipoUsuario;
		this.fecha = LocalDate.now();
		this.usuario = usuario;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public Boolean esDeExperto() {
		return tipoUsuario.esExperto();
	}

}
