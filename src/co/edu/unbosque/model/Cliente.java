package co.edu.unbosque.model;

public class Cliente {
	private String nombres;
	private String docuemto;
	private String contrase�a;
	private String celular;
	private String tipoDocumento;
	private String apellidos;
	private String correo;
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
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
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
	@Override
	public String toString() {
		return "Cliente [nombres=" + nombres + ", docuemto=" + docuemto + ", contrase�a=" + contrase�a + ", celular="
				+ celular + ", tipoDocumento=" + tipoDocumento + ", apellidos=" + apellidos + ", correo=" + correo
				+ "]";
	}
	

}
