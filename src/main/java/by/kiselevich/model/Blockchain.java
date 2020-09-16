package by.kiselevich.model;

import by.kiselevich.util.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blockchain {
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
