package br.com.zupacademy.diego.proposta.repositories;

import br.com.zupacademy.diego.proposta.models.Biometria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometriaRepository extends JpaRepository<Biometria, String> {
}
