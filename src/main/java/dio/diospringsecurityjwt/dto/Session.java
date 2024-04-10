package dio.diospringsecurityjwt.dto;

public class Session {

    private String login;
    private String token;

    public String getLogin() {
        return login;
    }

    public Session setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getToken() {
        return token;
    }

    public Session setToken(String token) {
        this.token = token;
        return this;
    }
}
