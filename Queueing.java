import java.util.LinkedList;

public class Queueing {
    private final LinkedList<String> userQueue;

    public Queueing() {
        this.userQueue = new LinkedList<>();
    }

    public void addUser(String username) {
        userQueue.addLast(username);
        System.out.println(username + " has been added to the queue.");
    }

    public String processNextUser() {
        if (!userQueue.isEmpty()) {
            String nextUser = userQueue.removeFirst();
            System.out.println("Processing " + nextUser + "...");
            return nextUser;
        } else {
            System.out.println("The queue is empty.");
            return null;
        }
    }

    public boolean isEmpty() {
        return userQueue.isEmpty();
    }

    public void viewQueue() {
        System.out.println("Current queue: " + userQueue);
    }
}