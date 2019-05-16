package br.pucrs.sd.client;

import br.pucrs.sd.FSInterface;

import java.rmi.RemoteException;

public class CreateDirectory implements CommandInterface {
    @Override
    public boolean verify(String command) {
        return command.startsWith("mkdir");
    }

    @Override
    public void execute(String command, FSInterface fsInterface) throws RemoteException {
        String pathToCreate = PathUtils.getPathFrom(command);

        if (fsInterface.mkdir(pathToCreate) == 1) {
            System.out.println("Diretorio Criado com Sucesso no Servidor: " + pathToCreate);
        } else {
            System.out.println("Erro ao tentar criar diretorio no seridor");
        }
    }
}