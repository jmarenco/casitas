package com.mym.myfirstapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mym.myfirstapp.mvc.Controller;
import com.mym.myfirstapp.mvc.Vista;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Creamos el controller y la vista
        controller = new Controller(this);
        vista = new Vista(controller);

        // Creamos el relative layout
        layout = new RelativeLayout(MainActivity.this);
        layout.setId(R.id.layout_id);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(layoutParams);

        // Creamos un canvas para dibujar
        bitmap = Bitmap.createBitmap((int)getAnchoDisplay(), (int)getAlturaDisplay(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);

        imageView = new ImageView(MainActivity.this);
        imageView.setId(R.id.image_id);

        RelativeLayout.LayoutParams imageViewLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(imageViewLayoutParams);
        imageView.setImageBitmap(bitmap);
        layout.addView(imageView);

        // Creamos un TextView auxiliar
        textView = new TextView(MainActivity.this);
        textView.setId(R.id.text_id);

        LayoutParams textViewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textViewLayoutParams);
        layout.addView(textView);

        // Seteamos el layout principal
        setContentView(layout);
        layout.setOnClickListener(viewOnClickListener);

        // Muestra el nivel
        vista.inicializar();

        // Touch listener
        layout.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {

                x = event.getX();
                y = event.getY();

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    downx = x;
                    downy = y;

                    textView.setText("Touch coordinates : " + Float.toString(x) + "x" + Float.toString(y));
                }
                else if (event.getAction() == MotionEvent.ACTION_MOVE)
                {
                    canvas.drawLine(downx, downy, x, y, paint);
                    imageView.invalidate();

                    downx = x;
                    downy = y;

                    textView.setText("Move to : " + Float.toString(x) + "x" + Float.toString(y));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    canvas.drawLine(downx, downy, x, y, paint);
                    imageView.invalidate();

                    downx = 0;
                    downy = 0;

                    textView.setText("Up in : " + Float.toString(x) + "x" + Float.toString(y));
                }
                return true;
            }
        });
    }

    private float x;
    private float y;
    private float downx;
    private float downy;

    private Controller controller;
    private Vista vista;
    private RelativeLayout layout;
    private TextView textView;
    private Bitmap bitmap;
    private Canvas canvas;
    private final Paint paint = new Paint();
    private ImageView imageView;

    // Ancho y alto del display
    public double getAnchoDisplay()
    {
        Display currentDisplay = getWindowManager().getDefaultDisplay();
        return currentDisplay.getWidth();
    }
    public double getAlturaDisplay()
    {
        Display currentDisplay = getWindowManager().getDefaultDisplay();
        return currentDisplay.getHeight();
    }

    // Agrega una imagen
    public void crearImagen(int id, double x, double y)
    {
        ImageView imagen = new ImageView(MainActivity.this);
        imagen.setId(R.id.image_id);
        imagen.setImageResource(id);
//        imagen.setImageResource(R.drawable.casita);

        RelativeLayout.LayoutParams imageViewLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        imageViewLayoutParams.setMargins((int)x, (int)y, 0, 0);
        imagen.setLayoutParams(imageViewLayoutParams);

        layout.addView(imagen);
//        imagen.setOnClickListener(viewOnClickListener);
    }

    OnClickListener viewOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v)
        {
            textView.setText("ID: " + String.valueOf(v.getId()) + " clicked");
        }};
}