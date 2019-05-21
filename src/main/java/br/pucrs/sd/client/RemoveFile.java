package br.pucrs.sd.client;

import br.pucrs.sd.FSInterface;

import java.rmi.RemoteException;

public class RemoveFile implements CommandInterface {
    @Override
    public boolean verify(String command) {
        return command.startsWith("unlink");
    }

    @Override
    public void execute(String command, FSInterface fsInterface) throws RemoteException {
        String pathToDelete = PathUtils.getPathFrom(command);
        if (fsInterface.unlink(pathToDelete) == 1) {
            System.out.println("Arquivo removido com sucesso no servidor: " + pathToDelete);
        } else {
            System.out.println("Erro ao tentar remover arquivo no seridor");
        }
    }
}