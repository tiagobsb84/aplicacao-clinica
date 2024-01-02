package clinica.med.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import clinica.med.api.domain.medico.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
