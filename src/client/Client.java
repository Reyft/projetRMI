package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;

import serveur.Serveur;

public class Client  {
	private Topic monTopic;
	private TopicSession maSession;
	private TopicConnection maConnexion;
	
	public Topic getMonTopic() {
		return monTopic;
	}

	public void setMonTopic(Topic monTopic) {
		this.monTopic = monTopic;
	}

	public TopicSession getMaSession() {
		return maSession;
	}

	public void setMaSession(TopicSession maSession) {
		this.maSession = maSession;
	}

	public Client(){
		monTopic = null;
	}
	
	public static void main(String[] args) {
		Serveur serv;
		Client c = new Client();
		try {
			serv = (Serveur) Naming.lookup("rmi://localhost/MiniTwitter");			
			c.setMaSession(serv.getConnect().createTopicSession(false,Session.AUTO_ACKNOWLEDGE));
			System.out.println("succes");
		} catch (MalformedURLException|RemoteException|NotBoundException|JMSException e) {
			e.printStackTrace();
		}	
	}
}