package br.com.solutis.desafio.controller;

import br.com.solutis.desafio.domain.Usuario;
import br.com.solutis.desafio.security.LoginResponse;
import br.com.solutis.desafio.security.jwt.JwtAuthenticationRequest;
import br.com.solutis.desafio.security.jwt.JwtTokenUtil;
import br.com.solutis.desafio.security.jwt.JwtUser;
import br.com.solutis.desafio.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private LoginService loginService;


    @PostMapping(value="/api/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getCpf(),
                        authenticationRequest.getPassword()

                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = loginService.loadUserByUsername(authenticationRequest.getCpf());
        final String token = jwtTokenUtil.generateToken(userDetails);
        LoginResponse response = ((JwtUser)authentication.getPrincipal()).getLoginResponse();
        response.setToken(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value="/api/refresh")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String email = jwtTokenUtil.getUsernameFromToken(token);
        final Usuario user = loginService.findByEmail(email);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            LoginResponse response = new LoginResponse();
            response.setUsuario(user);
            response.setData(new Date());
            response.setToken(refreshedToken);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
