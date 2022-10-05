import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        try {
            Semaphore sendPackageSemaphore = new Semaphore(3);
            Semaphore receivePackageSemaphore = new Semaphore(3);

            List<Integer> list = new Vector<>();


            receivePackageSemaphore.acquire(3);

            Thread envioThread = new Thread(new Envio(sendPackageSemaphore, receivePackageSemaphore, list));
            Thread reciboThread = new Thread(new Recibo(sendPackageSemaphore,receivePackageSemaphore, list));


            envioThread.start();
            reciboThread.start();

            reciboThread.join();

            System.out.println("******Programa Terminado*******");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
