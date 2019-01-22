package br.com.solutis.desafio.security.jwt;

import java.io.Serializable;

public class JwtAuthenticationRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cpf;
    private String password;
    private String entidade;
    private String unidadeGestora;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String cpf, String password,String entidade,String unidadeGestora) {
        this.setCpf(cpf);
        this.setPassword(password);
        this.setEntidade(entidade);
        this.setUnidadeGestora(unidadeGestora);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public String getUnidadeGestora() {
        return unidadeGestora;
    }

    public void setUnidadeGestora(String unidadeGestora) {
        this.unidadeGestora = unidadeGestora;
    }
}
