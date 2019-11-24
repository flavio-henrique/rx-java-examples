public class Service {
    private int result = 0;

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void increment() {
        this.times++;
    }

    private int times = 0;

    public int getData() {
        return this.result++;
    }


}
