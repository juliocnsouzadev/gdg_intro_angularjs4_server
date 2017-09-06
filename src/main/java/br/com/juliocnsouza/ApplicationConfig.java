package br.com.juliocnsouza;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath( "ws" )
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new java.util.HashSet<>();
        try {
            final Class jacksonProvider
                        = Class.forName( "org.codehaus.jackson.jaxrs.JacksonJsonProvider" );
            resources.add( jacksonProvider );
        }
        catch ( final ClassNotFoundException ex ) {
            Logger.getLogger( this.getClass().getSimpleName() ).log( Level.SEVERE ,
                                                                     ex.getMessage() );
        }
        addRestResourceClasses( resources );
        return resources;
    }

    private void addRestResourceClasses( Set<Class<?>> resources) {
        resources.add( br.com.juliocnsouza.StudentsREST.class );
    }
}
