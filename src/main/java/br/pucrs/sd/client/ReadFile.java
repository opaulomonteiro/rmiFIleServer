package br.pucrs.sd.client;

import br.pucrs.sd.FSInterface;

import java.rmi.RemoteException;

public class ReadFile implements CommandInterface {

    @Override
    public boolean verify(String command) {
        return command.startsWith("read");
    }

    @Override
    public void execute(String command, FSInterface fsInterface) throws RemoteException {
        String filePath = PathUtils.getPathFrom(command);
        String contentFile = new String(fsInterface.read(filePath));
        System.out.println("Conteudo do arquivo: " + contentFile);
    }
}