package ubicacion;

public class Ubicacion {
	private double latitud;
	private double longitud;
	public Ubicacion(double latitud, double longitud) {
		this.latitud = latitud; //Math.toRadians(latitud)
		this.longitud = longitud; //Math.toRadians(longitud)
	}
	public double getLatitud() {
		return latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	
}
