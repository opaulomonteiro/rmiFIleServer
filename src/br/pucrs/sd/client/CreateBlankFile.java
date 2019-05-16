package br.pucrs.sd.client;

import br.pucrs.sd.FSInterface;

import java.rmi.RemoteException;

public class CreateBlankFile implements CommandInterface {
    @Override
    public boolean verify(String command) {
        return command.startsWith("create");
    }

    @Override
    public void execute(String command, FSInterface fsInterface) throws RemoteException {
        String pathToCreate = PathUtils.getPathFrom(command);

        if (fsInterface.create(pathToCreate) == 1) {
            System.out.println("Arquivo criado com sucesso no servidor: " + pathToCreate);
        } else {
            System.out.println("Erro ao tentar criar arquivo no seridor");
        }
    }
}