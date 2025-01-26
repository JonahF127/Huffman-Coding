package txt_encoding;
import java.util.Comparator;

public class HuffmanNodeComparator implements Comparator<HuffmanNode> {

    @Override
    public int compare(HuffmanNode node1, HuffmanNode node2) {
        return Integer.compare(node1.frequency, node2.frequency);
    }

}

