package T4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Gz implements ICompactacao {

    @Override
    public void compactar(String caminhoArquivo) throws Exception {
        String comando = "gzip " + caminhoArquivo; 
        executarComando(comando);
    }

    @Override
    public void descompactar(String caminhoArquivo) throws Exception {
        String comando = "gzip -d " + caminhoArquivo + ".gz"; 
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
    }
}
