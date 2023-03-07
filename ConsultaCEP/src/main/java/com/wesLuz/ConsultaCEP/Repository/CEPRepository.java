package com.wesLuz.ConsultaCEP.Repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wesLuz.ConsultaCEP.Model.CEP;


public interface CEPRepository  extends JpaRepository<CEP, Integer>{

	@Query("SELECT c FROM CEP c WHERE c.cep = ?1")
	CEP findByCEP(String cep);
	
	@Query("Select c FROM CEP c WHERE c.logradouro LIKE %:logradouro%")
	CEP findByLogradouro(String logradouro);
	
	@Query("Select c from CEP c where c.uf = ?1")
	List<CEP> findAllByUf(String uf);
	
	@Query("Select c from CEP c where c.uf = ?1")
	Page<CEP> pageCep(Pageable pagable,String uf);
}
