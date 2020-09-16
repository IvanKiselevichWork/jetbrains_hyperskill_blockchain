package by.kiselevich.temp3;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;

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
            if (table[idx] == null) {
                table[idx] = new TableEntry(key, value);
            } else {
                table[idx] = new TableEntry(key, table[idx].value + " " + value);
            }
            return true;
        }

        public T get(int key) {
            int idx = findKey(key);
            if (table[idx] == null) {
                return null;
            }
            return (T) table[idx].getValue();
        }

        private int findKey(int key) {
            return key % size;
        }

        public Set<TableEntry> entrySet() {
            Set<TableEntry> tableEntrySet = new HashSet<>();
            for (TableEntry tableEntry : table) {
                if (tableEntry != null) {
                    tableEntrySet.add(tableEntry);
                }
            }
            return tableEntrySet;
        }

        private void rehash() {
            // put your code here
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        HashTable<String> table = new HashTable<>(size);
        String query;
        String[] parsedQuery;
        for (int i = 0; i < size; i++) {
            query = scanner.nextLine();
            parsedQuery = query.split(" ");
            table.put(Integer.parseInt(parsedQuery[0]), parsedQuery[1]);
        }
        for (TableEntry tableEntry : table.entrySet()) {
            System.out.println(tableEntry.getKey() + ": " + tableEntry.getValue());
        }
    }
}
