package Controle;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Principal {
    public static void main(String[] args) throws Exception {
        // Diretório contendo as imagens
        String directoryPath = "C:\\Users\\Luis\\OneDrive\\Documents\\CIFAR-10-images-master\\test\\Teste de Leitura";

        // Lista de arquivos de imagem na pasta
        File directory = new File(directoryPath);
        File[] imageFiles = directory
                .listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png") ||
                        name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".gif"));

        // Define o número de threads com base no número de processadores disponíveis
        int numThreads = Runtime.getRuntime().availableProcessors();

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        long startTime = System.currentTimeMillis();

        for (File imageFile : imageFiles) {
            Runnable worker = new Paralelizacao(imageFile, startTime);
            executor.execute(worker);
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        //Filtro.histogramaRGB();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Tempo total de processamento: " + totalTime + " ms");
    }
}
