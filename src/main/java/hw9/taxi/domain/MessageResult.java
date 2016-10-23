package hw9.taxi.domain;

/**
 * Created by s_okhoda on 12.03.2016.
 */
public class MessageResult {
    private String mText;
    private String mColor;

    public MessageResult(String mText, String mColor) {
        this.mText = mText;
        this.mColor = mColor;
    }

    public MessageResult() {
    }

    public String getMText() {
        return mText;
    }

    public void setMText(String mText) {
        this.mText = mText;
    }

    public String getMColor() {
        return mColor;
    }

    public void setMColor(String mColor) {
        this.mColor = mColor;
    }
}
