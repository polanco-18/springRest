package com.cliente.modelo.DAO;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.json.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cliente.interfaces.ClienteInterface;
import com.cliente.modelo.Cliente;

@Repository
public class ClienteDAO implements ClienteInterface{

	@Autowired
	JdbcTemplate template;
	
	@Override
	public List<Map<String, Object>> listar() {
		List<Map<String,Object>>lista=template.queryForList("SELECT `id`, `nombre`, `apellido`, `fecha_nacimiento`, `creado`, TIMESTAMPDIFF(YEAR,fecha_nacimiento,CURDATE()) AS edad FROM `clientes`");
		return lista;
	}

	@Override
	public int add(Cliente p) {
		String sql="INSERT INTO clientes (nombre, apellido, fecha_nacimiento) VALUES ('"+p.getNombre()+"','"+p.getApellido()+"','"+p.getFechaNacimiento()+"')";
		return template.update(sql);
	}

	@Override
	public String Calculo() {
		
		List<Map<String,Object>>lista=template.queryForList("select fecha_nacimiento from clientes");
		
		double ProEdad=0;
		double SumEdad=0;
		double Desvia=0;
		
		for (Map<String, Object> map : lista) {
		    for (Map.Entry<String, Object> entry : map.entrySet()) {
		    	//optencion de fecha de nacimiento 
		        Object value = entry.getValue();// fecha de nacimiento
		        //calculo de fecha de nacimiento
		        String FechaNacimiento = String.valueOf(value);//fecha nacimiento convertido
		        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        LocalDate fechaNac = LocalDate.parse(FechaNacimiento, fmt);
		        LocalDate ahora = LocalDate.now(); 
		        Period periodo = Period.between(fechaNac, ahora); 
		        int edad = (Integer) periodo.getYears();//edad de la persona
		        SumEdad=edad+SumEdad; 
		    } 
		} 

        ProEdad=SumEdad/lista.size(); //promedio

		for (Map<String, Object> map : lista) {
		    for (Map.Entry<String, Object> entry : map.entrySet()) {
		    	//optencion de fecha de nacimiento 
		        Object value = entry.getValue();// fecha de nacimiento
		        //calculo de fecha de nacimiento
		        String FechaNacimiento = String.valueOf(value);//fecha nacimiento convertido
		        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        LocalDate fechaNac = LocalDate.parse(FechaNacimiento, fmt);
		        LocalDate ahora = LocalDate.now(); 
		        Period periodo = Period.between(fechaNac, ahora); 
		        int edad = (Integer) periodo.getYears();//edad de la persona
		        
		        Desvia=((edad-ProEdad)*(edad-ProEdad))+Desvia; 
		    } 
		}
		double DesviaTotal=Math.sqrt(Desvia/(lista.size()-1));//desviacion 
		
		JSONObject myObject = new JSONObject();

        // Cadenas de texto básicas
        myObject.put("Promedio", ProEdad);
        myObject.put("Desviacion", DesviaTotal);
		
        String json = myObject.toString();
        
		return json;
	} 

}
