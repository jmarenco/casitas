package com.mym.myfirstapp.negocio;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Objeto
{
    // ID de la imagen
    private int _id;

    // Posicion virtual y dimensiones reales de la imagen
    private double _x;
    private double _y;
    private double _ancho = 10; // TODO: Calcular a partir de la imagen!
    private double _altura = 25;

    // Constructor
    public Objeto(int id, double x, double y)
    {
        _id = id;
        _x = x;
        _y = y;
    }

    // Getters
    public int getID()
    {
        return _id;
    }
    public double getX()
    {
        return _x;
    }
    public double getY()
    {
        return _y;
    }
    public double getAncho()
    {
        return _ancho;
    }
    public double getAltura()
    {
        return _altura;
    }

    // Determina si contiene al punto especificado
    public boolean contiene(double x, double y)
    {
        return _x <= x && x <= _x + _ancho && _y <= y && y <= _y + _altura;
    }
}
