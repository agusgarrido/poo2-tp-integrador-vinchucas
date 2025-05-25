package muestra;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import opinion.Opinion;
import usuario.Usuario;

public class Muestra {

	private EstadoMuestra estadoMuestra;
	private List<Opinion> opiniones;
	private Usuario created_by;
	private LocalDateTime dateCreated;
	private String fotoMuestra;
	private String ubicacion; // lo dejo asi para poder modelarlo mas tarde

	public Muestra(Usuario user, String foto, String ubi) {
		this.estadoMuestra = new MuestraInicial();
		this.opiniones = new ArrayList<Opinion>();
		this.created_by = user;
		this.dateCreated = LocalDateTime.now();
		this.fotoMuestra = foto;
		this.ubicacion = ubi;

	}

}
