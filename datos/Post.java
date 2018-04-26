package clase.datos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "post")
public class Post {
    private int id;
    private int idPropietario;					//alias del propietario
    private String fecha;						//tiene que se
    private String texto;


    public Post(int idPropietario, String fecha, String texto) {
        super();
        this.id = id;
        this.idPropietario=idPropietario;
        this.fecha=fecha;
        this.texto=texto;
    }
    
    @XmlAttribute(required=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public int getPropietario() {
		return idPropietario;
	}

	public void setPropietario(int idPropietario) {
		this.idPropietario = idPropietario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
