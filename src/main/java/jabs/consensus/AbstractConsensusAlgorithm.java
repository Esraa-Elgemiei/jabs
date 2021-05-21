package jabs.consensus;

import jabs.data.Block;
import jabs.data.Tx;

import java.util.HashSet;

public abstract class AbstractConsensusAlgorithm<B extends Block<B>, T extends Tx<T>>
        implements ConsensusAlgorithm<B, T> {

    /**
     * All accepted blocks (received and agreed) for the consensus algorithm
     */
    protected HashSet<B> acceptedBlocks = new HashSet<>();

    /**
     * All accepted transactions (residing inside accepted blocks)
     */
    protected final HashSet<T> acceptedTxs = new HashSet<>();

    /**
     * When a new block is received by the node this function should be called.
     * The consensus algorithm should take actions required accordingly to
     * update the state of agreement.
     *
     * @param block Recently received block
     */
    @Override
    public abstract void newIncomingBlock(B block);

    /**
     * Check if the received block is valid according to the state of the chain.
     * This might include difficulty check or signature verification etc.
     *
     * @param block The block to check if it is valid or not
     * @return True if the block is valid according to the current state of the
     * chain
     */
    @Override
    public boolean isBlockValid(B block) { // TODO: This should check that the block meets required difficulty
        return true;
    } // for checking difficulty signature and etc

    /**
     * Check if this block is agreed by the consensus algorithm executed by node.
     *
     * @param block The block to check if it is agreed by consensus algorithm.
     * @return True if the block is accepted by the consensus algorithm.
     */
    @Override
    public boolean isBlockAccepted(B block) {
        return acceptedBlocks.contains(block);
    }

    /**
     * Check if the provided transaction is inside a block that is agreed by the
     * node consensus algorithm.
     *
     * @param tx The transaction to check if it is included in a agreed block
     *           inside the consensus algorithm.
     * @return True if the transaction is inside a block that is agreed by the
     * consensus algorithm.
     */
    @Override
    public boolean isTxAccepted(T tx) {
        return acceptedTxs.contains(tx);
    }

    /**
     * Returns the total number of blocks agreed by the consensus algorithm
     * executed by the node
     *
     * @return The total number of blocks agreed by consensus algorithm
     */
    @Override
    public int getNumOfAcceptedBlocks() {
        return acceptedBlocks.size();
    }

    /**
     * Returns the yotal number of accepted transactions by the consensus algorithm
     *
     * @return Total number of accepted transactions by the consensus algorithm
     */
    @Override
    public int getNumOfAcceptedTxs() {
        return acceptedTxs.size();
    }
}
