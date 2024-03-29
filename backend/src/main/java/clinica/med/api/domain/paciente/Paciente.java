package clinica.med.api.domain.paciente;

import clinica.med.api.domain.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Paciente {

	public Paciente(DadosCadastroPaciente dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.cpf = dados.cpf();
		this.endereco = new Endereco(dados.endereco());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String cpf;
	private Boolean ativo = true;
	
	@Embedded
	private Endereco endereco;

	public void atualizarInformacao(DadosAtualizarPaciente paciente) {
		if(paciente.nome() != null) {
			this.nome = paciente.nome();
		}
		if(paciente.telefone() != null) {
			this.telefone = paciente.telefone();
		}
		if(paciente.endereco() != null) {
			this.endereco = new Endereco(paciente.endereco());
		}
	}

	public void excluir() {
		this.ativo = false;
		
	}
}
