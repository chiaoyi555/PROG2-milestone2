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
    //mnemonic {opcode: XX, rs: XX, rt: XX, rd: XX, shmt: XX, funct: XX}
    public static void rType(int opcode, int funct, int binary) {
        String mnemonic;
        switch(funct){
            case 32 -> mnemonic = "add"; //32 is the decimal of 0x20
            case 36 -> mnemonic = "and"; //0x24
            case 37 -> mnemonic = "or"; //0x25
            case 42 -> mnemonic = "slt"; //0x2A
            case 34 -> mnemonic = "sub"; //0x22
            default -> {
                System.out.println("Unknown R-type funct: " + funct);
                return;
            }
        }
        //shift
        int rs = (binary >>> 21) & 0x1F;
        int rt = (binary >>> 16) & 0x1F;
        int rd = (binary >>> 11) & 0x1F;
        int shmt = (binary >>> 6) & 0x1F;
        String output = String.format(mnemonic+" {opcode: %02x, rs: %02x, rt: %02x, rd: %02x, shmt: %02x, funct: %02x}"
                , opcode, rs, rt, rd, shmt, funct);
        System.out.println(output);
    }

    //Parses and prints I-type instruction
    //mnemonic {opcode: XX, rs(base): XX, rt: XX, immediate(offset): XXXX}
    public static void iType(int opcode, int binary) {
        String mnemonic;
        switch(opcode){
            case 9 -> mnemonic = "addiu";// opcode = 0x09
            case 12 -> mnemonic = "andi";// opcode = 0x0C
            case 4 -> mnemonic = "beq"; // opcode = 0x04
            case 5 -> mnemonic = "bne";// opcode = 0x05
            case 15 -> mnemonic = "lui";// opcode = 0x0F
            case 35 -> mnemonic = "lw";// opcode = 0x23
            case 13 -> mnemonic = "ori";// opcode = 0x0D
            case 43 -> mnemonic = "sw";// opcode = 0x2B
            default -> {
                System.out.println("Unknown I-type op: " + opcode);
                return;
            }
        }
        int rsBase = (binary>>21) & 0x1F;
        int rt = (binary>>16) & 0x1F;
        int immediateOffset = binary & 0x1FF; // mask - only want lower 16 bits
        String output = String.format(mnemonic+" {opcode: %02x, rs(base): %02x, rt: %02x, immediate(offset): %04x}", opcode, rsBase, rt, immediateOffset);
        System.out.println(output);
    }

    //Parses and prints J-type instruction
    //mnemonic {opcode: XX, index: XXXXXXX}
    public static void jType(int opcode, int binary) {
        int index = binary & 0x3FFFFFF;
        String output = String.format("j {opcode: %02x, index: %07x}", opcode, index);
        System.out.println(output);
    }

    //print syscall instruction
    //mnemonic {opcode: XX, code: 000000, funct: XX}
    public static void syscall() {
        String output = "syscall {opcode: 00, code: 000000, funct: 0c}";
        System.out.println(output);
    }
}