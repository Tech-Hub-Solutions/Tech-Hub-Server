package api.tech.hub.techhubapi.service.usuario.autenticacao;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import api.tech.hub.techhubapi.service.usuario.dto.UsuarioDetailsDto;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.util.URLEncoder;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public static String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";
    public static String APP_NAME = "TechHub";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailAndIsAtivoTrue(username);

        if (usuarioOpt.isEmpty()) {
            throw new UsernameNotFoundException(String.format("usuario: %s não encontrado!", username));
        }

        return new UsuarioDetailsDto(usuarioOpt.get());
    }

    public UsuarioDetailsDto getUsuarioDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UsuarioDetailsDto) authentication.getPrincipal();
    }

    public Usuario getUsuarioFromUsuarioDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioDetailsDto usuarioDetailsDto =  (UsuarioDetailsDto) authentication.getPrincipal();

        Optional<Usuario> usuario = usuarioRepository.findById(usuarioDetailsDto.getId());
        return usuario.get();

    }

    public String generateQRUrl(Usuario user) {
        URLEncoder urlEncoder = new URLEncoder();
        return QR_PREFIX + urlEncoder.encode(String.format(
                        "otpauth://totp/%s:%s?secret=%s&issuer=%s",
                        APP_NAME, user.getEmail(), user.getSecret(), APP_NAME),
                Charset.defaultCharset());
    }

    public String generateSecret() {
        return Base32.random();
    }
}
