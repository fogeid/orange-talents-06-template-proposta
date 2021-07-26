package br.com.zupacademy.diego.proposta.repositories;

import br.com.zupacademy.diego.proposta.models.CarteiraCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraCartaoRepository extends JpaRepository<CarteiraCartao, String> {
}
