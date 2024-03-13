package cn.wxl475.pojo;

public enum FileType {
    IMAGE("image"),
    VIDEO("video");
//    AUDIO("audio"),
//    FILE("file");

    private final String type;

    FileType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
