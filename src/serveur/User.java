package serveur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Topic;

@SuppressWarnings("serial")
public class User implements Serializable{
	private String name;
	private String password;
	private Topic topic;
	private List<Topic> abo;
	
	public User(){
		this("anonymous", "admin");
	}
	
	public User(String name){
		this(name, "passw");
	}
	
	public User(String name, String password){
		this.name = name;
		password = "password";
		topic = null;
		abo = new ArrayList<Topic>();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public List<Topic> getAbo() {
		return abo;
	}

	public void setAbo(List<Topic> abo) {
		this.abo = abo;
	}
	
	public void addAbo(Topic t){
		boolean b = true;
		for(Topic top : abo){
			try {
				if (top.getTopicName().equals(t.getTopicName())){
					b = false;
				}
			} catch (JMSException e) {
				b = false;
				e.printStackTrace();
			}
			if (b){
				abo.add(t);
			}
		}
	}
}
