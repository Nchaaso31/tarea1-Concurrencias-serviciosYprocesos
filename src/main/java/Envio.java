import java.util.List;
import java.util.concurrent.Semaphore;

public class Envio implements Runnable {
    private Semaphore sendPackageSemaphore;
    private Semaphore receivePackageSemaphore;

    private List<Integer> list;

    public Envio(Semaphore sendPackageSemaphore, Semaphore receivePackageSemaphore, List<Integer> list) {
        this.sendPackageSemaphore = sendPackageSemaphore;
        this.receivePackageSemaphore = receivePackageSemaphore;
        this.list = list;
    }

    public Envio(Semaphore sendPackageSemaphore, Semaphore receivePackageSemaphore) {
        this.sendPackageSemaphore = sendPackageSemaphore;
        this.receivePackageSemaphore = receivePackageSemaphore;
    }


    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {

            try {
                sendPackageSemaphore.acquire();
                System.out.println("Enviado paquete " + i);
                list.add(i);
                receivePackageSemaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

