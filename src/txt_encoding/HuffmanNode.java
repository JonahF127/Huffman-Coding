package txt_encoding;

public class HuffmanNode {
    //character to hold the data
    char data;

    //integer with frequency of the character
    int frequency;

    //left node
    HuffmanNode left;

    //right node
    HuffmanNode right;

    //node constructor 
    HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
}