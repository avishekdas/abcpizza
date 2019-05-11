package com.abc.service.order.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;

public class ByteBufferRead {
    public static void main(String[] args) {

        long startTime = System.nanoTime();

        try {
            RandomAccessFile aFile = new RandomAccessFile("c:/temp/my-large-file.csv", "r");
            FileChannel inChannel = aFile.getChannel();
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());

            StringBuffer strBuff = new StringBuffer();

            for (int i = 0; i < buffer.limit(); i++) {
                byte read = buffer.get();
                strBuff.append((char)read);
                //System.out.print((char)read);

            }
            aFile.close();
            //Customer convertedCustomer = mapper.readValue(jsonString, Customer.class);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        long endTime = System.nanoTime();
        long elapsedTimeInMillis = TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS);
        System.out.println("Total elapsed time: " + elapsedTimeInMillis + " ms");
    }
}
