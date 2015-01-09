package com.mym.myfirstapp.mvc;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

import com.mym.myfirstapp.MainActivity;
import com.mym.myfirstapp.negocio.Nivel;
import com.mym.myfirstapp.negocio.Objeto;

public class Vista
{
    // Activity principal y controller
    private MainActivity _activity;
    private Controller _controller;

    // Canvas donde se dibuja
    private Canvas _canvas;
    private ImageView _imageView;
    private Paint _paint;

    // Ancho y altura del display y del nivel
    private double _anchoDisplay;
    private double _alturaDisplay;
    private double _anchoNivel;
    private double _alturaNivel;

    // Nivel actual
    private Nivel _nivel;

    // Constructor
    public Vista(Controller controller)
    {
        _controller = controller;
        _controller.setVista(this);

        _activity = _controller.getActivity();
        _nivel = _controller.getNivel();

        _anchoDisplay = _activity.getAnchoDisplay();
        _alturaDisplay = _activity.getAlturaDisplay();
        _anchoNivel = _nivel.getAncho();
        _alturaNivel = _nivel.getAltura();
    }

    // Setters
    public void setCanvas(Canvas canvas, ImageView imageView)
    {
        _canvas = canvas;
        _imageView = imageView;

        _paint = new Paint();
        _paint.setColor(Color.BLUE);
        _paint.setStrokeWidth(3);
    }

    // Inicializa el nivel
    public void inicializar()
    {
        for(Objeto objeto: _nivel.getObjetos())
            _activity.crearImagen(objeto.getID(), convX(objeto), convY(objeto));
    }

    // Convierte unidades del modelo a unidades en el display
    public int convX(Objeto objeto)
    {
        return (int)(objeto.getX() * _anchoDisplay / _anchoNivel);
    }
    public int convY(Objeto objeto)
    {
        return (int)(objeto.getY() * _alturaDisplay / _alturaNivel);
    }

    // Convierte unidades en el display a unidades del modelo
    public double pointX(float x)
    {
        return x * _anchoNivel / _anchoDisplay;

    }
    public double pointY(float y)
    {
        return y * _alturaNivel / _alturaDisplay;
    }

    // Dibuja una línea
    public void dibujar(float x1, float y1, float x2, float y2)
    {
        _canvas.drawLine(x1, y1, x2, y2, _paint);
        _imageView.invalidate();
    }
}
