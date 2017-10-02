package cmput301.sychan1_countbook;

import android.widget.EditText;

/**
 * Created by Steven C on 10/1/2017.
 */

public class InputAuthenticator {

    public boolean isEmpty(EditText editText) {
        if (editText.getText().toString().trim().length() > 0) {
            return false;
        }
        return true;
    }

    public boolean isLessThanZero(EditText editText) {
        if (Integer.parseInt(editText.getText().toString()) >= 0) {
            return false;
        }
        return true;
    }
}
