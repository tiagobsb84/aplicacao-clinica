package clinica.med.api.domain.medico;

import clinica.med.api.domain.endereco.Endereco;

public record DadosDetalhadoMedico(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {

	public DadosDetalhadoMedico(Medico dados) {
		this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getTelefone(), dados.getCrm(), dados.getEspecialidade(), dados.getEndereco());
	}
}
