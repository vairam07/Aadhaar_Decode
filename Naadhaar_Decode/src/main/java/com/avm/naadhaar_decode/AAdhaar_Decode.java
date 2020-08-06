package com.avm.naadhaar_decode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;

public class AAdhaar_Decode {

    String contents;

    public AAdhaar_Decode(String contents) {

        this.contents = contents;
    }

    public void main(String[] args) throws IOException {
        AAdhaar_Decode aAdhaar_decode = new AAdhaar_Decode(contents);
        byte[] content = aAdhaar_decode.decompressByteArray(contents);


    }

    public byte[] decompressByteArray(String bytes) throws IOException {
        BigInteger bigInteger = new BigInteger(bytes,10);
        System.out.println(bigInteger);
        byte[] aByte = bigInteger.toByteArray();
        System.out.println(Arrays.toString(aByte));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(aByte);
            GZIPInputStream gis = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int len;
            while((len = gis.read(buffer)) != -1){
                os.write(buffer, 0, len);
            }
            os.close();
            gis.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return os.toByteArray();
    }

 }




