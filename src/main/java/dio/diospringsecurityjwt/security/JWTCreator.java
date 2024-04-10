package dio.diospringsecurityjwt.security;

import io.jsonwebtoken.*;

import java.util.List;
import java.util.stream.Collectors;

public class JWTCreator {

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "Authorities";

    public static String create(String prefix, String key, JWTObject jwtObject){
        String token = Jwts.builder()
                .setSubject(jwtObject.getSubject())
                .setIssuedAt(jwtObject.getIssuedAt())
                .setExpiration(jwtObject.getExpiration())
                .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles()))
                .signWith(SignatureAlgorithm.HS512, key).compact();
        return prefix + " " + token;
    }

    public static JWTObject create(String token, String prefix, String key) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
        token = token.replace(prefix, "");
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        return new JWTObject()
                .setSubject(claims.getSubject())
                .setExpiration(claims.getExpiration())
                .setIssuedAt(claims.getIssuedAt())
                .setRoles((List<String>) claims.get(ROLES_AUTHORITIES));
    }


    private static List<String> checkRoles(List<String> roles) {
        return roles.stream().map(role -> "ROLE_".concat(role.replaceAll("ROLE_", ""))).collect(Collectors.toList());
    }
}
