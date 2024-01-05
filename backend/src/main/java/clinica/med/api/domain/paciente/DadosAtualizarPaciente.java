package clinica.med.api.domain.paciente;

import clinica.med.api.domain.endereco.EnderecoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarPaciente(
		@NotNull
		Long id, 
		String nome,
		String telefone,
		@NotNull @Valid
		EnderecoDto endereco) {

}
