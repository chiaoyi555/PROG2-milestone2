public class Instructions {

    //determine the type
    public static String InstructionType(int opcode, int funct){
        if(opcode == 0x00 && funct == 0x0c)
            return "syscall";
        else if(opcode == 0x00)
            return "R";
        else if(opcode == 0x02)
            return "J";
        else
            return "I";
    }

    //Parses and prints R-type instruction
    public static void rType(int opcode, int binary) {

    }

    //Parses and prints I-type instruction
    public static void iType(int opcode, int binary) {

    }

    //Parses and prints J-type instruction
    public static void jType(int opcode, int binary) {
        int index = binary & 0x3FFFFFF;
        String output = String.format("j {opcode: %02x, index: %07x}", opcode, index);
        System.out.println(output);
    }

    //print syscall instruction
    public static void syscall() {
        String output = "syscall {opcode: 00, code: 000000, funct: 0c}";
        System.out.println(output);
    }
}
