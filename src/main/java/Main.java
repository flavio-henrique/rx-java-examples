import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Main {

    int i = 0;
    public static void main(String [] args) {
        Service service = new Service();
        Observable.just(1)
                .map(i -> service.getData())
                .takeUntil(n -> n == 3)
                .repeatWhen(completed -> completed.delay(2, TimeUnit.SECONDS))
                .doAfterNext((a) -> service.increment())
                .subscribe(onSuccess -> {
                    if(service.getTimes() > 2) {
                        throw new RuntimeException("time exceeded");
                    }
                }, onError -> {
                    System.out.println("error -> " + onError.getMessage());
                },() -> {
                    System.out.println("completed");
                });
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int increment() {
        return this.i++;
    }
}
