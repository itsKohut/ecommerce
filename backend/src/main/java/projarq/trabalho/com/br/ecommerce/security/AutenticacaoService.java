package projarq.trabalho.com.br.ecommerce.security;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import projarq.trabalho.com.br.ecommerce.json.response.JwtResponse;

@Service
public class AutenticacaoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @SneakyThrows
    public JwtResponse criarTokenAuth(String email, String password) {

        final Authentication auth = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(auth);

        return new JwtResponse(jwtTokenService.generateToken(auth));
    }

    private Authentication authenticate(String email, String password) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new DisabledException("Usuário desabilitado", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credenciais inválidas", e);
        }
    }

}
