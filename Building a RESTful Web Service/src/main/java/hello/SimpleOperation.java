package hello;

public class SimpleOperation {
    private Long left;
    private Long right;
    public SimpleOperation(Long left, Long right) {
        this.left = left;
        this.right = right;
    }

    public Long getAddition() {
        return left + right;
    }

    public Long getMultiplication() {
        return left * right;
    }
}
