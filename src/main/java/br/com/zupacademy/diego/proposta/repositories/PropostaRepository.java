package br.com.zupacademy.diego.proposta.repositories;

import br.com.zupacademy.diego.proposta.models.Proposta;
import br.com.zupacademy.diego.proposta.models.StatusProposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PropostaRepository extends JpaRepository<Proposta, String> {
    List<Proposta> findByStatusAndNumeroCartaoIsNull(StatusProposta statusProposta);
}
