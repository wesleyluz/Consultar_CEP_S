package com.wesLuz.ConsultaCEP.Business;
import java.util.List;

import org.springframework.data.domain.Page;

import com.wesLuz.ConsultaCEP.Model.CEP;

public interface ICEPBusiness {
	public String ValidateCEP(String CEP);
	public CEP InsertAddress(String CEPString);
	public CEP FindbyLogradouro(String logradouro);
	public List<CEP> FindAllByUf(String uf);
	public Boolean Exists(String Cep);
	
	public Page<CEP>pageCEPbyUF(String uf,int pageNum,int size);
}
