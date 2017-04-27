import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ivan on 29/03/17.
 */

public class Utils {

    public static ArrayList<String> wordArray, sigArray;
    public static String wordList;

    public Utils() {
        initArrays();
    }

    public static void initArrays() {
        String res[] = readFile("dict/dictionary.txt").split("[)\n]");

        wordArray = new ArrayList<>();
        sigArray = new ArrayList<>();

        for (int i = 0; i < res.length; i++) {
            if (i % 2 == 0)
                Utils.wordArray.add(res[i]);
            else
                Utils.sigArray.add(res[i]);
        }

        wordList = "";

        for (int i = 0; i < wordArray.size(); i++)
            if (i < wordArray.size() - 1)
                wordList += wordArray.get(i) +"\n";
            else
                wordList += wordArray.get(i);
    }

    //Buscar uma palavra no dicionario e retornar a posicao
    public static int searchWord(String word) {

        int i;
        for (i = 0; i < Utils.wordArray.size(); i++)
            if (word.equalsIgnoreCase(Utils.wordArray.get(i)))
                return i;
        return -1;
    }

    //Ler arquivos txt
    private static String readFile(String path) {
        StringBuilder builder = new StringBuilder();

        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null)
                builder.append(line + "\n");
            br.close();
        }catch(IOException ignored){}
        return builder.toString();
    }

    //Escrever informações em arquivos txt
    public static boolean writeFile(String path, String word, String meaning) {

        BufferedWriter bw;
        FileWriter fw;

        try {
            String content = "";
            String res[] = readFile("dict/dictionary.txt").split("[)\n]");
            if (res[0].equalsIgnoreCase(""))
                content = word +")" +meaning;
            else
                content = "\n" +word +")" +meaning;
            fw = new FileWriter(path, true);
            bw = new BufferedWriter(fw);
            bw.write(content);

            bw.close();
            fw.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Realizar conexão socket
    public static ServerSocket runServer(int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ignored) {}

        return serverSocket;
    }

}
