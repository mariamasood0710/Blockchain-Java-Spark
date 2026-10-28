package blockgroupchain;

import java.util.Date;

public class Block {
    private int index;
    private String timestamp;
    private String data;
    private String hash;
    private String previousHash;
    private int proof;

    public Block(int index, String data, String previousHash, int proof) {
        this.index = index;
        this.timestamp = new Date().getTime() + "";
        this.data = data;
        this.previousHash = previousHash;
        this.proof = proof;
        this.hash = calculateHash();
    }

    public int getProof() {
        return proof;
    }

    public void setProof(int proof) {
        this.proof = proof;
    }

    public String calculateHash() {
        return StringUtil.applySha256(previousHash + timestamp + Integer.toString(index) + data + Integer.toString(proof));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
}
