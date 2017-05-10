package it.polito.tdp.metrodeparis.model;

public class Linea {
	int id_linea;
    String nome;
    double velocita;
    double intervallo;
    String colore;
	
	public Linea(int id_linea, String nome, double velocita, double intervallo, String colore) {
		super();
		this.id_linea = id_linea;
		this.nome = nome;
		this.velocita = velocita;
		this.intervallo = intervallo;
		this.colore = colore;
	}
	
	
    
    
	public int getId_linea() {
		return id_linea;
	}
	public void setId_linea(int id_linea) {
		this.id_linea = id_linea;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getVelocita() {
		return velocita;
	}
	public void setVelocita(double velocita) {
		this.velocita = velocita;
	}
	public double getIntervallo() {
		return intervallo;
	}
	public void setIntervallo(double intervallo) {
		this.intervallo = intervallo;
	}
	public String getColore() {
		return colore;
	}
	public void setColore(String colore) {
		this.colore = colore;
	}
    
}
