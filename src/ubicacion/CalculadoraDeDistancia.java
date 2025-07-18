package ubicacion;

import java.util.List;
import java.util.stream.Collectors;

import muestra.Muestra;

public class CalculadoraDeDistancia {

	// Ver si podemos migrar el Math.toRadians a la ubicacion para no repetir
	// codigo.
	public double distanciaEntreDosUbicaciones(Ubicacion ubicacion1, Ubicacion ubicacion2) {
		double radioTierra = 6371.0;
		double deltaLat = Math.toRadians(ubicacion2.getLatitud()) - Math.toRadians(ubicacion1.getLatitud());
		double deltaLon = Math.toRadians(ubicacion2.getLongitud()) - Math.toRadians(ubicacion1.getLongitud());

		double a = Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(Math.toRadians(ubicacion1.getLatitud()))
				* Math.cos(Math.toRadians(ubicacion2.getLatitud())) * Math.pow(Math.sin(deltaLon / 2), 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return radioTierra * c;
	}

	public List<Ubicacion> ubicacionesCercanasA(Ubicacion referencia, List<Ubicacion> ubicaciones,
			double distanciaMetros) {
		return ubicaciones.stream()
				.filter(ubicacion -> this.distanciaEntreDosUbicaciones(referencia, ubicacion) <= distanciaMetros)
				.collect(Collectors.toList());
	}

	public List<Muestra> muestrasCercanasA(Muestra muestraReferencia, List<Muestra> muestras, double distanciaMetros) {
		return muestras.stream().filter(muestra -> this.distanciaEntreDosUbicaciones(muestraReferencia.getUbicacion(),
				muestra.getUbicacion()) <= distanciaMetros).collect(Collectors.toList());
	}

}
