import java.io.*;
import java.util.ArrayList;

public class QuerySet implements Serializable{
    private ArrayList<Query> queries = new ArrayList<>();

    public boolean hasNext(){
        return queries.size() > 0;
    }

    public Query getNext(){
        if(queries.size() > 0) {
            return queries.remove(0);
        } else {
            return null;
        }
    }

    public void addQuery(Query q){
        queries.add(q);
    }

    public void removeQuery(Query q){
        queries.remove(q);
    }

    public void clear(){
        queries = new ArrayList<>();
    }

    public void write(String path){
        try{
            FileOutputStream fout = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error saving QuerySet.");
            e.printStackTrace();
        }
    }

    public static QuerySet read(String path){
        try{
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fin);
            return (QuerySet) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error reading QuerySet.");
            e.printStackTrace();
        }
        return null;
    }
}