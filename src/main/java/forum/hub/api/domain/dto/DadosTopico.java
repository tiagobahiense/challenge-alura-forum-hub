package forum.hub.api.domain.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosTopico(
        @NotBlank
        String titulo,

        @NotBlank
        String mensagem,

        LocalDate data_criacao,

        @NotBlank
        String estadoDoTopico,

        @NotBlank
        String nome_autor,

        @NotBlank
        @Email
        String email_autor,

        @NotBlank
        String curso) {
        public String getTitulo() {
            return "";
        }

        public String getNomeAutor() {
            return "";
        }
}


