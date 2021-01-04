package com.example.pdf1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btnclick;
    int pageWidth = 1200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        btnclick = (Button) findViewById(R.id.buttonok);
        createPDF();
    }

    private void createPDF() {
        btnclick.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                PdfDocument pdfDocument = new PdfDocument();
                // mo mau
                Paint myPaint = new Paint();
                // gioi thieu
                Paint paint1 = new Paint();
                // Cac tieu de
                Paint titlePaint = new Paint();
                Paint titlePaint2 = new Paint();
                // Noi dung
                Paint contentPaint = new Paint();
                // ky nang
                Paint kynang_paint = new Paint();
                kynang_paint.setStyle(Paint.Style.STROKE);
                kynang_paint.setStrokeWidth(10);
                Paint kynangphu = new Paint();
                kynangphu.setStyle(Paint.Style.STROKE);
                kynangphu.setStrokeWidth(10);
                kynangphu.setColor(Color.YELLOW);

                PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1200,2000,1).create();
                PdfDocument.Page page = pdfDocument.startPage(pageInfo);
                Canvas canvas = page.getCanvas();
                myPaint.setStyle(Paint.Style.FILL);
                myPaint.setColor(Color.rgb(100,100,100));
                canvas.drawRect(0, 0, pageWidth, 300, myPaint);

                paint1.setColor(Color.WHITE);
                paint1.setTextAlign(Paint.Align.LEFT);
                paint1.setTextSize(50);
                canvas.drawText("Nguyễn Chí Tôn", 30, 80, paint1);
                paint1.setTextSize(35);
                canvas.drawText("DBA", 30, 140, paint1);


                paint1.setTextSize(30);
                canvas.drawText("Ký túc xá B, đại học Cần Thơ", 30, 230, paint1);
                canvas.drawText("Batphuongtrinhvoti@gmail.com", 500, 230, paint1);
                canvas.drawText("032323232", pageWidth-230, 230, paint1);
                // muc tieu nghe nghiep
                titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                titlePaint.setTextSize(35);
                titlePaint.setColor(Color.BLACK);
                canvas.drawText("MỤC TIÊU NGHỀ NGHIỆP", 30, 380, titlePaint);

                contentPaint.setColor(Color.BLACK);
                contentPaint.setTextSize(25);
                canvas.drawText("Trở thành DBA làm việc trong một ngân hàng lớn, lương 1000$/năm", 30, 450, contentPaint);

                // hoc van
                canvas.drawText("HỌC VẤN", 30,  530, titlePaint);
                titlePaint2.setTextSize(30);
                titlePaint2.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                canvas.drawText("ĐẠI HỌC CẦN THƠ", 30, 610, titlePaint2);
                canvas.drawText("10/2017 - 10/2021", 30, 660, contentPaint);
                canvas.drawText("CHUYÊN NGÀNH: CÔNG NGHỆ THÔNG TIN", 500, 610, titlePaint2 );
                canvas.drawText("Tốt nghiệp loại giỏi, điểm trung bình 8.0", 500, 660, contentPaint);

                // hoat dong
                canvas.drawText("HOẠT ĐỘNG", 30, 730, titlePaint);
                canvas.drawText("THAM GIA CLUB TIẾNG ANH", 30, 800, titlePaint2);
                canvas.drawText("2020 - 2021", 30, 850, contentPaint);
                canvas.drawText("NGƯỜI THAM GIA", 500,800, titlePaint2);
                canvas.drawText("Nói tiếng anh với người bản xứ",  500, 850, contentPaint);

                // giai thuong
                canvas.drawText("GIẢI THƯỞNG", 30, 920, titlePaint);
                canvas.drawText("10/2017-12/2017", 30, 970, contentPaint);
                canvas.drawText("Học bổng du học Thái Lan", 500, 970, contentPaint);
                // ky nang
                canvas.drawText("KỸ NĂNG", 30, 1050, titlePaint);
                canvas.drawText("Kỹ năng tiếng anh", 30, 1100, contentPaint);
                int width = 300, height = 50;
                canvas.drawLine(30, 1150, 30 + width, 1150, kynang_paint );
                canvas.drawText("Kỹ năng tin học", 30, 1200, contentPaint);
                canvas.drawLine(30, 1250, width-100 + 30, 1250, kynang_paint);
                canvas.drawLine(30 + width - 100, 1250, width + 30, 1250, kynangphu);

                pdfDocument.finishPage(page);
                File file = new File(Environment.getExternalStorageDirectory(), "/a1.pdf");
                try {
                    pdfDocument.writeTo(new FileOutputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pdfDocument.close();

            }
        });

    }
}
