package dio.diospringsecurityjwt.security;

import java.util.Date;
import java.util.List;

public class JWTObject {

    private String subject; //nome do usuário
    private Date issuedAt; //data de criação do token
    private Date expiration; //data de expiração do token
    private List<String> roles; //perfis de acesso

    public String getSubject() {
        return subject;
    }

    public JWTObject setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public JWTObject setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
        return this;
    }

    public Date getExpiration() {
        return expiration;
    }

    public JWTObject setExpiration(Date expiration) {
        this.expiration = expiration;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public JWTObject setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }
}
