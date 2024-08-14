package in.qeasy.bkutils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import in.qeasy.bkanim.BKAnim;
import in.qeasy.bkcalender.BKCalender;
import in.qeasy.bkdialog.BKDialog;
import in.qeasy.bkprefs.BKPref;
import in.qeasy.bktoast.BKToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //BKToast.makeText(this, "Hello World", BKToast.LENGTH_SHORT, BKToast.TYPE_SUCCESS, BKToast.GRAVITY_TOP_RIGHT).show();

        //new BKDialog(this).simpleAlert("This is title", "This is message. This is message.\nThis is second line", BKDialog.DIALOG_SUCCESS).show();

        /*
        String title = "Log Out App !";
        String msg = "Are you sure want to logout the app?";
        String btn1 = "Cancel";
        String btn2 = "Logout";
        BKDialog bkDialog = new BKDialog(this);
        bkDialog.confirmAlert(title, msg, btn1, btn2, BKDialog.DIALOG_ERROR).show();

        bkDialog.getBtn_dialogBtn2().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bkDialog.getDialog().dismiss();
            }
        });
         */

        //BKDialog.progressDialog(this).show();
    }
}