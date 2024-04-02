package utils;

import java.nio.file.Paths;

public class Utilities {

    public static String getResourcePath(String resource){
        return Paths.get(System.getProperty("user.dir"),"src","test","resources",resource).toString();
    }

}
