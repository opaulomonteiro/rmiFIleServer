package br.pucrs.sd.server;

import br.pucrs.sd.FSInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class FSImpl extends UnicastRemoteObject implements FSInterface {

    private static final long serialVersionUID = 1L;
    private static final String BASEPATH = "/home/11105929/DriveH";

    public FSImpl() throws RemoteException {
    }

    @Override
    public String[] ls(String path) throws RemoteException {
        String[] listaString = null;
        System.out.println("Listando arquivos para pasta: " + path);
        try {
            File directory = new File(path);

            File[] fList = directory.listFiles();
            listaString = new String[fList.length];
            for (int i = 0; i < fList.length; i++) {
                listaString[i] = fList[i].getName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaString;
    }

    @Override
    public int mkdir(String path) throws RemoteException {
        try {
            Path newPath = Paths.get(BASEPATH + path);
            System.out.println("PATH :" + path);
            System.out.println("CRIANDO DIRETORIO");
            if (!Files.exists(newPath)) {

                Files.createDirectory(newPath);
                System.out.println("Directory created");
                return 1;
            } else {
                System.out.println("Directory already exists");
                return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int create(String path) throws RemoteException {
        PrintWriter writer;
        try {
            writer = new PrintWriter(BASEPATH + path, "UTF-8");
            writer.close();
            return 1;
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public int unlink(String path) throws RemoteException {
        try {
            File file = new File(BASEPATH + path);
            Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int write(byte[] data, String path) throws RemoteException {
        try {
            Files.write(Paths.get(BASEPATH + path), data, StandardOpenOption.APPEND);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public byte[] read(String path) throws RemoteException {
        FileReader fr;
        try {
            fr = new FileReader(BASEPATH + path);
            int i;
            while ((i = fr.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}