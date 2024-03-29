package clinica.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clinica.med.api.domain.usuario.DadosAutenticacao;
import clinica.med.api.domain.usuario.Usuario;
import clinica.med.api.infra.security.DadosTokenJWT;
import clinica.med.api.service.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
	     var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
	     var authentication = manager.authenticate(token);

	     var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
	     
	     return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));       
	}
}
