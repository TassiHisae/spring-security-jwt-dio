package dio.diospringsecurityjwt.controller;

import dio.diospringsecurityjwt.dto.Login;
import dio.diospringsecurityjwt.dto.Session;
import dio.diospringsecurityjwt.model.User;
import dio.diospringsecurityjwt.repository.UserRepository;
import dio.diospringsecurityjwt.security.JWTCreator;
import dio.diospringsecurityjwt.security.JWTObject;
import dio.diospringsecurityjwt.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;

@RestController
public class LoginController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public Session login(@RequestBody Login login){

        User user = userRepository.findByUsername(login.getUsername());

        if(Objects.nonNull(user)){
            Boolean passwordOk = encoder.matches(login.getPassword(), user.getPassword());

            if (Boolean.FALSE.equals(passwordOk)){
                throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
            }

            //Estamos enviando um objeto Sessão para retornar mais informações do usuário

            JWTObject jwtObject = new JWTObject()
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION))
                    .setRoles(user.getRoles());

            return new Session()
                    .setLogin(user.getUsername())
                    .setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
        }else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}
