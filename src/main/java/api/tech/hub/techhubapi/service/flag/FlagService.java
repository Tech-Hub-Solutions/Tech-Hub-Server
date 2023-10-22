package api.tech.hub.techhubapi.service.flag;

import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.repository.FlagUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlagService {
    private final FlagUsuarioRepository flagUsuarioRepositoryRepository;
    
}
