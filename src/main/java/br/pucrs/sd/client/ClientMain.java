package br.pucrs.sd.client;

import br.pucrs.sd.FSInterface;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Client <machine>");
            System.exit(1);
        }

        ArrayList<CommandInterface> commands = new ArrayList<>(
                Arrays.asList(
                        new AppendFile(),
                        new CreateBlankFile(),
                        new CreateDirectory(),
                        new ListFiles(),
                        new ReadFile(),
                        new RemoveFile())
        );


        FSInterface fs = registraServidor(args[0]);

        menu();
        try {

            Scanner entrada = new Scanner(System.in);

            while (entrada.hasNext()) {
                String texto = entrada.nextLine();

                commands.stream().filter(
                        command -> command.verify(texto)
                ).findFirst().get().execute(texto, fs);
            }

        } catch (Exception ex) {
            System.out.println("DEU RUIM: " + ex.getMessage());
        }

    }

    private static FSInterface registraServidor(String hostName) {
        String connectLocation = "//" + hostName + "/Client";
        FSInterface fs = null;
        try {
            fs = (FSInterface) Naming.lookup(connectLocation);
        } catch (Exception e) {
            System.out.println("Fs connection failed: ");
            e.printStackTrace();
        }
        return fs;
    }

    public static void menu() {
        System.out.println("\tBem Vindo ao - RMI Commander");
        System.out.println("Lista de Comandos:");
        System.out.println("Exit   -> Sair");
        System.out.println("ls     -> Listar arquivos Ex: ls ./tmp");
        System.out.println("mkdir  -> Criar diretorio Ex: mkdir teste");
        System.out.println("create -> Criar arquivos Ex: create ./arquivo.txt");
        System.out.println("unlink -> Remove arquivos Ex: create ./arquivo.txt");
    }
}