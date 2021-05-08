package it.unisa.model;

import java.io.Serializable;

public class DisponibileBean implements Serializable{

	/**
	 * è il bean della tabella Disponibile del DB
	 */
	private static final long serialVersionUID = 1L;
	
	private int videogioco;
	private int console;
	
	public DisponibileBean()
	{
		videogioco=0;
		console=0;
	}

	public int getVideogioco() {
		return videogioco;
	}

	public void setVideogioco(int videogioco) {
		this.videogioco = videogioco;
	}

	public int getConsole() {
		return console;
	}

	public void setConsole(int console) {
		this.console = console;
	}
	
	
}
