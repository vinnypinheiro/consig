package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Usuario;
import br.com.solutis.desafio.repository.UsuarioRepository;
import br.com.solutis.desafio.security.LoginResponse;
import br.com.solutis.desafio.security.jwt.JwtUser;
import br.com.solutis.desafio.security.jwt.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by fabricio on 02/09/18.
 */

@Service("LoginService")
public class LoginService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario findByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }


        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            Usuario usuario = null;
            try {
                usuario = usuarioRepository.findByEmail(email);
            }catch (Exception e){
                e.printStackTrace();
            }
            if (usuario == null) {
                throw new UsernameNotFoundException(String.format("Nenhum usuário encontrado com o cpf '%s'.", email));
            } else {
                LoginResponse response = new LoginResponse();
                response.setUsuario(usuario);
                response.setData(new Date());
                JwtUser user = JwtUserFactory.create(usuario, response);
                return  user;
            }
    }




}
