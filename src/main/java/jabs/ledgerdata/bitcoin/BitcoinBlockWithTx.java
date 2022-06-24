package jabs.ledgerdata.bitcoin;

import jabs.network.node.nodes.bitcoin.BitcoinMinerNode;

import java.util.HashSet;
import java.util.Set;

import static jabs.ledgerdata.BlockFactory.BITCOIN_BLOCK_HEADER_SIZE;

public class BitcoinBlockWithTx extends BitcoinBlock {
    private final Set<BitcoinTx> Txs;

    public BitcoinBlockWithTx(int height, double creationTime, BitcoinBlock parent, BitcoinMinerNode creator,
                              Set<BitcoinTx> txs, double difficulty) {
        super(0, height, creationTime, creator, parent, difficulty);
        Txs = txs;

        int totalSize = BITCOIN_BLOCK_HEADER_SIZE;
        for (BitcoinTx tx:txs) {
            totalSize += tx.getSize();
        }

        this.size = totalSize;
    }

    public Set<BitcoinTx> getTxs() { return new HashSet<>(this.Txs); }
}
