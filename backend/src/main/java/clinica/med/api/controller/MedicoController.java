package clinica.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clinica.med.api.domain.medico.DadosAtualizacaoMedico;
import clinica.med.api.domain.medico.DadosCadastroMedico;
import clinica.med.api.domain.medico.DadosListagemMedico;
import clinica.med.api.domain.medico.Medico;
import clinica.med.api.repository.MedicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroMedico dados) {
		var medico = new Medico(dados);
		repository.save(medico);
		return ResponseEntity.ok(medico);
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> lista(@PageableDefault(sort = {"nome"}, size = 10) Pageable page) {
		var lista = repository.findAll(page).map(DadosListagemMedico::new);
		return ResponseEntity.ok(lista);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody DadosAtualizacaoMedico dados) {
		var idMedico = repository.getReferenceById(dados.id());
		idMedico.atualizarInformacao(dados);
		return ResponseEntity.ok().build();
	}
}
