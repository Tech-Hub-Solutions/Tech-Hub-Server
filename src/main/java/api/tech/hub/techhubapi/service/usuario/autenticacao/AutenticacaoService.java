package api.tech.hub.techhubapi.service.usuario.autenticacao;

import api.tech.hub.techhubapi.entity.usuario.Usuario;
import api.tech.hub.techhubapi.repository.UsuarioRepository;
import api.tech.hub.techhubapi.service.usuario.dto.UsuarioDetailsDto;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Optional;
import org.springframework.web.server.ResponseStatusException;

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
            throw new UsernameNotFoundException(
                  String.format("usuario: %s não encontrado!", username));
        }

        return new UsuarioDetailsDto(usuarioOpt.get());
    }

    public UsuarioDetailsDto getUsuarioDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UsuarioDetailsDto) authentication.getPrincipal();
    }

    public Usuario getUsuarioFromUsuarioDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioDetailsDto usuarioDetailsDto = (UsuarioDetailsDto) authentication.getPrincipal();

        Optional<Usuario> usuario = usuarioRepository.findById(usuarioDetailsDto.getId());
        return usuario.get();

    }

    public String generateQRUrl(Usuario user) {
        String googleAuthenticatorBarCode = getGoogleAuthenticatorBarCode(user);
        try {
            BufferedImage qrCodeImage =  generateQRCodeImage(googleAuthenticatorBarCode);
            byte[] qrCodeImageBytes = convertBufferedImageToByteArray(qrCodeImage);
            String base64String = Base64.getEncoder().encodeToString(qrCodeImageBytes);

            return "data:image/png;base64," + base64String;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                  "Erro ao gerar QR Code");

        }
    }

    public static byte[] convertBufferedImageToByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos); // Replace "png" with desired format (jpg, gif, etc.)
        byte[] bytes = baos.toByteArray();
        baos.close();
        return bytes;
    }

    private String getGoogleAuthenticatorBarCode(Usuario user) {
        return String.format(
                    "otpauth://totp/%s:%s?secret=%s&issuer=%s",
                    APP_NAME, user.getEmail(), user.getSecret(), APP_NAME);
    }

    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
              barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public String generateSecret() {
        return Base32.random();
    }
}
