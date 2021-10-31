package co.edu.uniquindio.marketplace.model;

public enum EstadoProducto {

	VENDIDO(0), PUBLICADO(1), CANCELADO(2);
	
	private int numTipo;

	private EstadoProducto(int numTipo) {
		this.numTipo = numTipo;
	}

		public int getNumTipo() {
		return numTipo;
	}

	public void setNumTipo(int numTipo) {
		this.numTipo = numTipo;
	}

	public EstadoProducto getEstadoProducto(int index) {

		switch(index) {

		case 0: return EstadoProducto.VENDIDO;

		case 1: return EstadoProducto.PUBLICADO;

		case 2: return EstadoProducto.CANCELADO;

		default: 
			
		return null;

		}

	}

}
