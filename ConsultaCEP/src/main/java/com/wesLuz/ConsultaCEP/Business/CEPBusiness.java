package com.wesLuz.ConsultaCEP.Business;

import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesLuz.ConsultaCEP.Model.CEP;
import com.wesLuz.ConsultaCEP.Repository.CEPRepository;

@Service
public class CEPBusiness implements ICEPBusiness {

	@Autowired
	private CEPRepository _repository;
	
	@Override
	public String ValidateCEP(String CEP) {
		CEP.replaceAll("[^a-zA-Z0-9]","");
		return CEP;
	}

	@Override
	public CEP InsertAddress(String CEPString) {
		CEP newCEP = new CEP();
		CEPString = ValidateCEP(CEPString);
		if(!Exists(CEPString)) {
			newCEP = ConsultaCEP(CEPString);
			_repository.save(newCEP);
			System.out.println(newCEP.getComplemento());
			return newCEP;		
		}
		return _repository.findByCEP(CEPString);
	}
	
	private CEP ConsultaCEP(String CepString) {
		ObjectMapper mapper = new ObjectMapper();
		String url = "https://viacep.com.br/ws/" + CepString + "/json/";
		try(CloseableHttpClient client = HttpClients.createDefault()) {
			HttpGet request = new HttpGet(url);
			CEP response = client.execute(request, httpResponse-> mapper.readValue(httpResponse.getEntity().getContent(), CEP.class));
			response.setCep(CepString);
			System.out.println(response.getCep());
			return response;
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}

	@Override
	public CEP FindbyLogradouro(String logradouro) {
		return _repository.findByLogradouro(logradouro);
	}

	@Override
	public List<CEP> FindAllByUf(String uf) {
		return _repository.findAllByUf(uf);
	}

	@Override
	public Boolean Exists(String Cep) {
		System.out.println(_repository.findByCEP(Cep));
		if(_repository.findByCEP(Cep)!= null) {
			return true;
		}
		return false;
	}

	@Override
	public Page<CEP> pageCEPbyUF(String uf,int pageNum,int size) {
		Pageable pageable = PageRequest.of(pageNum,size );
		Page<CEP> CEPpage = _repository.pageCep(pageable,uf);
		return CEPpage;
	}

}
