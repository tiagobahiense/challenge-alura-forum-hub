package forum.hub.api.domain.dto;

public record DadosAutenticacao(String login, String senha) {
    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
