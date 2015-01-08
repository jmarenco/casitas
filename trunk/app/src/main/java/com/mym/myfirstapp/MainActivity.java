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

        imageView = new ImageView(MainActivity.this);
        imageView.setId(R.id.image_id);

        RelativeLayout.LayoutParams imageViewLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(imageViewLayoutParams);
        imageView.setImageBitmap(bitmap);
        layout.addView(imageView);
        vista.setCanvas(canvas, imageView);

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
                float x = event.getX();
                float y = event.getY();

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    controller.inicioDrag(x, y);
                else if (event.getAction() == MotionEvent.ACTION_MOVE)
                    controller.drag(x, y);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    controller.finDrag(x, y);

                return true;
            }
        });
    }

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