package vip.xiaozhao.intern.baseUtil.intf.enums;

public enum FirstMajorEnum {
    // 双值枚举
    MajorONE(1, "major1"),
    MajorTWO(2, "major2"),
    MajorTHREE(3, "major3"),
    MajorFOUR(4, "major4"),
    MajorFIVE(5, "major5");

    private int number;
    private String major;

    FirstMajorEnum(int number, String major) {
        this.number = number;
        this.major = major;
    }

    public int getNumber() {
        return number;
    }

    public String getMajor() {
        return major;
    }

    public static String getMajor(int number) {
        for (FirstMajorEnum firstMajorEnum : FirstMajorEnum.values()) {
            if (firstMajorEnum.getNumber() == number) {
                return firstMajorEnum.getMajor();
            }
        }
        return "其他";
    }

    public static void main(String[] args) {
        System.out.println(FirstMajorEnum.values()[0].getNumber());
        System.out.println(FirstMajorEnum.values()[0].getMajor());
    }
}
