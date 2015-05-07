package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import serveur.Serveur;

public class Admin {
	public static void main(String[] args) {
		Serveur serv;
		try {
			serv = (Serveur) Naming.lookup("rmi://localhost/MiniTwitter");
			serv.initialiser();
			System.out.println("succes");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
