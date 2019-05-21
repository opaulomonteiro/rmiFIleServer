package br.pucrs.sd.server;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class ServerMain {

    public static void main(String[] argv) {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry ready.");
        } catch (RemoteException e) {
            System.out.println("RMI registry already running.");
        }
        try {
            Naming.rebind("Client", new FSImpl());
            System.out.println("FileServer is ready.");
        } catch (Exception e) {
            System.out.println("FileServer failed:");
            e.printStackTrace();
        }
    }
}