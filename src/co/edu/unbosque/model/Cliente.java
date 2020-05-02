package co.edu.unbosque.model;

public class Cliente {
	private String nombres;
	private String docuemto;
	private String contraseña;
	private String celular;
	private String tipoDocumento;
	private String apellidos;
	private String correo;
	private String usuario; 
	private String tarjetaDeCredito;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getDocuemto() {
		return docuemto;
	}
	public void setDocuemto(String docuemto) {
		this.docuemto = docuemto;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getTarjetaDeCredito() {
		return tarjetaDeCredito;
	}
	public void setTarjetaDeCredito(String tarjetaDeCredito) {
		this.tarjetaDeCredito = tarjetaDeCredito;
	}
	@Override
	public String toString() {
		return "Cliente [nombres=" + nombres + ", docuemto=" + docuemto + ", contraseña=" + contraseña + ", celular="
				+ celular + ", tipoDocumento=" + tipoDocumento + ", apellidos=" + apellidos + ", correo=" + correo
				+ ", usuario=" + usuario + ", tarjetaDeCredito=" + tarjetaDeCredito + "]";
	}

}
