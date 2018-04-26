package clase.datos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "posts")
public class Posts {
	private ArrayList<Link> posts;

	public Posts() {
		this.posts= new ArrayList<Link>();
	}

	@XmlElement(name="post")
	public ArrayList<Link> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Link> posts) {
		this.posts = posts;
	}
}
