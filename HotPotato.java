/**
 * Created by shawn on 2/27/2017.
 */
public class HotPotato {

    private int numSkip;
    private CircularList players = new CircularList();

    public HotPotato(int m, int n){
        if (m < 0 || n <= 0){
            throw new NumberFormatException();
        }
        numSkip = m;
        for (int i = 0; i < n; i++) {
            players.add(i + 1);
        }
    }

    public void play(){
        MyLinkedList.Node current = players.head;
        while(players.size() > 1) {
            for (int temp = numSkip; temp > 0; temp -= 1){
                current = current.next;
            }
            System.out.println("Eliminated person: " + players.remove(current));
            current = current.next;
            System.out.print("Remaining circle (starting with the next person): ");
            for (int temp = 0; temp < players.size() - 1; temp += 1) {
                System.out.print(current.toString() + ", ");
                current = current.next;
            }
            System.out.print(current.toString());
            current = current.next;
            System.out.println();
            System.out.println();
        }
        System.out.println("Winner: " + players.remove(0));
    }
}
