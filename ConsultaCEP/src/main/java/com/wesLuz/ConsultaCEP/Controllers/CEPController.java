package com.wesLuz.ConsultaCEP.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wesLuz.ConsultaCEP.Business.CEPBusiness;
import com.wesLuz.ConsultaCEP.Model.CEP;

@RestController
@RequestMapping(value = "/")
public class CEPController {

	@Autowired
	private CEPBusiness _cepBusiness;

	@GetMapping(value = "/cep/{cep}")
	@ResponseBody
	public CEP InsertCEP(@PathVariable String cep) {
		return _cepBusiness.InsertAddress(cep);
	}

	@GetMapping(value = "/logradouro/{logradouro}")
	@ResponseBody
	public CEP findByLogradouro(@RequestParam(value = "logradouro") String logradouro) {
		return _cepBusiness.FindbyLogradouro(logradouro);
	}

	@GetMapping(value = "/uf/")
	@ResponseBody
	public List<CEP> findByUF(@RequestParam(value = "uf") String uf) {
		return _cepBusiness.FindAllByUf(uf);
	}

	@GetMapping(value = "/ufPaged/")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> findByUfPaged(@RequestParam(value = "uf") String uf,
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		try {
			Page<CEP> cepsByUF = _cepBusiness.pageCEPbyUF(uf, pageNum, size);
			List<CEP> cepsList = cepsByUF.getContent();
			Map<String,Object> response = new HashMap<>();
			response.put(uf.toUpperCase(),cepsList);
			response.put("currentPage",cepsByUF.getNumber());
			response.put("totalItens",cepsByUF.getTotalElements());
			response.put("totalPages",cepsByUF.getTotalPages());
			
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
}
