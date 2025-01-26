## Huffman Coding

Huffman Coding is a lossless data compression algorithm. The algorithm assigns codes to input characters based on the frequency of the character, rather than a fixed for each character. The codes assigned to the characters are also prefix codes, meaning that no code for a certain character is a prefix of the code for another character. 

## Features

- The project contains two main folders, image_encoding and txt_encoding. 
- In the image_encoding folder, running the ImageHuffman file and inputting an image will assign and print prefix codes for every different pixel value in the image. 
- Running the Huffman file in the txt_encoding folder and inputting a txt file will assign and print prefix codes for every different character in the file. The prefix code string for the entire file will also be printed.

