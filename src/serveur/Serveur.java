package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

public interface Serveur extends Remote{
	public void initialiser() throws RemoteException;
	public Topic lancerTopic(TopicSession session, String name) throws RemoteException;
	public TopicPublisher createPublisher(TopicSession session, Topic t) throws RemoteException;
	public TopicSubscriber createSubscriber(TopicSession session, Topic t) throws RemoteException;
	public void sendMessage(TopicPublisher tp, Message m) throws RemoteException;
	public Message receiveMessage(TopicSubscriber ts) throws RemoteException;
	public Topic Connection(String name, String password) throws RemoteException;
	public void créerCompte(String name, String password) throws RemoteException;
	public TopicConnection getConnect() throws RemoteException;
}