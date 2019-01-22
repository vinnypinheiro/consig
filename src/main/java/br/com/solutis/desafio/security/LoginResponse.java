package br.com.solutis.desafio.security;

import br.com.solutis.desafio.domain.Usuario;

import java.util.Date;
import java.util.Map;

public class LoginResponse {
	
	private String token;
	Usuario usuario;
	public Date data;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
