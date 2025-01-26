package image_encoding;

import java.util.Comparator;

public class ImageNodeComparator implements Comparator<ImageNode> {
    @Override
    public int compare(ImageNode node1, ImageNode node2) {
        return Integer.compare(node1.frequency, node2.frequency);
    }
    
}
