package T4;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Zip implements ICompactacao {

    @Override
    public void compactar(String caminhoArquivo) throws Exception {
        
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            throw new Exception("O caminho do arquivo '" + caminhoArquivo + "' não existe ou é inválido.");
        }

        
        String comando = "tar -a -cf \"" + caminhoArquivo + ".zip\" \"" + caminhoArquivo + "\"";
        executarComando(comando);
    }

    @Override
    public void descompactar(String caminhoArquivo) throws Exception {
     
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            throw new Exception("O caminho do arquivo compactado '" + caminhoArquivo + "' não existe ou é inválido.");
        }

        String comando = "tar -xf \"" + caminhoArquivo + "\"";
        executarComando(comando);
    }

    private void executarComando(String comando) throws Exception {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", comando);
        builder.redirectErrorStream(true);
        Process process = builder.start();

       
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String linha;
        while ((linha = reader.readLine()) != null) {
            System.out.println(linha);
        }
        process.waitFor();

       
        if (process.exitValue() != 0) {
            throw new Exception("Falha ao executar o comando: " + comando);
        }
    }
}
