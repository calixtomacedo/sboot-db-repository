package br.com.cmdev.data.jdbc.config;

import br.com.cmdev.data.jdbc.utils.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    //@Autowired
    //private final TokenJwtService tokenJwtService;
    //private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJwt = recoverToken(request);

        if (Objects.nonNull(tokenJwt)) {
            //var subjectEmail = tokenService.validateTokenJwt(tokenJwt);
            //var user = userRepository.findByEmail(email);
            //var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            //SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader(Constants.HEADER_AUTHORIZATION);
        if(Objects.nonNull(authorizationHeader)) {
            return authorizationHeader.replace("Bearer ", "");
        }
        throw new RuntimeException("Essa porra não foi passado");
   }

}
