package serveur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

@SuppressWarnings("serial")
public class ServeurTwitter extends UnicastRemoteObject implements Serveur{
	private TopicConnection connect;
	private TopicConnectionFactory factory;
	private List<User> users;
	
	public ServeurTwitter() throws RemoteException {
		super();
		connect = null;
		factory = null;
		users = new ArrayList<User>();
	}
	
	@Override
	public void initialiser(){
		try{	
            factory = new ActiveMQConnectionFactory("user", "password", "tcp://localhost:6161");
            connect = (TopicConnection) factory.createConnection("user", "password");
            //connect.start(); // on peut activer la connection. 
        } catch (Exception jmse){
            jmse.printStackTrace();
        }
	}
	
	@Override
	public Topic lancerTopic(TopicSession session, String name){		
		try {			
			Topic top = session.createTopic(name);
			return top;
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void créerCompte(String name, String password){
//		User u = new User(name, password);
//		u.setTopic(lancerTopic(name));
	}
	
	@Override
	public TopicPublisher createPublisher(TopicSession session, Topic t){
		try {
			return session.createPublisher(t);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public TopicSubscriber createSubscriber(TopicSession session, Topic t){
		try {
			return session.createSubscriber(t);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void sendMessage(TopicPublisher tp, Message m){
		TopicPublisher p = tp;
		try {
			p.send(m);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Message receiveMessage(TopicSubscriber ts){
		TopicSubscriber s = ts;
		Message m;
		try {
			m = s.receive();
			return m;
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	@Override
	public Topic Connection(String name, String password){
		for (User u : users){
			if (u.getName().equals(name) && u.getPassword().equals(password)){
				return u.getTopic();
			}
		}
		return null;
	}
	
	public static void main(String [] args) {
		try {
			ServeurTwitter st = new ServeurTwitter();
			String name = "MiniTwitter";
			Naming.rebind(name, st);
			System.out.println("ComputeEngine bound");
		} catch (RemoteException|MalformedURLException e) {
			e.printStackTrace();
		}		
	}

	public TopicConnection getConnect() {
		return connect;
	}
}