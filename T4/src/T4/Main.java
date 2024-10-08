package T4;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
         
            JFileChooser fileChooser = new JFileChooser();
            
           
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            
           
            int result = fileChooser.showOpenDialog(null);  
           
            if (result == JFileChooser.APPROVE_OPTION) {
              
                File selectedFile = fileChooser.getSelectedFile();
                String caminhoArquivo = selectedFile.getAbsolutePath();
                System.out.println("Arquivo selecionado: " + caminhoArquivo);

                
                String[] opcoes = {"ZIP", "7z", "Gz"};
                int escolha = JOptionPane.showOptionDialog(null, "Escolha o tipo de compactação",
                        "Tipo de Compactação", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, opcoes, opcoes[0]);

                ICompactacao compactador;

              
                switch (escolha) {
                    case 0: 
                        compactador = new Zip();
                        break;
                    case 1: 
                        compactador = new Z7();
                        break;
                    case 2: 
                        compactador = new Gz();
                        break;
                    default:
                        System.out.println("Escolha inválida!");
                        return;
                }

              
                System.out.println("### Compactando o arquivo ###");
                compactador.compactar(caminhoArquivo);

               
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja descompactar o arquivo criado?",
                        "Descompactar", JOptionPane.YES_NO_OPTION);

                if (resposta == JOptionPane.YES_OPTION) {
                    System.out.println("### Descompactando o arquivo ###");
                    compactador.descompactar(caminhoArquivo);
                }

            } else {
                System.out.println("Nenhum arquivo selecionado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
