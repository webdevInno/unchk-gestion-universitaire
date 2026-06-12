package sn.unchk.unchk_backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sn.unchk.unchk_backend.repository.UtilisateurRepository;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final UtilisateurRepository utilisateurRepository; // ✅ AJOUT

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();

        if ("OPTIONS".equalsIgnoreCase(method)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!path.startsWith("/api/utilisateurs")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (path.equals("/api/utilisateurs/login") ||
                path.equals("/api/utilisateurs/inscription")) {
            filterChain.doFilter(request, response);
            return;
        }

        String userEmail = request.getHeader("X-User-Email");
        String userRole  = request.getHeader("X-User-Role");

        if (userRole == null || userEmail == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Non autorise - connectez-vous !");
            return;
        }

        // ✅ Vérifier en BASE que cet email est bien un admin actif
        boolean isRealAdmin = utilisateurRepository.findByEmail(userEmail)
                .map(u ->
                        u.getActif() != null && u.getActif() &&
                                (u.getRole().equals("admin") || u.getRole().equals("administratif"))
                )
                .orElse(false);
        if (!isRealAdmin) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Acces interdit - reserve aux administrateurs !");
            return;
        }

        filterChain.doFilter(request, response);
    }
}