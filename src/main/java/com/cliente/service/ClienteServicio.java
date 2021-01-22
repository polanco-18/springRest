package com.cliente.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cliente.interfaces.ClienteInterface;
import com.cliente.modelo.Cliente;
import com.cliente.modelo.DAO.ClienteDAO;

@Service
public class ClienteServicio implements ClienteInterface{

	@Autowired
	ClienteDAO dao;
	@Override
	public List<Map<String, Object>> listar() {		
		return dao.listar();
	}

	@Override
	public int add(Cliente p) {
		return dao.add(p);
	}

	@Override
	public String Calculo() {
		return dao.Calculo();
	}
 

}
