package com.myapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Ignacio on 16/02/2020.
 */

public class Bresenham implements SurfaceHolder.Callback {

    public Bresenham(SurfaceView pSurface) {
        pSurface.getHolder().addCallback(this);
    }

    public void surfaceCreated(SurfaceHolder holder) {

        Rect rect = holder.getSurfaceFrame();
        Bitmap bitmap = Bitmap.createBitmap(rect.width(),
                rect.height(),Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);

        canvas.drawLine(20,0,30,100,paint);

        doUpdate(holder, bitmap);

        /**/
        // BRESENHAM ALGORITHM
        Paint paint_green = new Paint();
        paint_green.setStyle(Paint.Style.FILL);
        paint_green.setColor(Color.GREEN);

        canvas.drawText(rect.width()+"x"+rect.height(), 250,250, paint_green);

        int xc = 151;
        int yc = 151;
        int r = 50;

        int x = 0;
        int y = r;
        int d = 3 - 2 * r;

        while (y>=x) {
            canvas.drawPoint(xc+x, yc+y, paint_green);
            canvas.drawPoint(xc+x, yc-y, paint_green);
            canvas.drawPoint(xc-x, yc+y, paint_green);
            canvas.drawPoint(xc-x, yc-y, paint_green);

            canvas.drawPoint(xc+y, yc+x, paint_green);
            canvas.drawPoint(xc+y, yc-x, paint_green);
            canvas.drawPoint(xc-y, yc+x, paint_green);
            canvas.drawPoint(xc-y, yc-x, paint_green);

            x+=1;
            if (d > 0){
                y-=1;
                d = d + 4 * (x - y) + 10;
            }
            else d = d + 4 * x + 6;
        }

        doUpdate(holder, bitmap);

        canvas.drawCircle(152,152,45,paint);
        doUpdate(holder, bitmap);
    }

    private void doUpdate(SurfaceHolder holder, Bitmap bitmap){
        Canvas canvas_holder = holder.lockCanvas();
        canvas_holder.drawBitmap (bitmap, 0, 0, null);
        holder.unlockCanvasAndPost(canvas_holder);
    }


    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    public void surfaceDestroyed(SurfaceHolder holder) {

    }


}
