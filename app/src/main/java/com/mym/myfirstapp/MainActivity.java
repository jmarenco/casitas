package com.mym.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mym.myfirstapp.mvc.Controller;
import com.mym.myfirstapp.mvc.Vista;

import java.text.DecimalFormat;
import java.text.Format;

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

        // Fondo de pantalla
        // ImageView fondo = new ImageView(MainActivity.this);
        // fondo.setId(R.id.image_id);
        // fondo.setImageResource(R.drawable.pasto1024x768);
        // fondo.setScaleType(ImageView.ScaleType.FIT_XY);
        // layout.addView(fondo);

        // Creamos un canvas para dibujar
        Bitmap bitmap = Bitmap.createBitmap((int)getAnchoDisplay(), (int)getAlturaDisplay(), Bitmap.Config.ARGB_8888);

        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setId(R.id.image_id);

        RelativeLayout.LayoutParams imageViewLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(imageViewLayoutParams);
        imageView.setImageBitmap(bitmap);
        layout.addView(imageView);
        vista.setBitmap(bitmap, imageView);

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

        final Format format = new DecimalFormat("#.00");

        // Touch listener
        layout.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                float x = event.getX();
                float y = event.getY();

                // textView.setText("Real: (" + format.format(x) + ", " + format.format(y) + ")" + "- Virtual: (" + format.format(vista.pointX(x)) + ", " + format.format(vista.pointY(y)) + ")");

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
    public ImageView crearImagen(int imageID, int resourceID, double x, double y)
    {
        ImageView imagen = new ImageView(MainActivity.this);
        imagen.setId(imageID);
        imagen.setImageResource(resourceID);

        RelativeLayout.LayoutParams imageViewLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        imageViewLayoutParams.setMargins((int)x, (int)y, 0, 0);
        imagen.setLayoutParams(imageViewLayoutParams);

        layout.addView(imagen);
        return imagen;
    }
    public ImageView crearImagenClickeable(int imageID, int resourceID, double x, double y)
    {
        ImageView imagen = crearImagen(imageID, resourceID, x, y);
        imagen.setOnClickListener(viewOnClickListener);
        return imagen;
    }

    OnClickListener viewOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v)
        {
            vista.click(v.getId());
        }};

    // Auxiliar para el debug
    public void mostrarTexto(String texto)
    {
        textView.setText(texto);
    }

    // Inicia otra activity con el próximo nivel
    public void nuevoNivel()
    {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}