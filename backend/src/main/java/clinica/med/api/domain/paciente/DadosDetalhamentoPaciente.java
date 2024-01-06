package clinica.med.api.domain.paciente;

import clinica.med.api.domain.endereco.Endereco;

public record DadosDetalhamentoPaciente(Long id, String nome, String email, String telefone, String cpf, Endereco endereco) {

	public DadosDetalhamentoPaciente(Paciente dados) {
		this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getTelefone(), dados.getCpf(), dados.getEndereco());
	}
}
