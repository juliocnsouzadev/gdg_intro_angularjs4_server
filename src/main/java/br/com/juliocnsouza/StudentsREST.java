package br.com.juliocnsouza;

import com.google.gson.Gson;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author julio
 */
@Stateless
@LocalBean
@Path( "/students" )
public class StudentsREST {

    @Inject
    private StudentsSingleton studentsSingleton;

    @POST
    @Path( "/save" )
    @Produces( { "application/json" } )
    public Response save( String json ) {
        final StudentsSingleton.Student student = new Gson().fromJson( json ,
                                                                       StudentsSingleton.Student.class );
        if ( student != null && student.isOk() ) {
            studentsSingleton.add( student );
            return Response.ok().build();
        }
        return Response.status( Response.Status.PRECONDITION_FAILED ).build();
    }

    @GET
    @Produces( "application/json" )
    @Path( "/list" )
    public Response getStudents() {
        final List<StudentsSingleton.Student> students = studentsSingleton.getStudents();
        return Response.ok( new Gson().toJson( students ) ).build();
    }

    @DELETE
    @Produces( "application/json" )
    @Path( "/{id}/" )
    public Response delete( @PathParam( "id" ) String id ) {
        studentsSingleton.remove( id );
        return Response.ok().build();
    }

}
