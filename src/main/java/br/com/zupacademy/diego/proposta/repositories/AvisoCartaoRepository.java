package br.com.zupacademy.diego.proposta.repositories;

import br.com.zupacademy.diego.proposta.models.AvisoCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoCartaoRepository extends JpaRepository<AvisoCartao, String> {
}
