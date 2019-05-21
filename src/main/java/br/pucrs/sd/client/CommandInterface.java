package br.pucrs.sd.client;

import br.pucrs.sd.FSInterface;

import java.rmi.RemoteException;

public interface CommandInterface {

    boolean verify(String command);

    void execute(String command, FSInterface fsInterface) throws RemoteException;
}