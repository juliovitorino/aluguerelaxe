package br.com.jcv.backend.distancia;


/**
 * <h2>Distancia</h2>
 * <p>Classe para calculos geograficos, distancia e tempo gasto.</p>
 * @author Julio Vitorino
 *
 */
public class Distancia {
	
	// Constantes para conversao
	public static final int METROS = 0;
	public static final int KM = 1;

	public static final int DE_CARRO = 0;
	public static final int A_PE = 1;
	
	private static final int VELOCIDADE_BASE_CARRO = 50; //80Km/h
	private static final int VELOCIDADE_BASE_A_PE = 4; //4Km/h

	private static final double RADIUS = 6371.0;
	
	// Variaveis para latitudes e longitudes de p1 e p2
	private double p1Lat;
	private double p1Long;
	private double p2Lat;
	private double p2Long;
	
	// Armazenamento 
	private double resultado;
	
	public Distancia(double p1Lat, double p1Long, double p2Lat, double p2Long) {
		this.p1Lat = p1Lat;
		this.p1Long = p1Long;
		this.p2Lat = p2Lat;
		this.p2Long = p2Long;
		
		calcularDistancia2Pontos();
	}
	
	private void calcularDistancia2Pontos() {
		// Converte para radianos
		double p1LatRad = p1Lat * Math.PI / 180.0;
		double p1LongRad = p1Long * Math.PI / 180.0;
		double p2LatRad = p2Lat * Math.PI / 180.0;
		double p2LongRad = p2Long * Math.PI / 180.0;
		
		double dLat = p2LatRad + (p1LatRad * -1);
		double dLong = p2LongRad + (p1LongRad * -1);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(p1LatRad) * Math.cos(p2LatRad) * Math.sin(dLong / 2) * Math.sin(dLong / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 + (a*-1)));

		resultado = RADIUS * c; // resultado em Km
	}
	
	public double getDistancia(int modo) {
		switch(modo) {
		case METROS:
			return resultado * 1000;
		case KM:
			return resultado;
		default:
			return resultado;
		}
		
	}
	
	public double getTempoGasto(int modo) {
		switch(modo) {
		case A_PE:
			return resultado / VELOCIDADE_BASE_A_PE * 60;
		case DE_CARRO:
			return resultado / VELOCIDADE_BASE_CARRO * 60;
		default:
			return resultado;
		}
		
	}
	
	public DistanciaDTO getDTO() {
		DistanciaDTO dto = new DistanciaDTO();
		dto.distanciaKm = this.getDistancia(KM);
		dto.distanciaMt = this.getDistancia(METROS);
		dto.tempoGastoCarro = this.getTempoGasto(DE_CARRO);
		dto.tempoGastoPe = this.getTempoGasto(A_PE);
		return dto;
	}

}
