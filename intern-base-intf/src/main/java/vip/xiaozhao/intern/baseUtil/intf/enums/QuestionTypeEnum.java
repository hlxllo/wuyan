package vip.xiaozhao.intern.baseUtil.intf.enums;

// 问题类别枚举
public enum QuestionTypeEnum {
    POLICE(1, "考研政策"),
    LIFE(2, "就业生活"),
    STRATEGY(3, "备考策略"),
    OTHER(100, "其他");

    private int number;
    private String type;

    QuestionTypeEnum(int number, String type) {
        this.number = number;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public static String getType(int number) {
        for (QuestionTypeEnum questionTypeEnum : QuestionTypeEnum.values()) {
            if (questionTypeEnum.getNumber() == number) {
                return questionTypeEnum.getType();
            }
        }
        throw new RuntimeException("问题类型不存在");
    }
}
