/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thekingdoms;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Michael
 */
public class FileHandler {
    
    public static FileHandler fileHandler = new FileHandler();
    
    static String currentFolder;
    
    static BufferedWriter writer;
    static FileWriter fileWriter;
    static BufferedReader reader;
    static FileReader fileReader;
    static File[] chunks;
    
    public FileHandler(){
        
        createBaseFolders();
        
    }
    
    public static FileHandler getFileHandler(){
        return fileHandler;
    }
    
    public static void setFolder(String tmp){
        currentFolder = "saves"+tmp;
        File tmpFile = new File(currentFolder);
        if(tmpFile.exists()){
            System.out.println("folder already exists: "+tmpFile.getPath());
        }else{
            try{
                System.out.println("folder creation started");
                tmpFile.mkdir();
                System.out.println("folder created");
            }catch(Exception e){
                System.err.println("folder could not be created: "+e.getStackTrace());
            }
        }
    }
    
    public static File getFile(String tmp){
        File tmpFile = new File(currentFolder+tmp+".txt");
        if(tmpFile.exists()){
            System.out.println("file returned: "+tmpFile.getPath());
            return tmpFile;
        }else{
            return null;
        }
    }
    
    public static File createFile(String tmp){
        File tmpFile = new File(currentFolder+tmp+".txt");
        try{
            System.out.println("file creation started");
            tmpFile.createNewFile();
            System.out.println("file created");
        }catch(Exception e){
            System.err.println("file could not be created: "+e.getStackTrace());
        }
        return tmpFile;
    }
    
    public static void createBaseFolders(){
        currentFolder = "saves";
        File tmpFile = new File(currentFolder);
        if(tmpFile.exists()){
            System.out.println("folder already exists: "+tmpFile.getPath());
        }else{
            try{
                System.out.println("folder creation started");
                tmpFile.mkdir();
                System.out.println("folder created");
            }catch(Exception e){
                System.err.println("folder could not be created: "+e.getStackTrace());
            }
        }
    }
    
    public static void writeToFile(String path, String[] tmpStr, boolean keepContents){
        
        File tmpFile = getFile(path);
        try{
            fileWriter = new FileWriter(tmpFile, keepContents);
            writer = new BufferedWriter(fileWriter);
            
            for(int i=0;i<tmpStr.length;i++){
                writer.write(tmpStr[i]);
                writer.newLine();
                writer.flush();
            }
            
            writer.close();
            
        }catch(Exception e){
            System.err.println("writing to file error at file: "+tmpFile.getPath()+" with stack trace error: "+e.getStackTrace());
        }
        
    }
    
    public static String[] readFromFile(String path){
        
        File tmpFile = getFile(path);
        String[] returnString;
        try{
            fileReader = new FileReader(tmpFile);
            reader = new BufferedReader(fileReader);
            String tmpString;
            ArrayList arrayList = new ArrayList();
            while ((tmpString = reader.readLine()) != null){
                arrayList.add(tmpString);
            }
            System.out.println();
            returnString = new String[arrayList.size()];
            arrayList.toArray(returnString);
            
            reader.close();
            
            return returnString;
        }catch(Exception e){
            System.err.println("reading from file error at file: "+path+" with stack trace error: "+e.getLocalizedMessage());
            return null;
        }
    }
    
}
