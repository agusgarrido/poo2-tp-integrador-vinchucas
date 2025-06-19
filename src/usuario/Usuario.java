package usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;
import opinion.Opinion;
import opinion.TipoOpinion;
import sistema.Sistema;
import ubicacion.Ubicacion;

public class Usuario {
	private TipoUsuario tipo;
	private List<Opinion> opinionesEnviadas;
	private Sistema sistema;

	public Usuario() {
		this.tipo = new UsuarioBasico();
		this.opinionesEnviadas = new ArrayList<Opinion>();
	}

	public void enviarMuestra(String foto, Ubicacion ubicacion, TipoOpinion tipoOpinion, Sistema sistema) {
		Opinion opinionInicial = new Opinion(tipoOpinion, tipo, this);
		Muestra muestra = new Muestra(this, foto, ubicacion, opinionInicial);
		sistema.addMuestra(muestra);
	}

	public void darOpinion(TipoOpinion tipoOpinion, Muestra muestra) {
		Opinion opinion = new Opinion(tipoOpinion, tipo, this);
		muestra.addOpinion(opinion);
		opinionesEnviadas.add(opinion);
	}

	public void cambiarCategoria(LocalDate fecha, Sistema sistema) {
		this.getTipo().cambiarCategoria(this, fecha, sistema);
	}

	public int cantidadMuestrasEnviadas(Sistema sistema) {
		return (int) sistema.getMuestras().stream().filter(muestra -> enUltimos30Dias(muestra.getFechaDeCreacion())).count();
	}

	private boolean enUltimos30Dias(LocalDate fecha) {
		LocalDate hoy = LocalDate.now();
		LocalDate hace30Dias = hoy.minusDays(30);
		return !fecha.isBefore(hace30Dias) && !fecha.isAfter(hoy);
	}

	public int cantidadOpinionesEnviadas(LocalDate fecha) {
		return (int) opinionesEnviadas.stream().filter(opinion -> enUltimos30Dias(opinion.getFecha())).count();
	}

	public TipoUsuario getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
	public boolean esExperto() {
	    return tipo.esExperto();
	}

}