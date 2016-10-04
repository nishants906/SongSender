package first.com.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class choice extends AppCompatActivity {

    Button send,recieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        send= (Button) findViewById(R.id.send);
        recieve= (Button) findViewById(R.id.recieve);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(choice.this,MusicActivity.class);
                startActivity(intent);
                finish();

            }
        });
        recieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(choice.this,blutooth.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
