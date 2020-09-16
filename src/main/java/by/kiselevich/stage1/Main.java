package by.kiselevich.stage1;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Block {
    private Long id;
    private Long timestamp;
    private String previousBlockHash;

    public Block(Long id, Long timestamp, String previousBlockHash) {
        this.id = id;
        this.timestamp = timestamp;
        this.previousBlockHash = previousBlockHash;
    }

    public Long getId() {
        return id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Block:\n");
        sb.append("Id: ");
        sb.append(id);
        sb.append("\n");
        sb.append("Timestamp: ");
        sb.append(timestamp);
        sb.append("\n");
        sb.append("Hash of the previous block: \n");
        sb.append(previousBlockHash);
        sb.append("\n");
        sb.append("Hash of the block: \n");
        sb.append(StringUtil.applySha256(this));
        sb.append("\n");

        return sb.toString();
    }
}

class Blockchain {
    private List<Block> blockchain = new ArrayList<>();

    public Block getFirstBlock() {
        return blockchain.get(0);
    }

    public List<Block> getBlockchain() {
        return new ArrayList<>(blockchain);
    }

    public Block addBlock() {
        String hash = "0";
        if (!blockchain.isEmpty()) {
            hash = StringUtil.applySha256(blockchain.get(blockchain.size() - 1));
        }
        Block block = new Block((long) blockchain.size(), new Date().getTime(), hash);
        blockchain.add(block);
        return block;
    }

    public boolean isBlockchainValid() {
        if (blockchain.isEmpty()) {
            return true;
        }
        if (!blockchain.get(0).getPreviousBlockHash().equals("0")) {
            return false;
        }
        if (blockchain.size() == 1) {
            return true;
        }
        for (int i = 0; i < blockchain.size() - 1; i++) {
            if (!StringUtil.applySha256(blockchain.get(i)).equals(blockchain.get(i + 1).getPreviousBlockHash())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Blockchain{" +
                "blockchain=" + blockchain +
                '}';
    }
}

class StringUtil {
    /* Applies Sha256 to a string and returns a hash. */
    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte elem : hash) {
                String hex = Integer.toHexString(0xff & elem);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* Applies Sha256 to a Block and returns a hash. */
    public static String applySha256(Block block) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(block.getId());
            sb.append(block.getTimestamp());
            sb.append(block.getPreviousBlockHash());
            String input = sb.toString();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte elem : hash) {
                String hex = Integer.toHexString(0xff & elem);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        blockchain.addBlock();
        blockchain.addBlock();
        blockchain.addBlock();
        blockchain.addBlock();
        blockchain.addBlock();

        for (Block block : blockchain.getBlockchain()) {
            System.out.println(block);
        }
    }
}
