package by.kiselevich.temp;

import java.util.Scanner;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;
        private boolean removed;

        public TableEntry(int key, T value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }

        public void remove() {
            removed = true;
        }

        public boolean isRemoved() {
            return removed;
        }
    }

    private static class HashTable<T> {
        private int size;
        private TableEntry[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(int key, T value) {
            int idx = findKey(key);
            table[idx] = new TableEntry(key, value);
            return true;
        }

        public T get(int key) {
            int idx = findKey(key);
            if (table[idx] == null) {
                return null;
            }
            return (T) table[idx].getValue();
        }

        public void remove(int key) {
            int idx = findKey(key);
            if (table[idx] != null && table[idx].getKey() == key) {
                table[idx] = null;
            }
        }

        private int findKey(int key) {
            return key % size;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        HashTable<String> table = new HashTable<>(size);
        String query;
        String s;
        String[] parsedQuery;
        for (int i = 0; i < size; i++) {
            query = scanner.nextLine();
            parsedQuery = query.split(" ");
            if (parsedQuery[0].equals("put")) {
                table.put(Integer.parseInt(parsedQuery[1]), parsedQuery[2]);
            }
            if (parsedQuery[0].equals("get")) {
                s = table.get(Integer.parseInt(parsedQuery[1]));
                if (s != null) {
                    System.out.println(s);
                } else {
                    System.out.println("-1");
                }
            }
            if (parsedQuery[0].equals("remove")) {
                table.remove(Integer.parseInt(parsedQuery[1]));
            }
        }
    }
}