package com.demo.base;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.Date;

/**
 * @author TMW
 * @since 2020/2/11 18:18
 */
public class JwtDemo {
    @Test
    public void test01() {
        JwtBuilder jwtBuilder = Jwts.builder().setId("1").setSubject("测试")
                .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString("tmw".getBytes()));
        // eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoi5rWL6K-VIiwiaWF0IjoxNTgxNDE2NzQ4fQ.Hw45M0oVhwAZPZpCzbERuKnmZU0mFL0ZI0ThtfHZND4
        System.out.println(jwtBuilder.compact());
    }


    @Test
    public void test02(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoi5rWL6K-VIiwiaWF0IjoxNTgxNDE2NzQ4fQ.Hw45M0oVhwAZPZpCzbERuKnmZU0mFL0ZI0ThtfHZND4";

        Claims claims = Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString("tmw".getBytes())).parseClaimsJws(token).getBody();
        // claims.setId("i").setSubject("测试").setIssuedAt()
        System.out.println(claims);
    }
}
