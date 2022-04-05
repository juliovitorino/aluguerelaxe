package br.com.jcv.webservices.impl;

import java.util.ArrayList;

import javax.jws.WebService;

import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.webservices.interfaces.HelloWorld;

import com.google.gson.Gson;

//Service Implementation
@WebService(endpointInterface = "br.com.jcv.webservices.interfaces.HelloWorld")
public class HelloWorldImpl implements HelloWorld{

	public String getHelloWorldAsString(String name) {
		return "Hello World JAX-WS " + name;
	}

	public int getSoma(int a, int b) {
		return a + b;
	}

	public String getCliente(int idCliente) {
		ClienteDTO cdto = new ClienteDTO();
		cdto.id = idCliente;
		cdto.nome = "JULIO CESAR VITORINO";
		cdto.email = "julio.vitorino@ig.com.br";
		cdto.telefones = new ArrayList<TelefoneDTO>();
		
		TelefoneDTO tel1 = new TelefoneDTO();
		tel1.ddd = "24";
		tel1.telefone = "3344-3110";
		
		TelefoneDTO tel2 = new TelefoneDTO();
		tel2.ddd = "24";
		tel2.telefone = "9-9834-0040";
		
		cdto.telefones.add(tel1);
		cdto.telefones.add(tel2);
		
		 // Serializa objeto no formato JSON para enviar cliente
        String jsoncdto =  new Gson().toJson( cdto );
        return jsoncdto;
	}

}