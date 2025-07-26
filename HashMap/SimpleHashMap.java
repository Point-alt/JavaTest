import java.util.Objects;

public class SimpleHashMap<K, V> {
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node<K, V>[] table;
    int capacity = 16;
    int size = 0;

    public SimpleHashMap() {
        table = (Node<K, V>[]) new Node[capacity];
    }

    private int getIndex(K key) {
        if (key == null) {
            return 0;
        }
        int hash = key.hashCode();
        return Integer.remainderUnsigned(key.hashCode(), capacity);
    }

    public void put (K key, V value) {
        int index = getIndex(key);
        Node<K, V> head = table[index];

        for(Node<K, V> node = head; node != null; node = node.next) {
            if (Objects.equals(node.key, key)) {
                node.value = value;
                return;
            }
        }

        Node<K, V> newNode = new Node<>(key, value, head);
        table[index] = newNode;
        size++;
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> node = table[index];
        while (node != null) {
            if (Objects.equals(node.key, key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = getIndex(key);
        Node<K, V> node = table[index];
        Node<K, V> prev = null;

        while (node != null) {
           if (Objects.equals(node.key, key)) {
                if (prev == null) {
                    table[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return node.value;
            }
           prev = node;
           node = node.next;
        }
        return null;
    }
    public int size() {
        return size;
    }



}
