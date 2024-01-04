package clinica.med.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import clinica.med.api.domain.paciente.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
