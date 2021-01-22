package com.cliente.interfaces;

import java.util.List;
import java.util.Map;

import com.cliente.modelo.Cliente;

public interface ClienteInterface {
	public List<Map<String,Object>>listar();
	public int add (Cliente p);
	public String Calculo(); 
}
