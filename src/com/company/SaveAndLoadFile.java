package com.company;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class SaveAndLoadFile {

        public static List<String> readAllLines(String fileName ){
            List<String> lines = null;
            try{
                lines = Files.readAllLines( Paths.get(fileName));
            } catch(Exception e ){
                //  e.printStackTrace();
                //  System.out.println(e);
                System.out.println("The file/list doesn't exists, try again");
            }
            return lines;
        }

        public static void writeAllLines(ArrayList<String> data, String fileName){
            List<String> lines = data;
            try{
                Path path = Paths.get( "./"+fileName);
                Files.write(path, lines, StandardCharsets.UTF_8);
            } catch( Exception e ){
                //  e.printStackTrace();
                //  System.out.println(e);
                System.out.println("The file/list doesn't exists, try again");
            }
        }

    public static void saveObject(String filename, Object o, StandardOpenOption... option) {
        Path path = Paths.get(filename);
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path, option))) {
            out.writeObject(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object loadObject(String filename) {
        Path path = Paths.get(filename);
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
            return in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    }
