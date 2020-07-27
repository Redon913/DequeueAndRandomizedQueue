import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
    private Node head;
    private Node last;
    private int size = 0;
    private class Node
    {
        private Node prevNode;
        private Item data;
        private Node nextNode;

        Node(Item item)
        {
            prevNode = null;
            data = item;
            nextNode = null;
        }
    }

    // construct an empty deque
    public Deque()
    {
        this.head = new Node(null);
        this.last = new Node(null);
        this.head.nextNode = this.last;
        this.last.prevNode = this.head;
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        if (this.size == 0) return true;
        return false;
    }

    // return the number of items on the deque
    public int size()
    {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null) throw new IllegalArgumentException();
        if (this.head.data == null) {
            this.head.data = item;
            this.size++;
        } else {
            Node oldList = this.head;
            this.head = new Node(item);
            this.head.nextNode = oldList;
            this.size++;
        }
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null) throw new IllegalArgumentException();
        if (this.last.data == null) {
            this.last.data = item;
            this.size++;
        } else {
            Node oldList = this.last;
            this.last = new Node(item);
            this.last.prevNode = oldList;
            oldList.nextNode = this.last;
            this.size++;
        }
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = this.head.data;
        this.head = this.head.nextNode;
        this.size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (isEmpty())throw new NoSuchElementException();
        if (this.size == 1) return this.removeFirst();
        Item item = this.last.data;
        this.last = this.last.prevNode;
        this.last.nextNode = null;
        this.size--;
        return item;

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = head;
        public boolean hasNext()
        {
            return current != null;
        }

        public Item next()
        {
            if (!hasNext())throw new NoSuchElementException();
            Item item = current.data;
            current = current.nextNode;
            return item;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<Integer> list = new Deque<Integer>();
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        list.addFirst(1);
        list.addFirst(2);
        list.addLast(3);
        list.addLast(4);
        System.out.println();
        // System.out.println(list.removeFirst());
        // System.out.println(list.removeLast());
        System.out.println();
        System.out.println(list.size());
        System.out.println();

        // while (list.has) {
        //     System.out.println(list.head.data);
        //     list.head = list.head.nextNode;
        // }

        for (Integer integer : list) {
            System.out.println(integer);
        }


    }

}
