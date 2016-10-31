package com.example.hp.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnTouchListener{

    private ImageView imgafter;
    private ImageView imgbefore;
    private Canvas canvas;
    private Paint paint;
    private Bitmap bitmap;
    private Bitmap before;
    private Bitmap after;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgafter = (ImageView) findViewById(R.id.after);
        imgbefore = (ImageView) findViewById(R.id.before);
        // 获得图片
        after = BitmapFactory.decodeResource(getResources(), R.drawable.b);
        before = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        imgafter.setImageBitmap(after);
        imgbefore.setImageBitmap(before);
        // 创建可以修改的空白的bitmap
        bitmap = Bitmap.createBitmap(before.getWidth(), before.getHeight(),
                before.getConfig());
        imgbefore.setOnTouchListener(this);
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
        // 创建画布
        canvas = new Canvas(bitmap);
        canvas.drawBitmap(before, new Matrix(), paint);

    }
    @Override
    public boolean onTouch(View arg0, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int newX = (int) event.getX();
                int newY = (int) event.getY();
                // 将滑过的地方变为透明
                for (int i = -10; i < 10; i++) {
                    for (int j = -10; j < 10; j++) {
                        if ((i + newX) >= before.getWidth()
                                || j + newY >= before.getHeight() || i + newX < 0
                                || j + newY < 0) {
                            return false;
                        }
                        bitmap.setPixel(i + newX, j + newY, Color.TRANSPARENT);
                    }
                }
                imgbefore.setImageBitmap(bitmap);
                break;
        }
        return true;
    }
}
