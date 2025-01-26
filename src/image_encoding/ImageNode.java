package image_encoding;


public class ImageNode {
    //integer to hold the pixel value
    float value;

    //integer with frequency of the character
    int frequency;

    //left node
    ImageNode left;

    //right node
    ImageNode right;

    //node constructor 
    ImageNode(float value,int frequency) {
        this.value = value;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
}
