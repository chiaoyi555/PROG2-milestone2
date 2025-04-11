//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //String input = args[0];
        //String input = "020ed020"; // rType
        //String input = "246600e0"; // iType
        String input = "080000c7"; // jType
        //String input = "0000000c"; //syscall

        int binary = (int) Long.parseLong(input, 16);

        // & 0x3F is used to mask out and extract only the lowest 6 bits
        int opcode = (binary >>> 26) & 0x3F;
        int funct = binary & 0x3F; // for distinguishing syscall from other R-types
        String type = Instructions.InstructionType(opcode, funct);

        System.out.println(type);

        switch (type) {
            case "R" -> Instructions.rType(opcode, binary);
            case "I" -> Instructions.iType(opcode, binary);
            case "J" -> Instructions.jType(opcode, binary);
            default -> Instructions.syscall();
        }
    }
}

