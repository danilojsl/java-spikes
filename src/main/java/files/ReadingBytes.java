package files;

import data.structures.tree.Main;

import java.io.*;

public class ReadingBytes {


    public static void main(String[] args) throws IOException {

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("/home/danilo/JSL/datasets/PubMed-shuffle-win-2.bin"), 1 << 15);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("/home/danilo/JSL/datasets/PubMed-light.bin"));

        int vectorSize = 200;
        int CHUNK_SIZE = 32 + (4 * vectorSize); // change to 8096
        int startPos = 0;
        int endPos = 832 * 10;
        int bytesToRead = endPos - startPos;
        int b;
        byte[] buff = new byte[CHUNK_SIZE];
        bufferedInputStream.skip(startPos - 1);
        int currentChunkSize = Math.min(CHUNK_SIZE, bytesToRead);
        while ((b = bufferedInputStream.read(buff, 0, currentChunkSize)) != -1) {
            bufferedOutputStream.write(buff, 0, b);
            System.out.println("Writing " + b);
            bytesToRead -= b;
            if (bytesToRead == 0) {
                break;
            }
            currentChunkSize = Math.min(CHUNK_SIZE, bytesToRead);
        }

        bufferedInputStream.close();
        bufferedOutputStream.close();

    }

}
