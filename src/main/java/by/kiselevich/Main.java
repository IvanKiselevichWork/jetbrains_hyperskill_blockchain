package by.kiselevich;

import by.kiselevich.model.Block;
import by.kiselevich.model.Blockchain;
import by.kiselevich.util.StringUtil;

public class Main {

    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        blockchain.addBlock();
        blockchain.addBlock();
        blockchain.addBlock();
        blockchain.addBlock();
        blockchain.addBlock();

        System.out.println(blockchain);
        System.out.println("Is blockchain valid: " + blockchain.isBlockchainValid());

        for (Block block : blockchain.getBlockchain()) {
            System.out.println(block);
        }
    }
}
