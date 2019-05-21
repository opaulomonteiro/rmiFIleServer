package br.pucrs.sd.client;

import br.pucrs.sd.FSInterface;

import java.rmi.RemoteException;
import java.util.Arrays;

public class ListFiles implements CommandInterface {

    @Override
    public boolean verify(String command) {
        return command.startsWith("ls");
    }

    @Override
    public void execute(String command, FSInterface fsInterface) throws RemoteException {
        String remotePathDirectory = PathUtils.getPathFrom(command);
        String[] filesFromServer = fsInterface.ls(remotePathDirectory);
        Arrays.asList(filesFromServer).forEach((arquivo) -> System.out.println("# " + arquivo));
    }
}