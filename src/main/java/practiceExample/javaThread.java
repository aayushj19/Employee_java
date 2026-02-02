package practiceExample;

//class Mythread implements Runnable{
//       public void run(){
//            System.out.println("The thread is running");
//       }
//}
//
//public class javaThread{
//    static void main(String[] args) {
//        Thread t = new Thread(new Mythread());
//        t.start();
//    }
//}

class TransactionTask implements Runnable {
    private int txnId;

    TransactionTask(int txnId) {
        this.txnId = txnId;
    }

    @Override
    public void run() {
        System.out.println(
                "Processing transaction " + txnId +
                        " by " + Thread.currentThread().getName()
        );

        processPayment();
        sendNotification();
    }

    private void processPayment() {
        try { Thread.sleep(2000); } catch (Exception e) {}
    }

    private void sendNotification() {
        System.out.println("Notification sent for txn " + txnId);
    }
}
