import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] stack;
    private int top = 0, stackSize = 1;
    // construct an empty randomized queue
    public RandomizedQueue()
    {
        this.stack  = (Item[]) new Object[this.stackSize];
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
        return this.top == 0;
    }

    // return the number of items on the randomized queue
    public int size()
    {
        return this.top;
    }

    // add the item
    public void enqueue(Item item)
    {
        if (item == null) throw new IllegalArgumentException();
        if (this.top == this.stackSize) this.stack = reSize(this.stackSize * 2);
        this.stack[this.top++] = item;
    }

    // remove and return a random item
    public Item dequeue()
    {
        if (isEmpty()) throw new NoSuchElementException();
        if (this.top == this.stackSize / 4) this.stack = reSize(this.stackSize/2);
        int randomIndex = StdRandom.uniform(this.top);
        Item item = this.stack[randomIndex];
        this.stack[randomIndex] = this.stack[--this.top];
        this.stack[this.top] = null;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample()
    {
        if (isEmpty()) throw new NoSuchElementException();
        return this.stack[StdRandom.uniform(this.top)];
    }

    private Item[] reSize(int size)
    {
        Item[] arr = (Item[]) new Object[size];
        for (int i = 0; i < this.top; i++) arr[i] = this.stack[i];
        this.stackSize = size;
        return arr;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item>
    {
        private final Item[] current = stack;
        private int iTop = top;

        public boolean hasNext()
        {
            return iTop > 0;
        }

        public Item next()
        {
            if (!hasNext()) throw new NoSuchElementException();
            int randomIndex = StdRandom.uniform(this.iTop);
            Item item = this.current[randomIndex];
            this.current[randomIndex] = this.current[--iTop];
            this.current[iTop] = null;
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
        RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
        test.enqueue(1);
        test.enqueue(2);
        test.enqueue(3);
        test.enqueue(4);
        test.enqueue(5);
        test.enqueue(6);
        // StdOut.println(test.dequeue());
        // StdOut.println(test.dequeue());
        // StdOut.println(test.dequeue());
        StdOut.println(test.sample());

        for (Integer integer: test) {
            StdOut.print(integer + " ");
        }
    }

}