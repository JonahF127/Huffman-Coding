package image_encoding;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class ImageHuffman {
    // method to construct the huffman tree
    public ImageNode huffman(ArrayList<ImageNode> C) {
        Comparator<ImageNode> frequencyComparator = new ImageNodeComparator();
        PriorityQueue<ImageNode> prefixCodeTree = new PriorityQueue<>(frequencyComparator);
        for(ImageNode x : C){
            prefixCodeTree.add(x); // add all to priority queue
        }

        while(prefixCodeTree.size() > 1) { //repeat until only one item in queue
            ImageNode left = prefixCodeTree.poll(); // extract the two nodes with the lowest frequencies
            ImageNode right = prefixCodeTree.poll();
            ImageNode z = new ImageNode(Float.POSITIVE_INFINITY, left.frequency + right.frequency); // z's frequency is their sum
            z.left = left;
            z.right = right;
            prefixCodeTree.add(z); // z replaces left and right
        }
        return prefixCodeTree.peek();
    }
    // method to print the code
    public HashMap<Float, String> printCodes(ImageNode root, StringBuilder code, HashMap<Float, String> codemap){
        if (root == null){
            return codemap;
        }

        if(root.value != Float.POSITIVE_INFINITY) {
            System.out.println(root.value + ": " + code);
            codemap.put(root.value, code.toString());
        }

        if(root.left != null){
            printCodes(root.left, new StringBuilder(code).append("0"), codemap);
        }

        if(root.right != null) {
            printCodes(root.right, new StringBuilder(code).append("1"), codemap);
        }

        return codemap;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<ImageNode> C = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file you want to be encoded");
        

        BufferedImage img = null;

        try {
            img = ImageIO.read(new File(scanner.nextLine()));  
            scanner.close(); 
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
        }

        int width = img.getWidth();
        int height = img.getHeight();

        int[][] rgb = new int[height][width];
        for(int row = 0; row < height; row++) {
            for(int column = 0; column < width; column++) {
                rgb[row][column] =  img.getRGB(column, row);
            }
        }

        HashMap<Float, Integer> frequencyMap = new HashMap<>();
        for(int row = 0; row < height; row++) {
            for(int column = 0; column < width; column++) {
                float value = rgb[row][column];
                frequencyMap.put(value, frequencyMap.getOrDefault(value, 0)+1);
            }
        }

        for (float key : frequencyMap.keySet()) {
            ImageNode node = new ImageNode(key, frequencyMap.get(key));
            C.add(node);
        }

        ImageHuffman imageEncoder = new ImageHuffman();
        ImageNode root = imageEncoder.huffman(C);
        HashMap<Float, String> codes = new HashMap<>();
        imageEncoder.printCodes(root, new StringBuilder(), codes);

        for(int row = 0; row < height; row++) {
            for(int column = 0; column < width; column++) {
                float value = rgb[row][column];
                System.out.print(codes.get(value));
            }
        }





    }

}
