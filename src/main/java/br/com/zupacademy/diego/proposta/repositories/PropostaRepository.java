package br.com.zupacademy.diego.proposta.repositories;

import br.com.zupacademy.diego.proposta.models.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PropostaRepository extends JpaRepository<Proposta, String> {
}
