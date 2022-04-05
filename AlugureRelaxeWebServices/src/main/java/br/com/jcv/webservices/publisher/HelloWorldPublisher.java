package br.com.jcv.webservices.publisher;


import javax.xml.ws.Endpoint;

import br.com.jcv.webservices.impl.HelloWorldImpl;
 
//Endpoint publisher - exmplo de publicacao
public class HelloWorldPublisher{
 
	public static void main(String[] args) {
	   Endpoint.publish("http://localhost:9999/ws/hello", new HelloWorldImpl());
    }
 
}