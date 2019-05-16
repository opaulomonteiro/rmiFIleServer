package br.pucrs.sd.client;

public class PathUtils {

    public static String getPathFrom(String command) {
        return command.split(" ")[1];
    }
}