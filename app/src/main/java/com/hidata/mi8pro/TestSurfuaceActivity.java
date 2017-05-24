package com.hidata.mi8pro;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import java.io.IOError;

/**
 * Created by k_way on 2017/5/15.
 */

public class TestSurfuaceActivity extends Activity {

    SurfaceView mSurfaceiew;

    private String[] xAxis = new String[]{"9:00", "9:10", "9:15", "9:20", "9:25"};
    private Double[] yAxis = new Double[]{1213.00, 16313.00, 2313.00, 1313.00, 1332.00};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSurfaceiew = new SurfaceView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mSurfaceiew.setLayoutParams(layoutParams);
        setContentView(mSurfaceiew);

        final SurfaceHolder surfaceHolder = mSurfaceiew.getHolder();

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                DrawThread drawThread = new DrawThread(surfaceHolder);
                drawThread.setxAxis(xAxis);
                drawThread.setyAxis(yAxis);
                new Thread(drawThread).start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });


    }


    class DrawThread implements Runnable {

        private SurfaceHolder holder;
        private Boolean run;
        private String[] xAxis;
        private Double[] yAxis;


        private int padding = 70; //轴离画布边的距离
        private int scaleHeight = 5;//标线的高度


        //颜色
        private static final int COLOR_LINE = Color.BLUE;//拆线颜色
        private static final int COLOR_AXIS_Y_MID = Color.GREEN;//中轴线
        private static final int COLOR_AXIS_TEXT = Color.GRAY;//字体颜色
        private static final int COLOR_AXIS = Color.TRANSPARENT;//轴线颜色


        DrawThread(SurfaceHolder holder) {
            this.holder = holder;
        }

        public String[] getxAxis() {
            return xAxis;
        }

        public void setxAxis(String[] xAxis) {
            this.xAxis = xAxis;
        }

        public Double[] getyAxis() {
            return yAxis;
        }

        public void setyAxis(Double[] yAxis) {
            this.yAxis = yAxis;
        }

        @Override
        public void run() {
            int cnt = 0;
            Canvas canvas = null;


            //具体绘制工作
            try {
                // 获取Canvas对象，并锁定之
                canvas = holder.lockCanvas();
                // 设定Canvas对象的背景颜色
                canvas.drawColor(Color.WHITE);

                //画XY轴
                draw(canvas);


            } catch (Exception e) {

            } finally {
                if (canvas != null) {
                    // 解除锁定，并提交修改内容
                    holder.unlockCanvasAndPost(canvas);
                }
            }


        }


        private void draw(Canvas canvas) {
            int cWith = canvas.getWidth();
            int cHeight = canvas.getHeight();

            int startX = padding;
            int startY = cHeight - padding;
            int stopX = cWith - padding;
            int stopY = padding;
            Paint paint = new Paint();

            //画X轴
            paint.setColor(COLOR_AXIS);
            canvas.drawLine(startX, startY, stopX, startY, paint);
            //y轴
            canvas.drawLine(startX, startY, startX, stopY, paint);
            //画y中轴线
            paint.setColor(COLOR_AXIS_Y_MID);
            canvas.drawLine(startX, (startY + stopY) / 2, stopX, (startY + stopY) / 2, paint);

            //标线
            //x轴标线
            int xScaleCnt = xAxis.length - 1;
            int xScale = (stopX - startX) / xScaleCnt;
            for (int i = 0; i <= xScaleCnt; i++) {
                if (i % 2 == 1) continue;
                paint.setColor(COLOR_AXIS);
                canvas.drawLine(startX + i * xScale, startY, startX + i * xScale, startY - scaleHeight, paint);

                //画x轴的文字
                Rect rect = new Rect();
                paint.getTextBounds(xAxis[i], 0, xAxis[i].length(), rect);
                paint.setColor(COLOR_AXIS_TEXT);
                canvas.drawText(xAxis[i], (startX + i * xScale) - rect.width() / 2, startY + rect.height() * 3 / 2, paint);

            }


            //Y轴标线
            //以y数组第一个值为中间值
            Double yMax = yAxis[0];
            Double yMin = yAxis[0];
            Double yAvg = yAxis[0];
            for (Double tmp : yAxis) {
                if (yMax < tmp) {
                    yMax = tmp;
                }
                if (yMin > tmp) {
                    yMin = tmp;
                }
            }

            if (Math.abs(yMax) < Math.abs(yMin)) {
                yMax = Math.abs(yMin);
            } else {
                yMax = Math.abs(yMax);
            }

            if (yMax < Math.abs(yAvg * (1 + 0.001))) {
                yMax = Math.abs(yAvg * (1 + 0.001));
            }

            float yStartValue = (float) (yAvg - Math.abs(yAvg - yMax));

            float yRatio = (float) ((yMax - yAvg) / ((startY - stopY) / 2));


            //y轴标线，统一4等分
            for (int i = 0; i <= 4; i++) {
                float tmpY = ((startY - stopY) * i / 4);
                paint.setColor(COLOR_AXIS);
                canvas.drawLine(startX, (startY - tmpY), startX + scaleHeight, (startY - tmpY), paint);
                //画文字
                String text = String.format("%.2f", tmpY * yRatio + yStartValue);
                Rect rect = new Rect();
                paint.getTextBounds(text, 0, text.length(), rect);
                paint.setColor(COLOR_AXIS_TEXT);
                canvas.drawText(text, startX - rect.width() - 5, (startY - tmpY + rect.height() / 2), paint);

            }


            float lX = startX;
            float lY = (float) (startY - ((yAxis[0] - yStartValue) / yRatio));

            //画曲线
            paint.setColor(COLOR_LINE);
            for (int i = 0; i < yAxis.length; i++) {
                float cX = startX + i * xScale;
                float cY = (float) (startY - ((yAxis[i] - yStartValue) / yRatio));
                canvas.drawLine(lX, lY, cX, cY, paint);
                lX = cX;
                lY = cY;
            }


        }


    }


}
