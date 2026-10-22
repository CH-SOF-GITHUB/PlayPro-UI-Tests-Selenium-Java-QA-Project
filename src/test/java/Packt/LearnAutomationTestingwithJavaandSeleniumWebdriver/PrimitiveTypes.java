package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver;

public class PrimitiveTypes {

    public static void main(String[] args) {
        System.out.println("Byte'Size : " + Byte.SIZE);
        System.out.println("Byte'Bytes : " + Byte.BYTES);
        System.out.println("Byte'Max value : " + Byte.MAX_VALUE);
        System.out.println("Byte'Min value : " + Byte.MIN_VALUE);

        long l = 50000000000l;
        int i = (int) l;
        l = i;
        System.out.println(l);

        double dbl = 34.5678;
        float f2 = (float) dbl;
        System.out.println("f2: "+f2);
        System.out.println("f2++ : "+  dbl++);
        System.out.println("f2-- : " + dbl--);
    }
}
