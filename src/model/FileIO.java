package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class FileIO {
    private static final String SEP = File.separator;
    private final String file_name = "src" + SEP + "students.txt";

    public void writeObject(final Map<Integer, Student> obj) throws IOException{
        try (
            final OutputStream file = new FileOutputStream ( this.file_name );
            final OutputStream bstream = new BufferedOutputStream ( file );
            final ObjectOutputStream ostream = new ObjectOutputStream ( bstream ) ;
        ){   
             ostream.writeObject(obj);
        } catch (InvalidClassException e) {
            System.out.println("PROBLEMI CLASSE INVALIDA");
        } catch (NotSerializableException e) {
            System.out.println("PROBLEMI CLASSE NON SERIALIZZABILE");
        } catch (IOException e ){
            System.out.println("PROBLEMI IO");
        }
    }

    //the objects are surely Map<Integer, Student> are designed in that way
    @SuppressWarnings("unchecked") 
    public Map<Integer, Student> readObjects() throws IOException, ClassNotFoundException{
         Map<Integer, Student> obj = new HashMap<>();
        try (
            final InputStream file2 = new FileInputStream (this.file_name);
            final InputStream bstream2 = new BufferedInputStream ( file2 );
            final ObjectInputStream ostream2 = new ObjectInputStream ( bstream2 ) ;
        ){
           obj = (Map<Integer, Student>) ostream2.readObject();
           return obj;
        } catch (Exception ex) {
            //System.out.println("PROBLEMI IN LETTURA");
            return null;
        }
        
    }
}
