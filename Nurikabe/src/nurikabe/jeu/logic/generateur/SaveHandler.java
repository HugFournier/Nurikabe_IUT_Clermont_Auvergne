package nurikabe.jeu.logic.generateur;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveHandler {
    
    private static SaveHandler saveHandler = null;
    private final Path path;
    private List<String> files;
    
    public static SaveHandler getSaveHandler( ){
        if (saveHandler == null)
            saveHandler = new SaveHandler();
        return saveHandler;
    }
    
    private SaveHandler(){
        URI uri = null;
        try {
            uri = SaveHandler.class.getProtectionDomain().getCodeSource().getLocation().toURI();
            
        } catch (URISyntaxException e) {
            System.err.println( "Execution path not defined : " + e.getMessage());
        }
        path = Paths.get(uri);
        loadFileLocations();
    }
    
    public void findInPP( ){
        File f = path.toFile().getParentFile().getParentFile();
        findSaveLocations( f);
        saveFiles();
    }

    public void findSaveLocations(){
        File f = path.toFile();
        File p = f.getParentFile();
        while ( p != null && p.exists()){
            f = p;
            p = p.getParentFile();
        }
        findSaveLocations( f);
        saveFiles();
    }

    private void findSaveLocations( File dir){
        if (dir == null || !dir.isDirectory())
            return;
        File sub[] = dir.listFiles();
        if (sub == null)
            return;
        for (File f : sub)
            if (f.isDirectory())
                findSaveLocations( f);
            else{
                String[] arr = f.getAbsolutePath().split("\\.");
                String extention = "." + arr[arr.length-1];
                if (extention.equals( ".nuribin") || extention.equals( ".nuritxt"))
                    addFile( f.getAbsolutePath());
            }
    }
    
    private void loadFileLocations( ){
        files = new ArrayList<String>();
        File f = new File( path.toAbsolutePath() + File.separator + "saves.bin");
        if (!f.exists())
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
                f.setWritable( true);
                f.setReadable( true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        try {
            FileInputStream fi = new FileInputStream( f);
            Scanner sc = new Scanner(fi);
            while ( sc.hasNextLine()){
                String line = sc.nextLine();
                if ((new File( line)).exists())
                    files.add( line);
            }
            sc.close();
            fi.close();
        } catch (Exception e) {
            System.err.println("The saves file could not be found : " + e.getMessage());
        }
        findInPP();
    }
    
    public void saveFiles( ){
        File f = new File( path.toAbsolutePath() + File.separator + "saves.bin");
        if (f.exists())
            f.delete();
        
        if (!f.exists())
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
                f.setWritable( true);
                f.setReadable( true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        try {
            FileOutputStream fo = new FileOutputStream( f);
            PrintStream ps = new PrintStream( fo);
            for (String s : files)
                ps.println( s);
            
            ps.close();
            fo.close();
        } catch (IOException e) {
            System.err.println( "The save paths could not be written to the file : " + e.getMessage());
        }
    }
    
    public String getFile( int index){
        if (index < 0 || index >= files.size())
            return null;
        return files.get( index);
    }
    
    public List<String> getFiles( ){
        return cloneStringList( files);
    }
    
    public int addFile( String filePath){
        if (files.contains( filePath))
            return files.indexOf( filePath);
        files.add( filePath);
        return files.size() -1;
    }
    
    public int nbFiles( ){
        return files.size();
    }
    
    public String removeFile( int index){
        if (index < 0 || index >= files.size())
            return null;
        return files.remove( index);
    }
    
    public boolean removeFile( String filePath){
        return  files.remove( filePath);
    }
    
    public List<String> cloneStringList( List<String> list){
        List<String> clone = new ArrayList<String>();
        for (String s : list)
            clone.add(s);
        return clone;
    }
    
}
