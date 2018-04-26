package clase.recursos.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.naming.NamingContext;

import com.mysql.jdbc.Statement;

import clase.datos.Link;
import clase.datos.Usuario;
import clase.datos.Usuarios;

@Path("/usuario")
public class RecursosUsuarios {

	@Context
	private UriInfo uriInfo;

	private DataSource ds;
	private Connection conn;

	public RecursosUsuarios() {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			NamingContext envCtx = (NamingContext) ctx.lookup("java:comp/env");
			
			ds = (DataSource) envCtx.lookup("jdbc/SocialUPM");

			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Lista de garajes JSON/XML generada con listas en JAXB
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUsuarios1() {
		try {
			String sql = "SELECT * FROM SocialUPM.Usuario ;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Usuarios u = new Usuarios();
			ArrayList<Link> usuarios = u.getUsuarios();
			rs.beforeFirst();
			while (rs.next()) {
				usuarios.add(new Link(uriInfo.getAbsolutePath() + "/" + rs.getInt("idUser"),"self"));
			}
			return Response.status(Response.Status.OK).entity(u).build(); // No se puede devolver el ArrayList (para generar XML)
		} catch (NumberFormatException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudieron convertir los índices a números")
					.build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error de acceso a BBDD").build();
		}
	}
	
	@GET
	@Path("{usuario_id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUsuario2(@PathParam("usuario_id") String id) {
		try {
			int int_id = Integer.parseInt(id);
			String sql = "SELECT * FROM Usuario where idUser=" + int_id + ";";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Usuario usuario =  usuarioFromRS(rs);
//				return Response.status(Response.Status.OK).entity(usuario).build();
				return Response.ok().entity(usuario).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Elemento no encontrado").build();
			}
		} catch (NumberFormatException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No puedo parsear a entero").build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error de acceso a BBDD").build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUsuario(Usuario usuario) {
		System.out.println("dentro1");
		try {
			System.out.println("dentro2");
			String sql = "INSERT INTO SocialUPM.Usuario (idUser, nombre) VALUES (" +usuario.getId() + ", '" + usuario.getnombre() +"' );";
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			int affectedRows = ps.executeUpdate();
			
			// Obtener el ID del elemento recién creado. 
			// Necesita haber indicado Statement.RETURN_GENERATED_KEYS al ejecutar un statement.executeUpdate() o al crear un PreparedStatement
			ResultSet generatedID = ps.getGeneratedKeys();
			if (generatedID.next()) {
				usuario.setId(generatedID.getInt(1));
				String location = uriInfo.getAbsolutePath() + "/" + usuario.getId();
				return Response.status(Response.Status.CREATED).entity(usuario).header("Location", location).header("Content-Location", location).build();
			}
			return Response.status(Response.Status.CREATED).build();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se pudo crear el usuario\n" + e.getStackTrace()).build();
		}
	}
	
	
	private Usuario usuarioFromRS(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario(rs.getString("nombre"));
		usuario.setId(rs.getInt("idUser"));
		return usuario;
	}
}
