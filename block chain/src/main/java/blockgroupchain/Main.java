package blockgroupchain;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        int port = 6767;
        Spark.port(port);
        Spark.init();

        Spark.get("/is_valid", (Request request, Response response) -> {
            boolean isValid = blockchain.isChainValid();
            return isValid ? "Blockchain is valid" : "There is an issue with the blockchain integrity";
        });

        Spark.get("/add_block", (Request request, Response response) -> {
            String data = request.queryParams("data");
            Block latestBlock = blockchain.getChain().get(blockchain.getChain().size() - 1);
            int proof = blockchain.proofOfWork(latestBlock.getProof());
            Block newBlock = new Block(blockchain.getChain().size(), data, latestBlock.getHash(), proof);
            blockchain.addBlock(newBlock);
            return new Gson().toJson(newBlock);
        });

        Spark.get("/get_chain", (Request request, Response response) -> new Gson().toJson(blockchain.getChain()));

        Spark.get("/sort_chain", (Request request, Response response) -> {
            blockchain.sortChainByIndex();
            return "Blockchain sorted by index";
        });

        Spark.get("/search_block", (Request request, Response response) -> {
            int index = Integer.parseInt(request.queryParams("index"));
            Block searchedBlock = blockchain.searchBlockByIndex(index);
            return searchedBlock != null ? new Gson().toJson(searchedBlock) : "Block not found";
        });

        Spark.post("/add_user_block", (Request request, Response response) -> {
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            Block latestBlock = blockchain.getChain().get(blockchain.getChain().size() - 1);
            int proof = blockchain.proofOfWork(latestBlock.getProof());
            User newUser = new User(blockchain.getChain().size(), username, password, latestBlock.getHash(), proof);
            blockchain.addBlock(newUser);
            return new Gson().toJson(newUser);
        });
    }
}
