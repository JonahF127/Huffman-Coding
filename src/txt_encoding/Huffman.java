package txt_encoding;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {
    // method to construct the huffman tree
    public HuffmanNode huffman(ArrayList<HuffmanNode> C) { 
        Comparator<HuffmanNode> frequencyComparator = new HuffmanNodeComparator();
        PriorityQueue<HuffmanNode> prefixCodeTree = new PriorityQueue<>(frequencyComparator);
        for(HuffmanNode x : C) {
            prefixCodeTree.add(x); // add all to priority queue
        }

        while(prefixCodeTree.size() > 1) { //repeat until only one item in queue
            HuffmanNode left = prefixCodeTree.poll(); // extract the two nodes with the lowest frequencies
            HuffmanNode right = prefixCodeTree.poll(); 
            HuffmanNode z = new HuffmanNode('$', left.frequency + right.frequency); // z's frequency is their sum
            z.left = left;
            z.right = right;
            prefixCodeTree.add(z); // z replaces left and right
        }
        return prefixCodeTree.peek();
    } 

    // method to print the codes
    public HashMap<Character, String> printCodes(HuffmanNode root, StringBuilder code, HashMap<Character, String> codeMap) {
        if (root == null) {
            return codeMap;
        }

        if (root.data != '$') {
            System.out.println(root.data + ": " + code);
            codeMap.put(root.data, code.toString());
        }

        if (root.left != null) {
            printCodes(root.left, new StringBuilder(code).append("0"), codeMap);
        }

        if (root.right != null) {
            printCodes(root.right, new StringBuilder(code).append("1"), codeMap);
        }

        return codeMap;
    }






    public static void main(String[] args){
        ArrayList<HuffmanNode> C = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file you want to be encoded");
        String message = "";

        try {
            File file = new File(scanner.nextLine());
            Scanner reader = new Scanner(file);
            
            while(reader.hasNextLine()) {
                message += reader.nextLine();
            }
            scanner.close();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
        }
        
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : message.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        for(char key : frequencyMap.keySet()) {
            HuffmanNode node = new HuffmanNode(key, frequencyMap.get(key));
            C.add(node);
        }

        Huffman huffmanEncoder = new Huffman();
        HuffmanNode root = huffmanEncoder.huffman(C);
        HashMap<Character, String> codes = new HashMap<>();
        huffmanEncoder.printCodes(root, new StringBuilder(), codes);

        for(char x : message.toCharArray()) {
            System.out.print(codes.get(x));
        }
        
    }







}
