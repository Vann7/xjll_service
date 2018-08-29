import java.util.concurrent.*;

public class Demo {



    public static void main(String[] args){
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        ExecutorService service = Executors.newFixedThreadPool(2);
        for(int i=0; i<10; i++) {
            final int tmp = i;

            service.submit(() -> {
                map.put(tmp,"hello: "+ tmp);
                System.out.println(tmp+" ,多线程: " + Thread.currentThread().getName());
            });
        }

        System.out.println(map);
        service.shutdown();

    }
}
