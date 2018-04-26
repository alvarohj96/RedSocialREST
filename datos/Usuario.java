package clase.datos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "usuario")
public class Usuario {
    private int id;
    private String nombre;
    private ArrayList<Usuario> amigos;
    private ArrayList<Post> posts;

    public Usuario() {
        this.amigos = new ArrayList<Usuario>();
        this.posts = new ArrayList<Post>();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getnombre() {
		return nombre;
	}

	public void setnombre(String nombre) {
		this.nombre = nombre;
	}
	
	@XmlElementWrapper(name="usuarios")
	@XmlElement(name="usuario")
	public ArrayList<Usuario> getAmigos() {
		return amigos;
	}
	
	public void setAmigos(ArrayList<Usuario> amigos) {
		this.amigos = amigos;
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}
	
	@XmlElementWrapper(name="posts")
	@XmlElement(name="post")
	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}
	public Usuario(String nombre) {
		super();
	    this.id = id;
	    this.nombre=nombre;
	    this.amigos = new ArrayList<>();
	    this.posts = new ArrayList<>();
	}
}
