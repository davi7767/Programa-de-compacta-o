package T4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Z7 implements ICompactacao {

    
    private static final String SEVEN_ZIP_EXECUTABLE = "C:\\Users\\davi0\\Desktop\\T4\\7zr.exe";

    @Override
    public void compactar(String caminhoArquivo) throws Exception {
        String comando = SEVEN_ZIP_EXECUTABLE + " a " + caminhoArquivo + ".7z " + caminhoArquivo; 
        executarComando(comando);
    }

    @Override
    public void descompactar(String caminhoArquivo) throws Exception {
        String comando = SEVEN_ZIP_EXECUTABLE + " e " + caminhoArquivo + ".7z"; 
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
