package br.com.zupacademy.diego.proposta.repositories;

import br.com.zupacademy.diego.proposta.models.BloqueioCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloqueioCartaoRepository extends JpaRepository<BloqueioCartao, String> {
}
