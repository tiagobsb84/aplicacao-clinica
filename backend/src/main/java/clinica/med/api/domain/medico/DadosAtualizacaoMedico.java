package clinica.med.api.domain.medico;

import clinica.med.api.domain.endereco.EnderecoDto;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
		@NotNull
		Long id, 
		String nome, 
		String telefone, 
		EnderecoDto endereco) {

}
