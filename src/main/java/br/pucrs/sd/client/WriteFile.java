package br.pucrs.sd.client;

import br.pucrs.sd.FSInterface;

import java.nio.charset.Charset;
import java.rmi.RemoteException;

public class WriteFile implements CommandInterface {
    @Override
    public boolean verify(String command) {
        return command.startsWith("write");
    }

    @Override
    public void execute(String command, FSInterface fsInterface) throws RemoteException {
        String pathToCreate = PathUtils.getPathFrom(command);
        byte[] content2 = command.split(";")[1].getBytes(Charset.forName("UTF-8"));

        if (fsInterface.write(content2, pathToCreate) == 1) {
            System.out.println("Conteudo adiconado com sucesso no arquivo: " + pathToCreate);
        } else {
            System.out.println("Erro ao tentar adicionar o conteudo no arquivo no servidor");
        }
    }
}