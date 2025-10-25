package com.HBA.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



//JWT Service Katmanı
@Component
public class JwtService {

	public static final String SECRET_KEY = "bqXh6z+Ubqo+yUKHa/4ClBuNxnXZSr70AocHyrb4/ts=";
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claimsMap = new HashMap<>(); //Token içerisine istenen herhangi veriyi eklemek için kullanılan hashmap.
		claimsMap.put("role", "ADMIN");
		
		return Jwts.builder()
		.setSubject(userDetails.getUsername()) //Oluşacak token Başlığını kullanıcı adı olarak atadık
		.addClaims(claimsMap) //Hashmap tokene eklendi.
		.setIssuedAt(new Date()) //Oluşacak token oluşturulma tarihini atadık
		.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*2)) //Token geçerlilik süresi olarak 2 saat atandı.
		.signWith(getKey(), SignatureAlgorithm.HS256)
		.compact();
	}
	
	public Object getClaimsByKey(String token, String key) {
		Claims claims = getClaims(token);
		return claims.get(key);
	}
	
	public Claims getClaims(String token) {
		Claims claims = Jwts
				.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token).getBody();
		return claims;
	}
	
	
	
	
	public <T> T exportToken(String token, Function<Claims, T> claimsFunction) {
		Claims claims = getClaims(token); 
		
		return claimsFunction.apply(claims);
	}
	
	public String getUsernameByToken(String token) {
		return exportToken(token, Claims::getSubject);
	}
	
	public boolean isTokenExpired(String token) { //Token süresi dolmuşmu dolmamışmı onu kontrol eden fonksiyon
		Date expiredDate = exportToken(token, Claims::getExpiration); //Tokenin süresini alır
		
		return new Date().before(expiredDate); //Şimdiki zamandan küçükse expiredDate True döner
	}
	
	public Key getKey() {
		byte[] keyBytes =  Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
