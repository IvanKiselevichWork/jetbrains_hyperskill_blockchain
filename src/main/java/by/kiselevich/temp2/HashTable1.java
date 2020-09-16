package by.kiselevich.temp2;

public class  HashTable1<T> {

    public int collision = 0;

    private class TableEntry1<T> {
        private final int key;
        private final T value;

        public TableEntry1(int key, T value) {
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

    private final int size;
    private TableEntry1[] table;

    public HashTable1(int size) {
        this.size = size;
        table = new TableEntry1[size];
    }

    private int findEntryIndex(int key) {
        int hash = key % size;

        while (!(table[hash] == null || table[hash].getKey() == key)) {
            collision++;
            hash = (hash + 1) % size;

            if (hash == key % size) {
                return -1;
            }
        }

        return hash;
    }

    public boolean put(int key, T value) {
        int idx = findEntryIndex(key);

        if (idx == -1) {
            return false;
        }

        table[idx] = new TableEntry1(key, value);
        return true;
    }

    public T get(int key) {
        int idx = findEntryIndex(key);

        if (idx == -1 || table[idx] == null) {
            return null;
        }

        return (T) table[idx].getValue();
    }

    @Override
    public String toString() {
        StringBuilder tableStringBuilder = new StringBuilder();

        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                tableStringBuilder.append(i + ": null");
            } else {
                tableStringBuilder.append(i + ": key=" + table[i].getKey()
                        + ", value=" + table[i].getValue());
            }

            if (i < table.length - 1) {
                tableStringBuilder.append("\n");
            }
        }

        return tableStringBuilder.toString();
    }
}
