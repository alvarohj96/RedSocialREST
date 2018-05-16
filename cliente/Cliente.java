package clase.cliente;
import clase.datos.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class Cliente {

	public static ArrayList<String> getUris(String str) {
		ArrayList<String> res = new ArrayList<String>();
		String div[] = str.split("http://localhost:8080/SOS/api/");
		for(int i = 1; i<div.length;i++) { //me salto el 1er elemento
			res.add(div[i].split("/>")[0].replace("\"", ""));
		}
		
		return res;
	}
	
	
    public static void main(String[] args) throws Exception{

        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target("http://localhost:8080/SOS/api/");
        
        //Envío de un post
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaActual = now.format(formatter);
        Post post = new Post(0,"Pero bueno folagor",fechaActual,1);//id del propietario falta
        Response response = target.path("/posts")
                .request()
                .post(Entity.json(post));  // Establece Content Types
        System.out.println("Estado: " + response.getStatus());
        response.close();
        

//        //Obtener mis estados id
//        
//        Response response= target.path("/getpostsid/" + 1)
//                .request()
//                .accept(MediaType.APPLICATION_XML)
//                .get(Response.class);
//        System.out.println("Estado: " + response.getStatus());
//        String valor = response.readEntity(String.class);
//        System.out.println("Entidad: " + valor);
//        
//        ArrayList <String> urisPosts = getUris(valor);
//        
//        
//        
////        //Obtener mis estados por contenido
//        System.out.println("Entidad:");
//        for (int i = 0; i<urisPosts.size();i++) {
//        	String path = urisPosts.get(i);
//        	
//	        Response res = (target.path(path.substring(1, path.length()-1))
//	                .request()
//	                .accept(MediaType.APPLICATION_XML)
//	                .get(Response.class));
//	        System.out.println("Estado: " + res.getStatus());
//	        System.out.println(res.readEntity(Post.class).getContenido());
//        }
        
        //Borrar un estado
//        Response response = target.path("3")
//              .request()
//              .delete();  // Establece Content Types
//      response.close();
//      System.out.println("Estado: " + response.getStatus());
        //Consultar numero estados publicados por mi en un periodo
//        Response response= target.path("count/" + 1)
//        	  .queryParam("fechaIni", "2018-05-14 09:00:00")
//        	  .queryParam("fechaFin", "2018-05-14 18:00:00")
//              .request()
//              .accept(MediaType.APPLICATION_XML)
//              .get(Response.class);
//        System.out.println("Estado: " + response.getStatus());
//        String valor = response.readEntity(String.class);
//        System.out.println("Entidad: " + valor);
        
        //Obtener lista de los ultimos estados de nuestros amigos
     
//        Response response= target.path("byFriendsAndDate/" + 1)
//        		.request()
//        		.accept(MediaType.APPLICATION_XML)
//        		.get(Response.class);
//        
//        System.out.println("Estado: " + response.getStatus());
//        String valor = response.readEntity(String.class);
//        System.out.println("Entidad: " + valor);
//        
        //Obtener lista de posts de mis amigos con determinado texto
        
//        Response response= target.path("bycontent/" + 1)
//        		.queryParam("contenido", "post")
//				.request()
//				.accept(MediaType.APPLICATION_XML)
//				.get(Response.class);
//		
//		System.out.println("Estado: " + response.getStatus());
//		String valor = response.readEntity(String.class);
//		System.out.println("Entidad: " + valor);
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/SocialUPM/api/posts").build();
    }
    
}
