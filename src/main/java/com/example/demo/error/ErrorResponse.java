package com.example.demo.error;

public class ErrorResponse {

    private final int codigo;
	private final String mensaje;

    /**
	 * 
	 * @param String message
	 */
	public ErrorResponse(Integer code, String mensaje) {
		super();
		this.codigo = code;
		this.mensaje = mensaje;

	}// closoure construct

	public String getMensaje() {
		return mensaje;
	}
 
	public int getCodigo() {
		return codigo;
	}
	
}
