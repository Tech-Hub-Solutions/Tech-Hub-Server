package br.com.techhub.server.techub.api.infra.config;

import br.com.techhub.server.techub.api.entity.empresa.Empresa;
import br.com.techhub.server.techub.api.entity.freelancer.Freelancer;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public List<Empresa> empresas() {
        return new ArrayList<>();
    }

    @Bean
    public List<Freelancer> freelancers() {
        return new ArrayList<>();
    }
}
