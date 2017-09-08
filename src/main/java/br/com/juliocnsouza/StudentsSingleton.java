package br.com.juliocnsouza;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 *
 * @author julio
 */
@Singleton
public class StudentsSingleton {

    private List<Student> students;

    @PostConstruct
    public void init() {
        students = new ArrayList<>();
        addDefaul();
    }

    private void addDefaul() {

        Student s = new Student();
        s.name = "Julio CN Souza";
        s.ra = getRA();
        students.add( s );
    }

    private String getRA() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder( r.nextInt( 10 ) );
        while ( sb.length() < 11 ) {
            sb.append( r.nextInt( 10 ) );
        }
        return sb.toString();
    }

    public void add( Student s ) {
        if ( students.contains( s ) ) {
            students.remove( s );
        }
        students.add( s );
    }

    public void remove( String ra ) {
        Student r = new Student();
        r.ra = ra;
        r.name = "Ze";
        students.remove( r );
        if ( students.isEmpty() ) {
            addDefaul();
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public static class Student {

        String ra, name;

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 47 * hash + Objects.hashCode( this.ra );
            return hash;
        }

        @Override
        public boolean equals( Object obj ) {
            if ( this == obj ) {
                return true;
            }
            if ( obj == null ) {
                return false;
            }
            if ( getClass() != obj.getClass() ) {
                return false;
            }
            final Student other = ( Student ) obj;
            return Objects.equals( this.ra , other.ra );
        }

        public boolean isOk() {
            return this.ra != null && this.name != null;
        }

    }
}
