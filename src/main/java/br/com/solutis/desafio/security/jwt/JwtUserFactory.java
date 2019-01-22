package br.com.solutis.desafio.security.jwt;

import br.com.solutis.desafio.domain.Usuario;
import br.com.solutis.desafio.security.LoginResponse;
import br.com.solutis.desafio.security.ProfileEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class JwtUserFactory {
	 private JwtUserFactory() {
	    }

	    public static JwtUser create(Usuario user, LoginResponse loginResponse) {
	 	String[] t = {ProfileEnum.ROLE_ADMIN.toString()};
	        return new JwtUser(
	                user.getId().toString(),
	                user.getEmail(),
	                user.getSenha(),
					"",
					"",
	                mapToGrantedAuthorities(t),
                    loginResponse
	        );
	    }

	    private static List<GrantedAuthority> mapToGrantedAuthorities(String[] profileEnum) {
	    		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); 
	    		authorities.add(new SimpleGrantedAuthority(profileEnum.toString())); 
	    		return   authorities ;
	    }
}
