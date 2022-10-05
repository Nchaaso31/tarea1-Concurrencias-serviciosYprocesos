import java.util.List;
import java.util.concurrent.Semaphore;

public class Recibo implements Runnable{
    private Semaphore sendPackageSemaphore;
    private Semaphore receivePackageSemaphore;

    private List<Integer> list;

    public Recibo(Semaphore sendPackageSemaphore, Semaphore receivePackageSemaphore, List<Integer> list) {
        this.sendPackageSemaphore = sendPackageSemaphore;
        this.receivePackageSemaphore = receivePackageSemaphore;
        this.list = list;
    }



    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            try {
                receivePackageSemaphore.acquire();
                int num = list.remove(0);
                System.out.println("Recibido paquete " + num);
                sendPackageSemaphore.release();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

