package com.cliente.controlador;
 
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cliente.modelo.Cliente;
import com.cliente.service.ClienteServicio;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequestMapping(path="/clientes")
public class ClienteControler {

	@Autowired
	ClienteServicio service;
	
	@GetMapping("/listclientes")
	public List<Map<String,Object>>listar(){
		return service.listar();
	}
	@PostMapping("/creacliente")
	public String add(@RequestBody Cliente p) {
		int r=service.add(p);
		if(r==0) {
			return "no se puedo registrar";	
		}else {
			return "Registrado";
		}
	}
	@GetMapping("/kpideclientes")
	public String Calculo() {
		return service.Calculo(); 
	}
	
}
