package clinica.med.api.domain.medico;

import clinica.med.api.domain.endereco.EnderecoDto;

public record DadosCadastroMedico(String nome, String email, String telefone, String crm, Especialidade especialidade, EnderecoDto endereco) {

}
