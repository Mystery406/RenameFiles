package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.File;

public class Controller {
    @FXML
    private TextField path;
    @FXML
    private TextField prefix;

    @FXML
    private void handleOk() {
        if (checkInput()) {
            String filePath = path.getText();
            File file = new File(filePath + File.separator);
            if (file.exists()) {
                File[] files = file.listFiles();
                if (files != null && files.length > 0) {
                    for (File f : files) {
                        f.renameTo(new File(f.getParent() + File.separator + prefix.getText() + f.getName()));
                    }
                    showAlert(Alert.AlertType.INFORMATION, "提示", "弄完了", null);
                } else {
                    showAlert(Alert.AlertType.ERROR, "错误", "不是", "里面文件都没你搞啥啊");
                }
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "错误", "不是", "路径都不填你瞎点啥啊");
        }
    }

    private boolean checkInput() {
        return path.getText().length() > 0;
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }
}
