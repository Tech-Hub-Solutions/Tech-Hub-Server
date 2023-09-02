package br.com.techhub.server.techub.api.entity.avaliacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Avaliacao {
    private String autor;
    private LocalDate date;
    private int nota;
    private String comentario;
}
