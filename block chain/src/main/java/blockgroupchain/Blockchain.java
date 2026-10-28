package blockgroupchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;
    private int proofOfWorkDifficulty = 4;

    public Blockchain() {
        chain = new ArrayList<>();
        chain.add(new Block(0, "Genesis Block", "0", 0));
    }

    public void addBlock(Block newBlock) {
        chain.add(newBlock);
    }

    public int proofOfWork(int previousProof) {
        int newProof = 1;
        boolean checkProof = false;
        while (!checkProof) {
            String hashOperation = StringUtil.applySha256(Integer.toString(newProof * newProof - previousProof * previousProof));
            if (hashOperation.startsWith("0".repeat(proofOfWorkDifficulty))) {
                checkProof = true;
            } else {
                newProof++;
            }
        }
        return newProof;
    }

    public boolean isChainValid() {
        Block previousBlock = chain.get(0);
        int blockIndex = 1;
        while (blockIndex < chain.size()) {
            Block currentBlock = chain.get(blockIndex);
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash()) ||
                    !currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }
            int previousProof = previousBlock.getProof();
            int proof = currentBlock.getProof();
            String hashOperation = StringUtil.applySha256(Integer.toString(proof * proof - previousProof * previousProof));
            if (!hashOperation.startsWith("0".repeat(proofOfWorkDifficulty))) {
                return false;
            }
            previousBlock = currentBlock;
            blockIndex++;
        }
        return true;
    }

    public List<Block> getChain() {
        return chain;
    }

    public void sortChainByIndex() {
        chain.sort((Block a, Block b) -> Integer.compare(a.getIndex(), b.getIndex()));
    }

    public Block searchBlockByIndex(int index) {
        for (Block block : chain) {
            if (block.getIndex() == index) {
                return block;
            }
        }
        return null; // Block not found
    }
}